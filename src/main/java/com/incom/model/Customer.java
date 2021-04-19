package com.incom.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.incom.security.Role;

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

    /** 権限 */
    @Enumerated
    private Role role = Role.ROLE_USER;
}
