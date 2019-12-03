package com.woowacourse.caffeine.domain;

public class InvalidMenuItemPriceException extends RuntimeException {
    public InvalidMenuItemPriceException(int actual) {
        super(String.format("올바르지 않은 가격입니다: %d", actual));
    }
}
