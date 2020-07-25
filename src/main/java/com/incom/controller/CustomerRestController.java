package com.incom.controller;

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

import com.incom.model.Customer;
import com.incom.model.CustomerRepository;

/**
 * 顧客画面のコントローラクラス。
 */
@RestController
@RequestMapping("/customers")
public class CustomerRestController {

  /** 顧客リポジトリ */
  @Autowired
  private CustomerRepository customerRepository;

  /**
   * 1件表示。
   *
   * @param id 顧客ID
   * @return 顧客情報
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<Customer> getOne(@PathVariable("id") Long id) {
    return ResponseEntity.ok(customerRepository.getOne(id));
  }

  /**
   * 登録。
   *
   * @param customer 顧客画面入力値
   * @return 顧客情報
   */
  @PostMapping(value = "/")
  public ResponseEntity<Customer> create(@Validated @RequestBody Customer customer) {
    customerRepository.save(customer);
    return ResponseEntity.ok(customer);
  }

  /**
   * 更新。
   *
   * @param customer 顧客画面入力値
   * @return 顧客情報
   */
  @PutMapping(value = "/{id}")
  public ResponseEntity<Customer> update(@Validated @RequestBody Customer customer) {
    customerRepository.save(customer);
    return ResponseEntity.ok(customer);
  }

  /**
   * 削除。
   *
   * @param id 顧客ID
   * @return 204:No Content
   */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> list(@PathVariable("id") Long id) {
    customerRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
