//package com.dal.ideasmeetfinance.pojo;
//
//import android.util.Log;
//
//public class Posting {
//    private String title;
//    private int image;
//    private String abs;
//    private String content;
//    private String username;
//    private String userId;
//    public Posting(){
//    }
//
//    public Posting(String title, String abs, String content, String username, String userId) {
//        this.title = title;
//        this.abs = abs;
//        this.content = content;
//        this.username = username;
//        this.userId=userId;
//    }
//
//    public String getUserId() {
//        Log.e("m","inside fn call "+userId);
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//        Log.e("m","inside fn call set"+this.userId);
//    }
//
//    public int getImage() {
//        return image;
//    }
//
//    public void setImage(int image) {
//        this.image = image;
//    }
//
//    public Posting(int image, String username, String title, String abs, String content) {
//        this.title = title;
//        this.image=image;
//        this.abs = abs;
//        this.content = content;
//        this.username = username;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//        Log.e("m","inside fn call set title"+this.title);
//    }
//
//    public String getAbs() {
//        return abs;
//    }
//
//    public void setAbs(String abs) {
//        this.abs = abs;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//}

package com.dal.ideasmeetfinance.pojo;

import android.util.Log;

public class Posting {
    private String title;
    private int image;
    private String abs;
    private String content;
    private String author;
    public Posting(){
    }

    public Posting(String title, String abs, String content, String author) {
        this.title = title;
        this.abs = abs;
        this.content = content;
        this.author = author;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Posting(int image, String author, String title, String abs, String content) {
        this.title = title;
        this.image=image;
        this.abs = abs;
        this.content = content;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

