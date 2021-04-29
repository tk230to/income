package com.incom.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * カート商品リポジトリクラス。
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
