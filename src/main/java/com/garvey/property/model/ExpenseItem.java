package com.garvey.property.model;

/**
 * @author GarveyWong
 * @date 2019/4/2
 */
public class ExpenseItem {
    private String payee;
    private String recorder;
    private long amountInCents;
    private String desc;
    private String fileHashes;
    private String fileNames;
    private long timestamp;

    public ExpenseItem() {
    }

    public ExpenseItem(String payee, String recorder, long amountInCents, String desc, String fileHashes, String fileNames, long timestamp) {
        this.payee = payee;
        this.recorder = recorder;
        this.amountInCents = amountInCents;
        this.desc = desc;
        this.fileHashes = fileHashes;
        this.fileNames = fileNames;
        this.timestamp = timestamp;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
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
