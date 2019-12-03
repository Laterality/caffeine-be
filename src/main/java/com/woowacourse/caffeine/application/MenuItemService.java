package com.woowacourse.caffeine.application;

import com.woowacourse.caffeine.domain.MenuItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuItemService {

    private final MenuItemInternalService menuItemInternalService;

    public MenuItemService(MenuItemInternalService menuItemInternalService) {
        this.menuItemInternalService = menuItemInternalService;
    }

    public List<MenuItemResponse> findByShopId(long shopId) {
        return menuItemInternalService.findByShopId(shopId)
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
