package com.bizongo.artwork.notification.beans;

public class User {
    public enum Type {
        ADMINUSER, USER
    }

    private int userId;
    private String name;
    private Type userType;
    private String email;
    private String contactNumber;

    public User(
            final int userId,
            final String name,
            final Type userType,
            final String email) {
        super();
        this.name = name;
        this.userType = userType;
        this.email = email;
        this.userId = userId;
    }

    // Needed by Caused by: com.fasterxml.jackson.databind.JsonMappingException:
    // Can not construct instance of com.in28minutes.springboot.model.Course:
    // no suitable constructor found, can not deserialize from Object value
    // (missing default constructor or creator, or perhaps need to add/enable
    // type information?)
    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getUserType() {
        return userType;
    }

    public void setUserType(Type userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    @Override
    public String toString() {
        return "User [name=" + name + ", userType=" + userType + ", email=" + email + ", contactNumber=" + contactNumber + "]";
    }
}
