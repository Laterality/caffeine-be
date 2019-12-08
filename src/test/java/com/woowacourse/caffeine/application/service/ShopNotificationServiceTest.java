package com.woowacourse.caffeine.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ShopNotificationServiceTest {

    private ShopNotificationService shopNotificationService;

    @BeforeEach
    void setUp() {
        shopNotificationService = new ShopNotificationService();
    }

    @Test
    void subscribe() {
        assertDoesNotThrow(() -> assertThat(shopNotificationService.subscribe(1)).isNotNull());
    }

    @Test
    void send() {
        shopNotificationService.subscribe(1);
        assertDoesNotThrow(() -> shopNotificationService.send(1, "{\n" +
                "    \"topic\": \"orderArrived\",\n" +
                "    \"data\": {\n" +
                "        \"id\": 1,\n" +
                "        \"orderItems\": [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"menuItemId\": 1,\n" +
                "                \"quantity\": 1,\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 2,\n" +
                "                \"menuItemId\": 2,\n" +
                "                \"quantity\": 1\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}"));
    }
}
