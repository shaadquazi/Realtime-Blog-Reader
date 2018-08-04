package com.shaad.admin.ureka360.Model;

import com.google.gson.annotations.SerializedName;
import com.shaad.admin.ureka360.Model.Class_Model.Caption;
import com.shaad.admin.ureka360.Model.Class_Model.Description;
import com.shaad.admin.ureka360.Model.Class_Model.Guid;
import com.shaad.admin.ureka360.Model.Class_Model.Links;
import com.shaad.admin.ureka360.Model.Class_Model.MediaDetails;
import com.shaad.admin.ureka360.Model.Class_Model.Meta;
import com.shaad.admin.ureka360.Model.Class_Model.Title;

public class WPMedia{
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
    @SerializedName("author")
    private Integer author;
    @SerializedName("comment_status")
    private String comment_status;
    @SerializedName("ping_status")
    private String ping_status;
    @SerializedName("template")
    private String template;
    @SerializedName("meta")
    private Meta meta;
    @SerializedName("description")
    private Description description;
    @SerializedName("caption")
    private Caption caption;
    @SerializedName("alt_text")
    private String alt_text;
    @SerializedName("media_type")
    private String media_type;
    @SerializedName("mime_type")
    private String mime_type;
    @SerializedName("media_details")
    private MediaDetails media_details;
    @SerializedName("post")
    private Integer post;
    @SerializedName("source_url")
    private String source_url;
    @SerializedName("_links")
    private Links _links;


    public WPMedia() {
    }

    public WPMedia(Integer id, String date, String date_gmt, Guid guid, String modified, String modified_gmt, String slug, String status, String type, String link, Title title, Integer author, String comment_status, String ping_status, String template, Meta meta, Description description, Caption caption, String alt_text, String media_type, String mime_type, MediaDetails media_details, Integer post, String source_url, Links _links) {
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
        this.author = author;
        this.comment_status = comment_status;
        this.ping_status = ping_status;
        this.template = template;
        this.meta = meta;
        this.description = description;
        this.caption = caption;
        this.alt_text = alt_text;
        this.media_type = media_type;
        this.mime_type = mime_type;
        this.media_details = media_details;
        this.post = post;
        this.source_url = source_url;
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

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
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

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }

    public String getAlt_text() {
        return alt_text;
    }

    public void setAlt_text(String alt_text) {
        this.alt_text = alt_text;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public MediaDetails getMedia_details() {
        return media_details;
    }

    public void setMedia_details(MediaDetails media_details) {
        this.media_details = media_details;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }
}
