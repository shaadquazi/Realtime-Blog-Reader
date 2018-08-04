package com.shaad.admin.ureka360.Model.Class_Model;

import com.google.gson.annotations.SerializedName;

public class Avatar {
    @SerializedName("24")
    private String _24;
    @SerializedName("48")
    private String _48;
    @SerializedName("96")
    private String _96;

    public Avatar(String _24, String _48, String _96) {
        this._24 = _24;
        this._48 = _48;
        this._96 = _96;
    }

    public String get_24() {
        return _24;
    }

    public void set_24(String _24) {
        this._24 = _24;
    }

    public String get_48() {
        return _48;
    }

    public void set_48(String _48) {
        this._48 = _48;
    }

    public String get_96() {
        return _96;
    }

    public void set_96(String _96) {
        this._96 = _96;
    }
}
