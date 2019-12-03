package com.woowacourse.caffeine.application;

import com.woowacourse.caffeine.domain.MenuItem;
import com.woowacourse.caffeine.domain.Shop;
import com.woowacourse.caffeine.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuItemInternalService {

    private final ShopInternalService shopInternalService;

    private final MenuItemRepository menuItemRepository;

    public MenuItemInternalService(ShopInternalService shopInternalService, MenuItemRepository menuItemRepository) {
        this.shopInternalService = shopInternalService;
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> findByShopId(long shopId) {
        Shop vendor = shopInternalService.findById(shopId);
        return menuItemRepository.findByVendor(vendor);
    }
}
