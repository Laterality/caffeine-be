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
public class ShopNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(ShopNotificationService.class);

    private Map<Long, ResponseBodyEmitter> subscriptions = new HashMap<>();

    public ResponseBodyEmitter subscribe(final long shopId) {
        ResponseBodyEmitter emitter = new SseEmitter();
        emitter.onCompletion(remove(shopId));
        emitter.onTimeout(remove(shopId));
        emitter.onError((e) -> {
            logger.error("Error in event emitter", e);
            subscriptions.remove(shopId);
        });

        subscriptions.put(shopId, emitter);
        return emitter;
    }

    private Runnable remove(final long id) {
        return () -> subscriptions.remove(id);
    }

    public void send(final long shopId, final String message) {
        try {
            subscriptions.get(shopId).send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
