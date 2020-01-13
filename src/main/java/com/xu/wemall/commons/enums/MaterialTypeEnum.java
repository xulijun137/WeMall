package com.xu.wemall.commons.enums;

public enum MaterialTypeEnum {

    IMAGE("image"),
    VIDEO("video"),
    VOICE("voice"),
    NEWS("news");

    private String value;

    MaterialTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;

    }

}
