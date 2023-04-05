package com.yz.springboot_mvc.entity;

public class User {

    /**
     * user id
     */
    private int userId;

    /**
     * username.
     */
    private String userName;

    public User() {
    }

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    /**
     * 获取
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return "User{userId = " + userId + ", userName = " + userName + "}";
    }
}
