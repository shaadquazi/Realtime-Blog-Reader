package com.shaad.admin.ureka360.Model.Class_Model;

import com.google.gson.annotations.SerializedName;

public class Guid {
    @SerializedName("rendered")
    private String rendered;

    public Guid(String rendered) {
        this.rendered = rendered;
    }

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }
}
