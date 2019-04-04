package com.garvey.property.model;

import java.util.Map;

/**
 * @author GarveyWong
 * @date 2019/4/2
 */
public class IncomeItem {
    private long idx;
    private String payer;
    private String payerName;
    private String recorderHash;
    private String recorderName;
    private long amountInCents;
    private String desc;
    private Map<String, String> attachments;
    private long timestamp;
    private int dayInMonth;

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getRecorderHash() {
        return recorderHash;
    }

    public void setRecorderHash(String recorderHash) {
        this.recorderHash = recorderHash;
    }

    public String getRecorderName() {
        return recorderName;
    }

    public void setRecorderName(String recorderName) {
        this.recorderName = recorderName;
    }

    public long getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getDayInMonth() {
        return dayInMonth;
    }

    public void setDayInMonth(int dayInMonth) {
        this.dayInMonth = dayInMonth;
    }
}
