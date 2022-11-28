package com.api.systemaccount.account.enums;

public enum AccountType {

    CHECKING(1, "CHECKING"),
    SAVING(2, "SAVING");

    private int cod;
    private String desc;

    private AccountType(int code, String desc) {
        this.cod = code;
        this.desc = desc;
    }

    public int getCode() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }
}
