package com.nhnacademy.minidooray_gateway.controller;


import com.nhnacademy.minidooray_gateway.dto.account.UserLoginDto;
import com.nhnacademy.minidooray_gateway.dto.account.UserRegisterDto;
import com.nhnacademy.minidooray_gateway.service.AccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;

    @GetMapping("/auth/login")
    public String getLogin(UserLoginDto userLoginDto, Model model) {
        model.addAttribute("userLoginDto", userLoginDto);
        return "login";
    }

//    @PostMapping("/auth/login")
//    public String postLogin(@RequestBody UserLoginDto userLoginDto) {
//        accountService.login(userLoginDto);
//        return "redirect:/";
//    }

    @GetMapping("/auth/logout")
    public void getLogout() {
        accountService.logout();
    }

    @GetMapping("/auth/register")
    public String getRegister(UserRegisterDto userRegisterDto, Model model) {
        model.addAttribute("userRegisterDto", userRegisterDto);
        return "register";
    }

    @PostMapping("/auth/register")
    public String postRegister(@RequestBody UserRegisterDto userRegisterDto) {
        accountService.register(userRegisterDto);
        return "redirect:/";
    }



}
