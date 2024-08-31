package com.example.short_chains.pojo;

/**
 * @author plumsun on 2024/8/31
 */
public enum ConstantEnum {

    SORT_CHAIN_KEY("short_chains:sort_chain");

    private final String value;

    ConstantEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
