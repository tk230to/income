package com.incom.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品リポジトリクラス。
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

}
