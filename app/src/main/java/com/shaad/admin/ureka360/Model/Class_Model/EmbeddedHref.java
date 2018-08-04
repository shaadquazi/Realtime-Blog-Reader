package com.shaad.admin.ureka360.Model.Class_Model;

import com.google.gson.annotations.SerializedName;

public class EmbeddedHref {
    @SerializedName("embeddable")
    private Boolean embeddable;
    @SerializedName("href")
    private String href;

    public EmbeddedHref(Boolean embeddable, String href) {
        this.embeddable = embeddable;
        this.href = href;
    }

    public Boolean getEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(Boolean embeddable) {
        this.embeddable = embeddable;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
