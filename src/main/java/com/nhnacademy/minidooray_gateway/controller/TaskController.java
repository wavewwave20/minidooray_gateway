package com.nhnacademy.minidooray_gateway.controller;

import com.nhnacademy.minidooray_gateway.dto.task.ProjectCreateDto;
import com.nhnacademy.minidooray_gateway.dto.task.UserDto;
import com.nhnacademy.minidooray_gateway.service.task.ProjectService;
import com.nhnacademy.minidooray_gateway.service.task.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class TaskController {

    private final UserService userService;

    private final ProjectService projectService;

    public TaskController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/users")
    public ResponseEntity<String> getAllUsers() {
        String response = userService.getAllUsers();
        return ResponseEntity.status(200).header("Content-Type", "application/json").body(response);
    }

    @GetMapping("/users/{userUUID}")
    public ResponseEntity<String> getUserById(@PathVariable String userUUID) {
        String userDto = userService.getUserById(userUUID);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/prjoects")
    public ResponseEntity<?> createProject(@RequestBody ProjectCreateDto projectCreateDto) {
        projectService.createProject(projectCreateDto);
        return ResponseEntity.ok().build();
    }

}
