package com.shaad.admin.ureka360.Model.Class_Model;

import com.google.gson.annotations.SerializedName;

public class Content {
    @SerializedName("rendered")
    private String rendered;
    @SerializedName("protected")
    private String _protected;

    public Content(String rendered, String _protected) {
        this.rendered = rendered;
        this._protected = _protected;
    }

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public String get_protected() {
        return _protected;
    }

    public void set_protected(String _protected) {
        this._protected = _protected;
    }
}
