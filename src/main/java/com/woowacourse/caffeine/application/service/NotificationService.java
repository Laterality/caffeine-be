package com.woowacourse.caffeine.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class NotificationService<T> {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private Map<T, ResponseBodyEmitter> subscriptions = new HashMap<>();
    private Map<T, Queue<String>> pendingMessages = new HashMap<>();

    public ResponseBodyEmitter subscribe(final T id) {
        ResponseBodyEmitter emitter = new SseEmitter();
        emitter.onCompletion(remove(id));
        emitter.onTimeout(remove(id));
        emitter.onError((e) -> {
            logger.error("Error in event emitter", e);
            subscriptions.remove(id);
        });

        subscriptions.put(id, emitter);

        if (pendingMessages.get(id) != null) {
            pendingMessages.get(id).forEach(m -> send(id, m));
            pendingMessages.get(id).clear();
        }
        return emitter;
    }

    private Runnable remove(final T id) {
        return () -> subscriptions.remove(id);
    }

    public void send(final T id, final String message) {
        try {
            ResponseBodyEmitter emitter = subscriptions.get(id);
            if (putPendingIfNotSubscribing(id, message, emitter)) return;
            subscriptions.get(id).send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean putPendingIfNotSubscribing(final T id, final String message, final ResponseBodyEmitter emitter) {
        if (emitter == null) {
            pendingMessages.computeIfAbsent(id, k -> new LinkedList<>());
            pendingMessages.get(id).add(message);
            return true;
        }
        return false;
    }
}
