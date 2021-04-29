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

import com.incom.model.CartItem;
import com.incom.model.CartItemRepository;

/**
 * カート商品コントローラクラス。
 */
@RestController
@RequestMapping("/api/open/cartitems")
public class CartItemRestController {

    /** カート商品リポジトリ */
    @Autowired
    private CartItemRepository cartItemRepository;

    /**
     * 全件表示。
     *
     * @return カート情報
     */
    @GetMapping
    public ResponseEntity<List<CartItem>> get() {
        return ResponseEntity.ok(cartItemRepository.findAll());
    }

    /**
     * 1件表示。
     *
     * @param id カートID
     * @return カート情報
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<CartItem> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cartItemRepository.findById(id).orElse(new CartItem()));
    }

    /**
     * 登録。
     *
     * @param Cart カート画面入力値
     * @return カート情報
     * @throws IOException
     */
    @PostMapping
    public ResponseEntity<CartItem> create(@Valid @RequestBody CartItem cartItem) {
        cartItemRepository.save(cartItem);
        return ResponseEntity.ok(cartItem);
    }

    /**
     * 更新。
     *
     * @param Cart カート画面入力値
     * @return カート情報
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<CartItem> update(@Validated @RequestBody CartItem cartItem) {
        cartItemRepository.save(cartItem);
        return ResponseEntity.ok(cartItem);
    }

    /**
     * 削除。
     *
     * @param id カートID
     * @return 204:No Content
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> list(@PathVariable("id") Long id) {
        cartItemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
