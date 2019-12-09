package com.woowacourse.caffeine.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Service
public class ShopNotificationService {

    private NotificationService<Long> notificationService = new NotificationService<>();

    public ResponseBodyEmitter subscribe(final long shopId) {
        return notificationService.subscribe(shopId);
    }

    public void send(final long shopId, final String message) {
        notificationService.send(shopId, message);
    }
}
