package com.nhnacademy.minidooray_gateway.controller;


import com.nhnacademy.minidooray_gateway.dto.task.ProjectDto;
import com.nhnacademy.minidooray_gateway.service.DoorayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dooray")
@RequiredArgsConstructor
public class BoardController {
    private final DoorayService doorayService;

    @GetMapping("/board")
    public String getBoardList(ProjectDto projectDto) {
        doorayService.getProjectList(projectDto);
        return "board";
    }

    @PostMapping("/board")
    public String postBoardList(ProjectDto projectDto) {
        doorayService.getProjectList(projectDto);
        return "board";
    }


}


