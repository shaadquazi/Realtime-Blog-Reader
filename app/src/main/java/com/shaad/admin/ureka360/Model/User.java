package com.shaad.admin.ureka360.Model;

import com.google.gson.annotations.SerializedName;
import com.shaad.admin.ureka360.Model.Class_Model.Avatar;
import com.shaad.admin.ureka360.Model.Class_Model.Links;
public class User{
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("url")
    private String url;
    @SerializedName("description")
    private String description;
    @SerializedName("link")
    private String link;
    @SerializedName("slug")
    private String slug;
    @SerializedName("avatar_urls")
    private Avatar avatar_urls;
    @SerializedName("links")
    private Links _links;

    public User() {
    }

    public User(Integer id, String name, String email, String url, String description, String link, String slug, Avatar avatar_urls, Links _links) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.url = url;
        this.description = description;
        this.link = link;
        this.slug = slug;
        this.avatar_urls = avatar_urls;
        this._links = _links;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Avatar getAvatar_urls() {
        return avatar_urls;
    }

    public void setAvatar_urls(Avatar avatar_urls) {
        this.avatar_urls = avatar_urls;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }
}
