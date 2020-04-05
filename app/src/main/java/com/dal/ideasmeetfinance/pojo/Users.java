package com.dal.ideasmeetfinance.pojo;

public class Users {
    String email;
    String name;
    String username;
    Boolean entrepreneur, financer;
    String userId;
    String descision;


    public Users(String email, String name, String username, Boolean entrepreneur, Boolean financer, String userId,String descision) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.entrepreneur = entrepreneur;
        this.financer = financer;
        this.userId = userId;
        this.descision = descision;
    }

    public Users() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescision() {
        return descision;
    }

    public void setDescision(String descision) {
        this.descision = descision;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(Boolean entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    public Boolean getFinancer() {
        return financer;
    }

    public void setFinancer(Boolean financer) {
        this.financer = financer;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
