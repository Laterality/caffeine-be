package com.woowacourse.caffeine.application;

public class MenuItemResponse {
    private final long id;
    private final String name;
    private final String description;
    private final int price;

    public MenuItemResponse(long id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
