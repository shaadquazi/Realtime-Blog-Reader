package com.shaad.admin.ureka360.Model.Class_Model;

import com.google.gson.annotations.SerializedName;

class Sizes {
    @SerializedName("thumbnail")
    private MediaAttribute thumbnail;
    @SerializedName("medium")
    private MediaAttribute medium;
    @SerializedName("medium_large")
    private MediaAttribute medium_large;
    @SerializedName("large")
    private MediaAttribute large;

    public Sizes(MediaAttribute thumbnail, MediaAttribute medium, MediaAttribute medium_large, MediaAttribute large) {
        this.thumbnail = thumbnail;
        this.medium = medium;
        this.medium_large = medium_large;
        this.large = large;
    }

    public MediaAttribute getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MediaAttribute thumbnail) {
        this.thumbnail = thumbnail;
    }

    public MediaAttribute getMedium() {
        return medium;
    }

    public void setMedium(MediaAttribute medium) {
        this.medium = medium;
    }

    public MediaAttribute getMedium_large() {
        return medium_large;
    }

    public void setMedium_large(MediaAttribute medium_large) {
        this.medium_large = medium_large;
    }

    public MediaAttribute getLarge() {
        return large;
    }

    public void setLarge(MediaAttribute large) {
        this.large = large;
    }
}
