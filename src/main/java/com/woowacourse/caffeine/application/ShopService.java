package com.woowacourse.caffeine.application;

import com.woowacourse.caffeine.domain.Shop;
import com.woowacourse.caffeine.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public ShopResponse createShop(ShopCreateRequest request) {
        Shop shop = shopRepository.save(new Shop(request.getName()));
        return new ShopResponse(shop.getId(), shop.getName());
    }

    @Transactional(readOnly = true)
    public ShopResponse findById(long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
        return new ShopResponse(shop.getId(), shop.getName());
    }
}
