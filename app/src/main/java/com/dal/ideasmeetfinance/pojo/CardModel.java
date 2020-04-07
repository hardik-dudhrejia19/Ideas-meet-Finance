//Class to define the structure of a card view while displaying users feed

package com.dal.ideasmeetfinance.pojo;

public class CardModel {
    private int image;
    private String user_name;
    private String title_card;
    private String abs_card;
    private String content_card;

//Constructor
    public CardModel(int image, String user_name,String title_card, String abs_card, String content_card) {
        this.user_name = user_name;
        this.image = image;
        this.title_card = title_card;
        this.abs_card = abs_card;
        this.content_card = content_card;
    }

//Getter and setter methods
    public String getUser_name() {
        return user_name;
    }

    public int getImage() {
        return image;
    }

    public String getTitle_card() {
        return title_card;
    }

    public String getAbs_card() {
        return abs_card;
    }

    public String getContent_card() {
        return content_card;
    }
}
