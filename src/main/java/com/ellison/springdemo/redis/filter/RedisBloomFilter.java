package com.ellison.springdemo.redis.filter;

import com.ellison.springdemo.redis.config.RedisConfigInfo;
import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Longs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

/**
 * 模仿Google 实现布隆过滤器
 * 使用 Redis 的 bitmap 实现
 *
 * @author Ellison_Pei
 * @date 2021/9/2 15:18
 * @since 1.0
 **/
@Component
public class RedisBloomFilter {

    public final static String RS_BF_NS = "rbf:";
    /**
     * 预估元素的数量
     */
    @Value("${redis.bloom.numApproxElements}")
    private int numApproxElements;
    /**
     * 可接受的最大误差，可能出现hash冲突
     */
    @Value("${redis.bloom.fpp}")
    private double fpp;
    /**
     * 自动计算hash函数的个数
     */
    @Value("${redis.bloom.numHashFunctions}")
    private int numHashFunctions;
    /**
     * 自动计算的最优BitMap的长度
     */
    private int bitmapLength;

    private final RedisConfigInfo redisConfigInfo;

    private JedisPool jedisPool = null;

    public RedisBloomFilter(RedisConfigInfo redisConfigInfo) {
        this.redisConfigInfo = redisConfigInfo;
    }

    /***
     *  初始化布隆过滤器
     * @return com.ellison.springdemo.redis.filter.RedisBloomFilter
     * @throws Exception
     * @author Ellison.Pei
     * @date 2021/9/2 15:29
     * @version 1.0
     */
    @PostConstruct
    public void init() {
        this.bitmapLength = (int) ((-numApproxElements * Math.log(fpp)) / (Math.log(2) * Math.log(2)));
        if (jedisPool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
            config.setBlockWhenExhausted(true);
            // 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            // 是否启用pool的jmx管理功能, 默认true
            config.setJmxEnabled(true);
            config.setMaxTotal(500);
            // 最大空闲连接数, 默认8个 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(redisConfigInfo.getMaxIdle());
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(redisConfigInfo.getMaxWaitMillis());
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(redisConfigInfo.getTestOnBorrow());
            config.setTestOnCreate(redisConfigInfo.getTestOnCreate());
            jedisPool = new JedisPool(config, redisConfigInfo.getIp(), redisConfigInfo.getPort(), 10000, redisConfigInfo.getAuth());
        }
    }

    /**
     * 计算一个元素值哈希后映射到Bitmap的哪些bit上
     * 用两个hash函数来模拟多个hash函数的情况
     * * @param element 元素值
     *
     * @return bit下标的数组
     */
    public long[] getBitIndices(String element) {
        long[] indices = new long[numHashFunctions];
        // 会把传入的字符串转为一个128位的hash值，并且转化为一个byte数组
        byte[] bytes = Hashing.murmur3_128().hashObject(element, Funnels.stringFunnel(StandardCharsets.UTF_8)).asBytes();
        long hash1 = lowerEight(bytes);
        long hash2 = upperEight(bytes);

        // 用这两个hash值来模拟多个函数产生的值
        long combineHash = hash1;
        for (int i = 0; i < numHashFunctions; i++) {
            indices[i] = (combineHash & Long.MAX_VALUE) % bitmapLength;
            combineHash = combineHash + hash2;
        }

        System.out.print(element + "数组下标: ");
        for (long index : indices) {
            System.out.print(index + ",");
        }
        System.out.println(" ");
        return indices;
    }

    private long lowerEight(byte[] bytes) {
        return Longs.fromBytes(
                bytes[7], bytes[6], bytes[5], bytes[4], bytes[3], bytes[2], bytes[1], bytes[0]);
    }

    private long upperEight(byte[] bytes) {
        return Longs.fromBytes(
                bytes[15], bytes[14], bytes[13], bytes[12], bytes[11], bytes[10], bytes[9], bytes[8]);
    }

    /**
     * 插入元素
     *
     * @param key       原始Redis键，会自动加上前缀
     * @param element   元素值，字符串类型
     * @param expireSec 过期时间（秒）
     */
    public void insert(String key, String element, int expireSec) {

        if (key == null || element == null) {
            throw new RuntimeException("键值均不能为空");
        }
        String actualKey = RS_BF_NS.concat(key);

        try (Jedis jedis = jedisPool.getResource()) {
            try (Pipeline pipeline = jedis.pipelined()) {
                for (long index : getBitIndices(element)) {
                    pipeline.setbit(actualKey, index, true);
                }
                pipeline.syncAndReturnAll();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            jedis.expire(actualKey, expireSec);
        }
    }

    /**
     * 检查元素在集合中是否（可能）存在
     *
     * @param key     原始Redis键，会自动加上前缀
     * @param element 元素值，字符串类型
     */
    public boolean mayExist(String key, String element) {
        if (key == null || element == null) {
            throw new RuntimeException("键值均不能为空");
        }
        String actualKey = RS_BF_NS.concat(key);
        boolean result = false;

        try (Jedis jedis = jedisPool.getResource()) {
            try (Pipeline pipeline = jedis.pipelined()) {
                for (long index : getBitIndices(element)) {
                    pipeline.getbit(actualKey, index);
                }
                result = !pipeline.syncAndReturnAll().contains(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "RedisBloomFilter{" +
                "numApproxElements=" + numApproxElements +
                ", fpp=" + fpp +
                ", numHashFunctions=" + numHashFunctions +
                ", bitmapLength=" + bitmapLength +
                '}';
    }


}
