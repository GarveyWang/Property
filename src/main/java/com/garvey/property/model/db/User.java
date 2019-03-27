package com.garvey.property.model.db;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private String cellphone;

    private String realname;

    private String nickname;

    private String md5password;

    private String md5id;

    private Integer role;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getMd5password() {
        return md5password;
    }

    public void setMd5password(String md5password) {
        this.md5password = md5password == null ? null : md5password.trim();
    }

    public String getMd5id() {
        return md5id;
    }

    public void setMd5id(String md5id) {
        this.md5id = md5id == null ? null : md5id.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCellphone() == null ? other.getCellphone() == null : this.getCellphone().equals(other.getCellphone()))
                && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
                && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
                && (this.getMd5password() == null ? other.getMd5password() == null : this.getMd5password().equals(other.getMd5password()))
                && (this.getMd5id() == null ? other.getMd5id() == null : this.getMd5id().equals(other.getMd5id()))
                && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCellphone() == null) ? 0 : getCellphone().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getMd5password() == null) ? 0 : getMd5password().hashCode());
        result = prime * result + ((getMd5id() == null) ? 0 : getMd5id().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        return result;
    }
}