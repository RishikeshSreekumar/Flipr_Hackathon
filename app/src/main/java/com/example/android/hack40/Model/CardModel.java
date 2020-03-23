package com.example.android.hack40.Model;

public class CardModel {
    private String card_name;
    private String board_name;
    private String list_name;
    private String due_date;
    private String desc;
    private int isArchived;

    public CardModel(String card_name, String board_name, String list_name, String due_date, String desc) {
        this.card_name = card_name;
        this.board_name = board_name;
        this.list_name = list_name;
        this.due_date = due_date;
        this.desc = desc;
    }

    public CardModel() {
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public CardModel(String name, String board_name, String list_name) {
        this.card_name = name;
        this.board_name = board_name;
        this.list_name = list_name;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public int getArchived() {
        return isArchived;
    }

    public void setArchived(int archived) {
        isArchived = archived;
    }
}
