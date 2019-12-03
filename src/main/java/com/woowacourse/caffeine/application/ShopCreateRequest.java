package com.woowacourse.caffeine.application;

public class ShopCreateRequest {
    private String name;

    public ShopCreateRequest() {
    }

    public ShopCreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
