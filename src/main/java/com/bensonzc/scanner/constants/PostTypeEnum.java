package com.bensonzc.scanner.constants;

/**
 * Created by zhangchen on 16/5/18.
 */
public enum PostTypeEnum {
    SALE("[出让]"),BUY("[求购]"),FAST_TRADE("[快速交易]");

    private String alias;

    private PostTypeEnum(String alias){
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
