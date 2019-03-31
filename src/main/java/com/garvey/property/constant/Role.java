package com.garvey.property.constant;

/**
 * @author GarveyWong
 * @date 2019/3/27
 */
public enum Role {
    AnonymousUser(0, "匿名用户"),
    Manager(1, "管理员"),
    Property(2, "物业"),
    Proprietor(3, "业主");
    private int value;
    private String desc;

    Role(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
