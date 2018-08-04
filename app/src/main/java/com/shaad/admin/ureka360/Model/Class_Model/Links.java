package com.shaad.admin.ureka360.Model.Class_Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Links {
    @SerializedName("self")
    private List<Href> self = new ArrayList<>();
    @SerializedName("collection")
    private List<Href> collection = new ArrayList<>();
    @SerializedName("about")
    private List<Href> about = new ArrayList<>();
    @SerializedName("author")
    private List<EmbeddedHref> author = new ArrayList<>();
    @SerializedName("replies")
    private List<EmbeddedHref> replies = new ArrayList<>();
    @SerializedName("version-history")
    private List<Href> version_history = new ArrayList<>();
    @SerializedName("wp:featuredmedia")
    private List<EmbeddedHref> featuredmedia = new ArrayList<>();
    @SerializedName("wp:attachment")
    private List<Href> attachment = new ArrayList<>();
    @SerializedName("wp:term")
    private List<Term> term = new ArrayList<>();
    @SerializedName("curies")
    private List<Curies> curies = new ArrayList<>();

    public Links(List<Href> self, List<Href> collection, List<Href> about, List<EmbeddedHref> author, List<EmbeddedHref> replies, List<Href> version_history, List<EmbeddedHref> featuredmedia, List<Href> attachment, List<Term> term, List<Curies> curies) {
        this.self = self;
        this.collection = collection;
        this.about = about;
        this.author = author;
        this.replies = replies;
        this.version_history = version_history;
        this.featuredmedia = featuredmedia;
        this.attachment = attachment;
        this.term = term;
        this.curies = curies;
    }

    public List<Href> getSelf() {
        return self;
    }

    public void setSelf(List<Href> self) {
        this.self = self;
    }

    public List<Href> getCollection() {
        return collection;
    }

    public void setCollection(List<Href> collection) {
        this.collection = collection;
    }

    public List<Href> getAbout() {
        return about;
    }

    public void setAbout(List<Href> about) {
        this.about = about;
    }

    public List<EmbeddedHref> getAuthor() {
        return author;
    }

    public void setAuthor(List<EmbeddedHref> author) {
        this.author = author;
    }

    public List<EmbeddedHref> getReplies() {
        return replies;
    }

    public void setReplies(List<EmbeddedHref> replies) {
        this.replies = replies;
    }

    public List<Href> getVersion_history() {
        return version_history;
    }

    public void setVersion_history(List<Href> version_history) {
        this.version_history = version_history;
    }

    public List<EmbeddedHref> getFeaturedmedia() {
        return featuredmedia;
    }

    public void setFeaturedmedia(List<EmbeddedHref> featuredmedia) {
        this.featuredmedia = featuredmedia;
    }

    public List<Href> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<Href> attachment) {
        this.attachment = attachment;
    }

    public List<Term> getTerm() {
        return term;
    }

    public void setTerm(List<Term> term) {
        this.term = term;
    }

    public List<Curies> getCuries() {
        return curies;
    }

    public void setCuries(List<Curies> curies) {
        this.curies = curies;
    }
}
