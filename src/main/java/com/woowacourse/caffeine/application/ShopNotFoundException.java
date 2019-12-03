package com.woowacourse.caffeine.application;

public class ShopNotFoundException extends RuntimeException {

    public ShopNotFoundException(final long actual) {
        super(String.format("매장을 찾을 수 없습니다, ID: %d", actual));
    }
}
