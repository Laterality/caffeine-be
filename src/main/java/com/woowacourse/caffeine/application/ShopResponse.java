package com.woowacourse.caffeine.application;

public class ShopResponse {
    public final long id;
    public final String name;

    public ShopResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
