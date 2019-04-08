package com.garvey.property.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.garvey.property.constant.Authority;
import org.web3j.crypto.Credentials;

public class User {
    private String address;
    private String encryptedPwd;
    private String encryptedPhone;
    private String nickName;
    private int authority;
    @JsonIgnore
    private Credentials credentials;

    public User() {

    }

    public User(String address, String encryptedPwd, String encryptedPhone, String nickName, int authority) {
        this.address = address;
        this.encryptedPwd = encryptedPwd;
        this.encryptedPhone = encryptedPhone;
        this.nickName = nickName;
        this.authority = authority;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEncryptedPwd() {
        return encryptedPwd;
    }

    public void setEncryptedPwd(String encryptedPwd) {
        this.encryptedPwd = encryptedPwd;
    }

    public String getEncryptedPhone() {
        return encryptedPhone;
    }

    public void setEncryptedPhone(String encryptedPhone) {
        this.encryptedPhone = encryptedPhone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public boolean canPublishInfo() {
        return Authority.contain(authority, Authority.PUBLISH_ANNOUNCEMENT);
    }

    public boolean canPublishFund() {
        return Authority.contain(authority, Authority.PUBLISH_FUND);
    }

    public boolean canPublishDiscussion() {
        return Authority.contain(authority, Authority.PUBLISH_DISCUSSION);
    }

    public boolean canPublishMotion() {
        return Authority.contain(authority, Authority.PUBLISH_MOTION);
    }

    public boolean canDeleteDiscussion() {
        return Authority.contain(authority, Authority.DELETE_DISCUSSION);
    }

    public boolean canAddProprietorAccount() {
        return Authority.contain(authority, Authority.ADD_PROPRIETOR_ACCOUNT);
    }

    public boolean canAddPropertyAccount() {
        return Authority.contain(authority, Authority.ADD_PROPERTY_ACCOUNT);
    }
}