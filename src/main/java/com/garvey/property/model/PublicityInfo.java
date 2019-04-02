package com.garvey.property.model;

import java.util.Map;

/**
 * @author GarveyWong
 * @date 2019/3/31
 */
public class PublicityInfo {
    private long idx;
    private String title;
    private String content;
    private Map<String, String> attachments;
    private String authorHash;
    private String authorName;
    private long timestamp;
    private PublicityInfoProp prop;

    public PublicityInfo() {
    }

    public PublicityInfo(long idx, String title, String content, Map<String, String> attachments, String authorHash, String authorName, long timestamp) {
        this.title = title;
        this.content = content;
        this.attachments = attachments;
        this.authorHash = authorHash;
        this.authorName = authorName;
        this.timestamp = timestamp;
    }

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
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

    public String getAuthorHash() {
        return authorHash;
    }

    public void setAuthorHash(String authorHash) {
        this.authorHash = authorHash;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public PublicityInfoProp getProp() {
        return prop;
    }

    public void setProp(PublicityInfoProp prop) {
        this.prop = prop;
    }
}
