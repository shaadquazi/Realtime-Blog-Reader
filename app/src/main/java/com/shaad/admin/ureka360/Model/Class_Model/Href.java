package com.shaad.admin.ureka360.Model.Class_Model;

import com.google.gson.annotations.SerializedName;

class Href {
    @SerializedName("href")
    private String href;

    public Href(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
