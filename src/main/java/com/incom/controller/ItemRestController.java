package com.incom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incom.model.Item;
import com.incom.model.ItemRepository;

/**
 * 商品画面のコントローラクラス。
 */
@RestController
@RequestMapping("/api/open/items")
public class ItemRestController extends BaseRestController<ItemRepository, Item, Long> {

    @Autowired
    public ItemRestController(ItemRepository respository) {
        super(respository);
    }
}
