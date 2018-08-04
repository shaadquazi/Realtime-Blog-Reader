package com.shaad.admin.ureka360.Model.Class_Model;

import com.google.gson.annotations.SerializedName;

class Curies {
    @SerializedName("name")
    private String name;
    @SerializedName("href")
    private String href;
    @SerializedName("templated")
    private Boolean templated;

    public Curies(String name, String href, Boolean templated) {
        this.name = name;
        this.href = href;
        this.templated = templated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getTemplated() {
        return templated;
    }

    public void setTemplated(Boolean templated) {
        this.templated = templated;
    }
}
