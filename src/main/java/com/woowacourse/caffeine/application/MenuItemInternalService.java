package com.woowacourse.caffeine.application;

import com.woowacourse.caffeine.domain.Shop;
import com.woowacourse.caffeine.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class ShopInternalService {
    private final ShopRepository shopRepository;

    ShopInternalService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    Shop findById(long id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
    }
}
