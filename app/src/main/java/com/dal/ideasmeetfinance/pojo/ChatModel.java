package com.dal.ideasmeetfinance.pojo;

import com.dal.ideasmeetfinance.DisplayChats;

import java.util.List;

public class ChatModel {
    private String nameCard;
    private int image;
    private String nameHidden;

    public ChatModel(String nameCard, int image, String nameHidden) {
        this.nameCard = nameCard;
        this.image = image;
        this.nameHidden = nameHidden;
    }

    public ChatModel(DisplayChats displayChats, List<ChatModel> allFactsList) {
    }

    public String getNameCard() {
        return nameCard;
    }

    public int getImage() {
        return image;
    }

    public String getNameHidden() {
        return nameHidden;
    }
}
