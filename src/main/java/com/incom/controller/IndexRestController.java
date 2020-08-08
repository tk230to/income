package com.incom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;
import com.github.mkopylec.recaptcha.validation.ValidationResult;
import com.incom.config.Roles;

/**
 * トップのRestコントローラクラス。
 */
@RestController
@RequestMapping("/")
public class IndexRestController {

    @Autowired
    private RecaptchaValidator recaptchaValidator;

    @PostMapping("/validate/recaptcha")
    public String validateRecaptcha(HttpServletRequest request) {
        ValidationResult result = recaptchaValidator.validate(request);
        if (result.isSuccess()) {
            return "成功";
        }
        return "失敗";
    }

    @GetMapping("/api/client/test")
    @Secured(value = Roles.ROLE_USER)
    public String test() {
        return "OK";
    }
}
