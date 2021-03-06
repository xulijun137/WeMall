package com.xu.wemall.commons.enums;

public enum AppTypeEnum {

    UNKNOW(0, "未知"),
    WEB(2, "Web应用"),
    FTP(7, "ftp应用"),
    SMTP(8, "Smtp应用"),
    POP3(9, "Pop应用"),
    IMAP(21, "Imap应用"),
    SMB(20, "Samba应用"),
    DNS(30, "DNS应用");

    private int key;
    private String value;

    AppTypeEnum(int key) {
        this.key = key;
    }

    AppTypeEnum(int key,String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return this.key;
    }

    public void setKey(Integer key){
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value){
        this.value =value;

    }

    public static Integer getKey(Integer key) {
        AppTypeEnum[] enums = values();
        for (AppTypeEnum enumTemp : enums) {
            if (enumTemp.getKey().equals(key)) {
                return enumTemp.getKey();
            }
        }
        return null;
    }

    public static String getValue(Integer key) {
        AppTypeEnum[] enums = values();
        for (AppTypeEnum enumTemp : enums) {
            if (enumTemp.getKey().equals(key)) {
                return enumTemp.getValue();
            }
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println(AppTypeEnum.values()[new Integer(0)].getValue());
    }
}
