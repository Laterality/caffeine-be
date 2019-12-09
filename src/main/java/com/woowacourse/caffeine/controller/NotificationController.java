package com.woowacourse.caffeine.controller;

import com.woowacourse.caffeine.application.service.CustomerNotificationService;
import com.woowacourse.caffeine.application.service.ShopNotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Controller
public class NotificationController {

    private final ShopNotificationService shopNotificationService;
    private final CustomerNotificationService customerNotificationService;

    public NotificationController(final ShopNotificationService shopNotificationService, final CustomerNotificationService customerNotificationService) {
        this.shopNotificationService = shopNotificationService;
        this.customerNotificationService = customerNotificationService;
    }

    @GetMapping("/notification/subscribe/shop")
    public ResponseBodyEmitter subscribeShopNotification(final long shopId) {
        return shopNotificationService.subscribe(shopId);
    }

    @GetMapping("/notification/subscribe/customer")
    public ResponseBodyEmitter subscribeShopNotification(final String customerId) {
        return customerNotificationService.subscribe(customerId);
    }
}
