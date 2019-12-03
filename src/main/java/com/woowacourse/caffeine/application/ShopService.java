package com.woowacourse.caffeine.application;

import com.woowacourse.caffeine.domain.Shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ShopService {

    private final ShopInternalService shopInternalService;

    public ShopService(ShopInternalService shopInternalService) {
        this.shopInternalService = shopInternalService;
    }

    public ShopResponse createShop(ShopCreateRequest request) {
        Shop shop = shopInternalService.createShop(request);
        return new ShopResponse(shop.getId(), shop.getName());
    }

    @Transactional(readOnly = true)
    public ShopResponse findById(long id) {
        Shop shop = shopInternalService.findById(id);
        return new ShopResponse(shop.getId(), shop.getName());
    }
}
