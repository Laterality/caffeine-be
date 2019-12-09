package com.woowacourse.caffeine.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Service
public class CustomerNotificationService {

    private NotificationService<String> notificationService = new NotificationService<>();

    public ResponseBodyEmitter subscribe(final String customerId) {
        return notificationService.subscribe(customerId);
    }

    public void send(final String customerid, final String message) {
        notificationService.send(customerid, message);
    }
}
