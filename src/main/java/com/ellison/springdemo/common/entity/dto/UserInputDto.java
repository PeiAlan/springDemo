package com.ellison.springdemo.common.entity.dto;

import com.ellison.springdemo.common.entity.User;
import com.google.common.base.Converter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * <p>TODO</p>
 *
 * @author Ellison Pei
 * @date 2020/8/9 21:39
 **/
//@Accessors(chain = true)
@Setter
@Getter
public class UserInputDto {
    private String username;

    private String password;

    private String address;

    private String phoneNum;

    private String sex;

    public User convertToUser(){
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        User convert = userDTOConvert.convert(this);
        return convert;
    }
    public UserInputDto convertFor(User user){
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        UserInputDto convert = userDTOConvert.reverse().convert(user);
        return convert;
    }
    private static class UserDTOConvert extends Converter<UserInputDto, User> {
        @Override
        protected User doForward(UserInputDto userDTO) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            return user;
        }

        @Override
        protected UserInputDto doBackward(User user) {
            UserInputDto userDTO = new UserInputDto();
            BeanUtils.copyProperties(user, userDTO);
            return userDTO;
        }
    }
}
