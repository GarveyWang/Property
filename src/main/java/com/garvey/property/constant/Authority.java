package com.garvey.property.constant;

/**
 * @author GarveyWong
 * @date 2019/3/29
 */
public enum Authority {
    BASIC_READ(1, "基本读权限"),

    PUBLISH_DISCUSSION(2, "发布讨论权限"),
    PARTICIPATE_DISCUSSION(4, "参与讨论权限"),
    DELETE_DISCUSSION(8, "删除讨论权限"),

    PUBLISH_PROPOSAL(16, "发布提议权限"),
    VOTE_PROPOSAL(32, "表决提议权限"),
    DELETE_PROPOSAL(64, "删除提议权限"),

    PUBLISH_ANNOUNCEMENT(128, "发布公告权限"),
    PUBLISH_FUND(256, "发布资金权限"),

    ADD_PROPRIETOR_ACCOUNT(512, "添加业主账号权限"),
    ADD_PROPERTY_ACCOUNT(1024, "添加物业账号权限"),

    SUPER(2048, "超级管理员权限");

    int value;
    String desc;

    Authority(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public boolean hasAuthority(int authority) {
        return (authority & value) > 0;
    }

    public static int getDefaultProprietorAuthValue() {
        return BASIC_READ.getValue()
                + PUBLISH_DISCUSSION.getValue() + PARTICIPATE_DISCUSSION.getValue()
                + VOTE_PROPOSAL.getValue();
    }

    public static int getDefaultPropertyAuthValue() {
        return BASIC_READ.getValue()
                + PUBLISH_DISCUSSION.getValue() + PARTICIPATE_DISCUSSION.getValue() + DELETE_DISCUSSION.getValue()
                + PUBLISH_PROPOSAL.getValue() + VOTE_PROPOSAL.getValue() + DELETE_PROPOSAL.getValue()
                + PUBLISH_ANNOUNCEMENT.getValue() + PUBLISH_FUND.getValue()
                + ADD_PROPRIETOR_ACCOUNT.getValue();
    }

    public static boolean contain(int auth, Authority... authorities) {
        for (Authority authority : authorities) {
            if ((authority.getValue() & auth) == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean containArray(int auth, Authority[] authorities) {
        for (Authority authority : authorities) {
            if ((authority.getValue() & auth) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //System.out.println(Authority.contain(2,BASIC_READ, PUBLISH_DISCUSSION));
        System.out.println(getDefaultProprietorAuthValue());
        System.out.println(getDefaultPropertyAuthValue());
    }
}
