package com.incom.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 顧客クラス。
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    /** ID */
    @Id
    private String id;

    /** 名前 */
    @NotBlank
    private String name;
}
