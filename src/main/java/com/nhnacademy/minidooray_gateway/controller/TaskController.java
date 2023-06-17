package com.nhnacademy.minidooray_gateway.controller;

import com.nhnacademy.minidooray_gateway.dto.task.UserDto;
import com.nhnacademy.minidooray_gateway.service.task.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/dooray")
public class TaskController {

    private final UserService userService;

    public TaskController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }

}
