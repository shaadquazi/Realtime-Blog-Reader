package com.shaad.admin.ureka360.Model;

public class Option {
    private int iconId;
    private String title;

    public Option(int iconId, String title) {
        this.iconId = iconId;
        this.title = title;
    }

    public Option() {
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
