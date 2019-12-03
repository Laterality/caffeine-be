package com.woowacourse.caffeine.domain;

public class InvalidMenuItemNameException extends RuntimeException {
    public InvalidMenuItemNameException(String actual) {
        super(String.format("올바르지 않은 메뉴 이름입니다: %s", actual));
    }
}
