package com.woowacourse.caffeine.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int price;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop vendor;

    protected MenuItem() {
    }

    public MenuItem(String name, String description, int price, Shop vendor) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.vendor = vendor;

        if (name.isEmpty()) {
            throw new InvalidMenuItemNameException(name);
        }

        if (price < 0) {
            throw new InvalidMenuItemPriceException(price);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
