package com.nhnacademy.minidooray_gateway.controller;

import com.nhnacademy.minidooray_gateway.dto.account.UserDataSearchDto;
import com.nhnacademy.minidooray_gateway.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDataBoardController {
    private final UserDataService userDataService;

    @GetMapping("/board")
    public String getAllUserData(Model model) {
        model.addAttribute("userDataList", userDataService.getAllUserData());
        return "userlist";
    }

    @GetMapping("/board/{userId}")
    public String getUserDataDetail(@PathVariable String userId, Model model) {
        UserDataSearchDto userDataSearchDto = userDataService.getUserDataDetail(userId);
        model.addAttribute("userData", userDataSearchDto );
        return "userdetail";
    }

    @PutMapping("/board/{userId}")
    public String putUserData(@PathVariable String userId, @RequestBody UserDataSearchDto userDataSearchDto, Model model) {
        model.addAttribute("userData", userDataService.putUserData(userId, userDataSearchDto));
        return "userdetail";
    }

    @DeleteMapping("/board/{userId}")
    public String deleteUserData(@PathVariable String userId, Model model) {
        model.addAttribute("userData", userDataService.deleteUserData(userId));
        return "userdetail";
    }
}
