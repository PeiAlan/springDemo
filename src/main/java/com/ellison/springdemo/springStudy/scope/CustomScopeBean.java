package com.ellison.springdemo.springStudy.scope;

/**
 * @author ellisonpei
 */
//@Component
//@Scope("ellisonScope")
public class CustomScopeBean {
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
