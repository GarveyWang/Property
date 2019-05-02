package com.garvey.property.model;

import com.garvey.property.constant.Authority;

/**
 * @author GarveyWong
 * @date 2019/4/28
 */
public class AuthOperation {
    private long idx;
    private boolean processing;
    private int authority;
    private String targetAddr;
    private String targetNickName;
    private int approvalCount;
    private int disapprovalCount;
    private int myVote;

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getTargetAddr() {
        return targetAddr;
    }

    public void setTargetAddr(String targetAddr) {
        this.targetAddr = targetAddr;
    }

    public String getTargetNickName() {
        return targetNickName;
    }

    public void setTargetNickName(String targetNickName) {
        this.targetNickName = targetNickName;
    }

    public int getApprovalCount() {
        return approvalCount;
    }

    public void setApprovalCount(int approvalCount) {
        this.approvalCount = approvalCount;
    }

    public int getDisapprovalCount() {
        return disapprovalCount;
    }

    public void setDisapprovalCount(int disapprovalCount) {
        this.disapprovalCount = disapprovalCount;
    }

    public int getMyVote() {
        return myVote;
    }

    public void setMyVote(int myVote) {
        this.myVote = myVote;
    }

    public String getAuthorityName(){
        return Authority.getDesc(authority);
    }
}
