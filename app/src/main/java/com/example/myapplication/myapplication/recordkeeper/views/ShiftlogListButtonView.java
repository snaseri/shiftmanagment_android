package com.example.myapplication.myapplication.recordkeeper.views;

import android.widget.Button;

public class ShiftlogListButtonView {

    private Button button;
    private Object tag;

    public ShiftlogListButtonView(Button button) {
        this.button = button;
        this.tag = button.getTag();
    }

    public Button getButton() {
        return button;
    }

    public Object getTag() {
        return tag;
    }

}
