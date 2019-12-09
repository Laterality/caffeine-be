package com.woowacourse.caffeine.controller;

public class ShopNotificationSendRequest {
    private long shopId;
    private String message;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
