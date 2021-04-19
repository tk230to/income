package com.incom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品クラス。
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 名前 */
    @NotBlank
    @NotNull
    private String name;

    /** 金額 */
    @NotNull
    @Min(1)
    private Long price;

    /** 画像 */
    @Lob
    @NotNull
    private byte[] image;

    /** 画像種別 */
    private String imageType;
}
