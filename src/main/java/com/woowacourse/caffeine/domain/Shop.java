package com.woowacourse.caffeine.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "vendor")
    private List<MenuItem> menus = new ArrayList<>();

    protected Shop() {
    }

    public Shop(String name) {
        this.name = Objects.requireNonNull(name);

        if (name.isEmpty()) {
            throw new InvalidShopNameException(name);
        }
    }

    public void addMenu(MenuItem menu) {
        menus.add(menu);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
