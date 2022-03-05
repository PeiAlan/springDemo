package com.ellison.springdemo.entity;

/**
 * <p>TODO</p>
 *
 * @author Ellison_Pei
 * @date 2022/2/28 15:38
 * @since 1.0
 **/
public class User {

    private String username;

    private String password;

    private String address;

    private String phoneNum;

    private String sex;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static UserDTO convertToUserDTO(User item) {
        if (item == null) {
            return null;
        }
        UserDTO result = new UserDTO();
        result.setUsername(item.getUsername());
        result.setPassword(item.getPassword());
        result.setAddress(item.getAddress());
        result.setPhoneNum(item.getPhoneNum());
        result.setSex(item.getSex());
        return result;
    }
}
