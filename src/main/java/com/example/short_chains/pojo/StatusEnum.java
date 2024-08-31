package com.example.short_chains.pojo;

/**
 * @author plumsun Created on 2023/12/3
 */
public enum StatusEnum {

    EFFECTIVE("1", "有效"),
    DELETE("0", "无效");

    private final String code;
    private final String info;

    StatusEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
