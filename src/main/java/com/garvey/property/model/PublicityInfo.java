package com.garvey.property.model;

import java.util.Map;

/**
 * @author GarveyWong
 * @date 2019/3/31
 */
public class PublicityInfo {
    private String title;
    private String content;
    private Map<String, String> attachments;
    private String author;
    private long timestamp;
    private int status;

    public PublicityInfo() {
    }

    public PublicityInfo(String title, String content, Map<String, String> attachments, String author, long timestamp, int status) {
        this.title = title;
        this.content = content;
        this.attachments = attachments;
        this.author = author;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
