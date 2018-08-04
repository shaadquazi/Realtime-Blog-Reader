package com.shaad.admin.ureka360.Model.Class_Model;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("amp_status")
    private String amp_status;

    public Meta(String amp_status) {
        this.amp_status = amp_status;
    }

    public String getAmp_status() {
        return amp_status;
    }

    public void setAmp_status(String amp_status) {
        this.amp_status = amp_status;
    }
}
