package com.example.android.hack40.Model;

import java.util.ArrayList;

public class BoardModel {
    String name;
    int category;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public BoardModel(String name, int category) {
        this.name = name;
        this.category = category;
    }

    public BoardModel(String name) {
        this.name = name;
    }

    public BoardModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
