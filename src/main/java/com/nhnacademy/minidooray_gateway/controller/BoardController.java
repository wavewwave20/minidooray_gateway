package com.nhnacademy.minidooray_gateway.controller;


import com.nhnacademy.minidooray_gateway.dto.task.ProjectDto;
import com.nhnacademy.minidooray_gateway.service.DoorayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dooray")
@RequiredArgsConstructor
public class BoardController {
    private final DoorayService doorayService;

    /**
     * POST CRUD, BOARD 컨트롤러 작성
     */

    @GetMapping("/board")
    public String getBoardList(Model model) {

        model.addAttribute(doorayService.getAllProjectList());
        return "board";
    }

    @GetMapping("/board/{id}")
    public String getBoardDetail(@PathVariable Long id, Model model) {
        model.addAttribute("project", doorayService.getProjectDetail(id));
        return "board";
    }

    @PostMapping("/board")
    public String postBoardList(@RequestBody ProjectDto projectDto) {
        doorayService.saveProject(projectDto);
        return "board";
    }

    @PutMapping("/board/{id}")
    public String putBoardList(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        doorayService.updateProject(id, projectDto);
        return "board";
    }

    @DeleteMapping("/board/{id}")
    public String deleteBoardList(@PathVariable Long id) {
        doorayService.deleteProject(id);
        return "board";
    }


}


