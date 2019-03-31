package com.garvey.property.model;

import org.web3j.crypto.Credentials;

public class User {
    private String encryptedPwd;
    private String encryptedPhone;
    private String nickName;
    private int authority;
    private Credentials credentials;

    public User() {

    }

    public User(String encryptedPwd, String encryptedPhone, String nickName, int authority) {
        this.encryptedPwd = encryptedPwd;
        this.encryptedPhone = encryptedPhone;
        this.nickName = nickName;
        this.authority = authority;
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
}