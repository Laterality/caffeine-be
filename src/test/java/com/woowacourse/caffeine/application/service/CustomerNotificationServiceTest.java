package com.woowacourse.caffeine.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CustomerNotificationServiceTest {

    private CustomerNotificationService customerNotificationService;

    @BeforeEach
    void setUp() {
        customerNotificationService = new CustomerNotificationService();
    }

    @Test
    void subscribe() {
        assertDoesNotThrow(() -> assertThat(customerNotificationService.subscribe(UUID.randomUUID().toString())).isNotNull());
    }

    @Test
    void send() {
        String customerId = UUID.randomUUID().toString();
        customerNotificationService.subscribe(customerId);
        assertDoesNotThrow(() -> customerNotificationService.send(customerId, "{\n" +
                "    \"topic\": \"orderAccepted\",\n" +
                "    \"data\": {\n" +
                "        \"id\": 1,\n" +
                "        \"estimated\": \"5\"\n" +
                "    }\n" +
                "}"));
    }
}
