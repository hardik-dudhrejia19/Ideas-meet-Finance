package com.dal.ideasmeetfinance.pojo;

public class CardModel {
    private int image;
    private String title_card;
    private String abs_card;
    private String content_card;

    public CardModel(int image, String title_card, String abs_card, String content_card) {
        this.image = image;
        this.title_card = title_card;
        this.abs_card = abs_card;
        this.content_card = content_card;
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
