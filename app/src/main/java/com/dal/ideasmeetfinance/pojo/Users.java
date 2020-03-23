package com.dal.ideasmeetfinance.pojo;

public class Users {
    String email;
    String name;
    String dalId;
    Boolean entrepreneur, financer;
    String userId;

    public Users(String email, String name, String dalId, Boolean entrepreneur, Boolean financer, String userId) {
        this.email = email;
        this.name = name;
        this.dalId = dalId;
        this.entrepreneur = entrepreneur;
        this.financer = financer;
        this.userId = userId;
    }

    public Users() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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


    public String getDalId() {
        return dalId;
    }

    public void setDalId(String dalId) {
        this.dalId = dalId;
    }




}
