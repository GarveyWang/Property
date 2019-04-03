package com.garvey.property.model;

/**
 * @author GarveyWong
 * @date 2019/4/2
 */
public class IncomeItem {
    private String payer;
    private String recorder;
    private long amountInCents;
    private String desc;
    private String fileHashes;
    private String fileNames;
    private long timestamp;

    public IncomeItem() {
    }

    public IncomeItem(String payer, String recorder, long amountInCents, String desc, String fileHashes, String fileNames, long timestamp) {
        this.payer = payer;
        this.recorder = recorder;
        this.amountInCents = amountInCents;
        this.desc = desc;
        this.fileHashes = fileHashes;
        this.fileNames = fileNames;
        this.timestamp = timestamp;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
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

    public String getFileHashes() {
        return fileHashes;
    }

    public void setFileHashes(String fileHashes) {
        this.fileHashes = fileHashes;
    }

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
