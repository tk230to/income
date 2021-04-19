package com.incom.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incom.model.Item;
import com.incom.model.ItemRepository;

/**
 * 商品画面のコントローラクラス。
 */
@RestController
@RequestMapping("/api/open/items")
public class ItemRestController {

    /** 商品リポジトリ */
    @Autowired
    private ItemRepository ItemRepository;

    /**
     * 全件表示。
     *
     * @return 商品情報
     */
    @GetMapping
    public ResponseEntity<List<Item>> get() {
        return ResponseEntity.ok(ItemRepository.findAll());
    }

    /**
     * 1件表示。
     *
     * @param id 商品ID
     * @return 商品情報
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ItemRepository.findById(id).orElse(new Item()));
    }

    /**
     * 登録。
     *
     * @param Item 商品画面入力値
     * @return 商品情報
     * @throws IOException
     */
    @PostMapping
    public ResponseEntity<Item> create(@Valid @RequestBody Item item) {
        ItemRepository.save(item);
        return ResponseEntity.ok(item);
    }

    /**
     * 更新。
     *
     * @param Item 商品画面入力値
     * @return 商品情報
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Item> update(@Validated @RequestBody Item Item) {
        ItemRepository.save(Item);
        return ResponseEntity.ok(Item);
    }

    /**
     * 削除。
     *
     * @param id 商品ID
     * @return 204:No Content
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> list(@PathVariable("id") Long id) {
        ItemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
