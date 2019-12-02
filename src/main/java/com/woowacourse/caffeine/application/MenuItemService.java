package com.woowacourse.caffeine.application;

import com.woowacourse.caffeine.domain.MenuItem;
import com.woowacourse.caffeine.domain.Shop;
import com.woowacourse.caffeine.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuItemService {

    private final ShopInternalService shopService;

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(ShopInternalService shopService, MenuItemRepository menuItemRepository) {
        this.shopService = shopService;
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItemResponse> findByShopId(long shopId) {
        Shop vendor = shopService.findById(shopId);
        return menuItemRepository.findByVendor(vendor)
        .stream()
        .map(this::convertToResponse)
        .collect(Collectors.toList());
    }

    private MenuItemResponse convertToResponse(MenuItem menuItem) {
        return new MenuItemResponse(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice()
        );
    }
}
