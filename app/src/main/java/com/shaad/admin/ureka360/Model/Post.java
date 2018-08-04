package com.shaad.admin.ureka360.Model;


import com.google.gson.annotations.SerializedName;
import com.shaad.admin.ureka360.Model.Class_Model.Content;
import com.shaad.admin.ureka360.Model.Class_Model.Excerpt;
import com.shaad.admin.ureka360.Model.Class_Model.Guid;
import com.shaad.admin.ureka360.Model.Class_Model.Links;
import com.shaad.admin.ureka360.Model.Class_Model.Meta;
import com.shaad.admin.ureka360.Model.Class_Model.Title;

import java.util.ArrayList;
import java.util.List;

public class Post {
    @SerializedName("id")
    private Integer id;
    @SerializedName("date")
    private String date;
    @SerializedName("date_gmt")
    private String date_gmt;
    @SerializedName("guid")
    private Guid guid;
    @SerializedName("modified")
    private String modified;
    @SerializedName("modified_gmt")
    private String modified_gmt;
    @SerializedName("slug")
    private String slug;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private Title title;
    @SerializedName("content")
    private Content content;
    @SerializedName("excerpt")
    private Excerpt excerpt;
    @SerializedName("author")
    private Integer author;
    @SerializedName("featured_media")
    private Integer featured_media;
    @SerializedName("comment_status")
    private String comment_status;
    @SerializedName("ping_status")
    private String ping_status;
    @SerializedName("sticky")
    private Boolean sticky;
    @SerializedName("template")
    private String template;
    @SerializedName("format")
    private String format;
    @SerializedName("meta")
    private Meta meta;
    @SerializedName("categories")
    private List<Integer> categories = new ArrayList<>();
    @SerializedName("tags")
    private List<Integer> tags = new ArrayList<>();
    @SerializedName("_links")
    private Links _links;


    public Post(Integer id, String date, String date_gmt, Guid guid, String modified, String modified_gmt, String slug, String status, String type, String link, Title title, Content content, Excerpt excerpt, Integer author, Integer featured_media, String comment_status, String ping_status, Boolean sticky, String template, String format, Meta meta, List<Integer> categories, List<Integer> tags, Links _links) {
        this.id = id;
        this.date = date;
        this.date_gmt = date_gmt;
        this.guid = guid;
        this.modified = modified;
        this.modified_gmt = modified_gmt;
        this.slug = slug;
        this.status = status;
        this.type = type;
        this.link = link;
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.author = author;
        this.featured_media = featured_media;
        this.comment_status = comment_status;
        this.ping_status = ping_status;
        this.sticky = sticky;
        this.template = template;
        this.format = format;
        this.meta = meta;
        this.categories = categories;
        this.tags = tags;
        this._links = _links;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public void setDate_gmt(String date_gmt) {
        this.date_gmt = date_gmt;
    }

    public Guid getGuid() {
        return guid;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModified_gmt() {
        return modified_gmt;
    }

    public void setModified_gmt(String modified_gmt) {
        this.modified_gmt = modified_gmt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Excerpt getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(Excerpt excerpt) {
        this.excerpt = excerpt;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getFeatured_media() {
        return featured_media;
    }

    public void setFeatured_media(Integer featured_media) {
        this.featured_media = featured_media;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public String getPing_status() {
        return ping_status;
    }

    public void setPing_status(String ping_status) {
        this.ping_status = ping_status;
    }

    public Boolean getSticky() {
        return sticky;
    }

    public void setSticky(Boolean sticky) {
        this.sticky = sticky;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }


}

