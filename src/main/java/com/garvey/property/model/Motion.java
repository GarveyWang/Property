package com.garvey.property.model;

import java.util.Map;

/**
 * @author GarveyWong
 * @date 2019/4/4
 */
public class Motion {
    private long idx;
    private String title;
    private String content;
    private Map<String, String> attachments;
    private String authorHash;
    private String authorName;
    private long timestamp;
    private boolean multipleVote;
    private int length;
    private String optionsJson;
    private String[] options;
    private int[] votes;

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

    public boolean isMultipleVote() {
        return multipleVote;
    }

    public void setMultipleVote(boolean multipleVote) {
        this.multipleVote = multipleVote;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getOptionsJson() {
        return optionsJson;
    }

    public void setOptionsJson(String optionsJson) {
        this.optionsJson = optionsJson;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int[] getVotes() {
        return votes;
    }

    public void setVotes(int[] votes) {
        this.votes = votes;
    }
}
