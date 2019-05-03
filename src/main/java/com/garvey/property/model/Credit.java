package com.garvey.property.model;

import java.io.Serializable;

public class Credit implements Serializable {
    private String userId;

    private String userName;

    private Long credit;

    private static final long serialVersionUID = 1L;

    public Credit() {
    }

    public Credit(String userId, Long credit) {
        this.userId = userId;
        this.credit = credit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Credit other = (Credit) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCredit() == null ? other.getCredit() == null : this.getCredit().equals(other.getCredit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCredit() == null) ? 0 : getCredit().hashCode());
        return result;
    }
}