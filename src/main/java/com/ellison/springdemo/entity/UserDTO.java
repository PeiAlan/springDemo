package com.ellison.springdemo.entity;

import lombok.Data;

/**
 * <p>TODO</p>
 *
 * @author Ellison_Pei
 * @date 2022/2/28 15:39
 * @since 1.0
 **/
@Data
public class UserDTO {
    private String username;

    private String password;

    private String address;

    private String phoneNum;

    private String sex;

    public UserDTO() {
    }
}
