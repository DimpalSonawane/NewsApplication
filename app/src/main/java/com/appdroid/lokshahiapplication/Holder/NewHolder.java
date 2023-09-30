package com.appdroid.lokshahiapplication.Holder;

import java.io.Serializable;

public class NewHolder implements Serializable {

    private  String title,date_gmt;
    private String content;
    private String date;
    private String excerpt;
    private String  tags;
    private String categories;
    private String feature_image,link,video;
    long id;
    private  String shortNews;

    public NewHolder(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public void setDate_gmt(String date_gmt) {
        this.date_gmt = date_gmt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getFeature_image() {
        return feature_image;
    }

    public void setFeature_image(String feature_image) {
        this.feature_image = feature_image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortNews() {
        return shortNews;
    }

    public void setShortNews(String shortNews) {
        this.shortNews = shortNews;
    }

    public NewHolder(String title, String date_gmt, String content, String date, String excerpt, String tags,
                     String categories, String feature_image, String link, String video, long id, String shortNews) {
        this.title = title;
        this.date_gmt = date_gmt;
        this.content = content;
        this.date = date;
        this.excerpt = excerpt;
        this.tags = tags;
        this.categories = categories;
        this.feature_image = feature_image;
        this.link = link;
        this.video = video;
        this.id = id;
        this.shortNews = shortNews;
    }
}
