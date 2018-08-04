package com.shaad.admin.ureka360.Model;

import com.google.gson.annotations.SerializedName;
import com.shaad.admin.ureka360.Model.Class_Model.Links;

public class Category {
    @SerializedName("id")
    private Integer id;
    @SerializedName("count")
    private Integer count;
    @SerializedName("description")
    private String description;
    @SerializedName("link")
    private String link;
    @SerializedName("name")
    private String name;
    @SerializedName("slug")
    private String slug;
    @SerializedName("taxonomy")
    private String taxonomy;
    @SerializedName("parent")
    private Integer parent;
    @SerializedName("_links")
    private Links _links;

    public Category() {
    }

    public Category(Integer id, Integer count, String description, String link, String name, String slug, String taxonomy, Integer parent, Links _links) {
        this.id = id;
        this.count = count;
        this.description = description;
        this.link = link;
        this.name = name;
        this.slug = slug;
        this.taxonomy = taxonomy;
        this.parent = parent;
        this._links = _links;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }
}
