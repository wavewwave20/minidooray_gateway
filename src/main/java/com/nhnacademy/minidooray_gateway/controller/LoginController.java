package com.nhnacademy.minidooray_gateway.controller;


import com.nhnacademy.minidooray_gateway.dto.account.UserLoginDto;
import com.nhnacademy.minidooray_gateway.service.AccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;

    @GetMapping("/auth/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/auth/login")
    public String postLogin(@RequestBody UserLoginDto userLoginDto) {
        accountService.normalLogin(userLoginDto);
        return "redirect:/";
    }


}
