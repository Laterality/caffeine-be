package com.woowacourse.caffeine.application.dto;

public class MenuItemResponse {

    private final long id;
    private final String name;
    private final String nameInEnglish;
    private final String description;
    private final int price;
    private final String imgUrl;
    private final String category;

    public MenuItemResponse(final long id, final String name, final String nameInEnglish, final String description, final int price, final String imgUrl, final String category) {
        this.id = id;
        this.name = name;
        this.nameInEnglish = nameInEnglish;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameInEnglish() {
        return nameInEnglish;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getCategory() {
        return category;
    }
}
