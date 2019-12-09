package com.woowacourse.caffeine.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class NotificationServiceTest {

    private NotificationService<Long> notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationService<>();
    }

    @Test
    void subscribe() {
        assertDoesNotThrow(() -> assertThat(notificationService.subscribe(1L)).isNotNull());
    }

    @Test
    void send() {
        notificationService.subscribe(1L);
        assertDoesNotThrow(() -> notificationService.send(1L, "{\n" +
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
