package com.shaad.admin.ureka360.Model.Class_Model;

import com.google.gson.annotations.SerializedName;

class Term {
    @SerializedName("taxonomy")
    private String taxonomy;
    @SerializedName("embeddable")
    private Boolean embeddable;
    @SerializedName("href")
    private String href;

    public Term(String taxonomy, Boolean embeddable, String href) {
        this.taxonomy = taxonomy;
        this.embeddable = embeddable;
        this.href = href;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
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
