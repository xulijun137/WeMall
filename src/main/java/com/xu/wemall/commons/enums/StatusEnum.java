package com.xu.wemall.commons.enums;

public enum StatusEnum {

    ON_LINE("已上线"),
    OFF_LINE("待上线");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;

    }

}
