package com.example.android.hack40.Model;

import java.util.ArrayList;

public class ListModel {
    String name;
    String board_name;

    public ListModel(String name, String board_name) {
        this.name = name;
        this.board_name = board_name;
    }

    public ListModel() {
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
