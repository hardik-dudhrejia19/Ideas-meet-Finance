//Class to define the structure of a chat
package com.dal.ideasmeetfinance.pojo;
import android.util.Log;

public class Posting {
    private String title;
    private int image;
    private String abs;
    private String content;
    private String author;

//    Constructor
    public Posting(){
    }

    public Posting(String title, String abs, String content, String author) {
        this.title = title;
        this.abs = abs;
        this.content = content;
        this.author = author;
    }

    public Posting(int image, String author, String title, String abs, String content) {
        this.title = title;
        this.image=image;
        this.abs = abs;
        this.content = content;
        this.author = author;
    }

//    Setter and Getter functions

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

