package com.woowacourse.caffeine.domain;

public class InvalidShopNameException extends RuntimeException {


    public InvalidShopNameException(String actualName) {
        super(String.format("올바르지 않은 매장 이름입니다: %s", actualName));
    }
}
