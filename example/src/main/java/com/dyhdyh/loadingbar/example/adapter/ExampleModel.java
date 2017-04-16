package com.dyhdyh.loadingbar.example.adapter;

/**
 * author  dengyuhan
 * created 2017/4/16 14:27
 */
public class ExampleModel {
    private String text;
    private boolean show;
    private int duration;

    public ExampleModel(String text, boolean show, int duration) {
        this.text = text;
        this.show = show;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
