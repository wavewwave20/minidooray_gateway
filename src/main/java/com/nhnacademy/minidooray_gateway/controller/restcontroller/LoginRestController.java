package com.nhnacademy.minidooray_gateway.controller.restcontroller;


import com.nhnacademy.minidooray_gateway.dto.account.UserLoginDto;
import com.nhnacademy.minidooray_gateway.service.AccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController-로그인 http 요청 처리
 * TODO: 로그인 처리를 위한 RestController-API테스트 및 추가
 */


@Getter
@Setter
@RequiredArgsConstructor
@RestController
public class LoginRestController {

    private final AccountService accountService;

//    @PostMapping("/auth/login")
//    public String postApiLogin(@RequestBody UserLoginDto userLoginDto) {
//        accountService.normalLogin(userLoginDto);
//        return "redirect:/";
//    }

}

