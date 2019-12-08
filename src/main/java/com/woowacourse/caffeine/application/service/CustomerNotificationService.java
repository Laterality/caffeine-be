package com.woowacourse.caffeine.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerNotificationService.class);

    private Map<String, ResponseBodyEmitter> subscriptions = new HashMap<>();

    public ResponseBodyEmitter subscribe(final String customerId) {
        ResponseBodyEmitter emitter = new SseEmitter();
        emitter.onCompletion(remove(customerId));
        emitter.onTimeout(remove(customerId));
        emitter.onError((e) -> {
            logger.error("Error in event emitter", e);
            subscriptions.remove(customerId);
        });

        subscriptions.put(customerId, emitter);
        return emitter;
    }

    private Runnable remove(final String id) {
        return () -> subscriptions.remove(id);
    }

    public void send(final String shopId, final String message) {
        try {
            subscriptions.get(shopId).send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
