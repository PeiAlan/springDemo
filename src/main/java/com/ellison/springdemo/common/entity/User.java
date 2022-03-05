package com.ellison.springdemo.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Accessors(chain = true)
@Setter
@Getter
public class User implements Serializable {
    private Integer uid;
    
    private String username;
    
    private String password;

    private String address;

    private String phoneNum;

    private String sex;
    
    private Set<Role> roles = new HashSet<Role>();
}
