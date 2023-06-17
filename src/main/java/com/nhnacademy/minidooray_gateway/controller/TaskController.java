package com.nhnacademy.minidooray_gateway.controller;

import com.nhnacademy.minidooray_gateway.dto.task.*;
import com.nhnacademy.minidooray_gateway.service.task.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskController {

    private final UserService userService;

    private final ProjectService projectService;

    private final CommentService commentService;
    private final MilestoneService milestoneService;
    private final TagService tagService;
    private final TaskService taskService;
//    public TaskController(UserService userService, ProjectService projectService) {
//        this.userService = userService;
//        this.projectService = projectService;
//    }

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

    @PostMapping("/projects")
    public ResponseEntity<?> createProject(@RequestBody ProjectCreateDto projectCreateDto) {
        projectService.createProject(projectCreateDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comments")
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        commentService.updateCommentById(commentId, commentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<String> getCommentById(@PathVariable Long commentId) {
        String commentDto = commentService.getCommentById(commentId);
        return ResponseEntity.ok(commentDto);
    }

    @GetMapping("/comments/tasks/{taskId}")
    public ResponseEntity<String> getCommentByTaskId(@PathVariable Long taskId) {
        String commentDto = commentService.getCommentByTaskId(taskId);
        return ResponseEntity.ok(commentDto);
    }

    @PostMapping("/milestones")
    public ResponseEntity<?> createMilestone(@RequestBody MilestoneCreateDto milestoneCreateDto) {
        milestoneService.createMilestone(milestoneCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/milestones/{milestoneId}")
    public ResponseEntity<String> getMilestoneById(@PathVariable Long milestoneId) {
        String milestoneDto = milestoneService.getMilestoneById(milestoneId);
        return ResponseEntity.ok(milestoneDto);
    }

    @GetMapping("/milestones/projects/{projectId}")
    public ResponseEntity<String> getMilestoneByProjectId(@PathVariable Long projectId) {
        String milestoneDto = milestoneService.getMilestoneByProjectId(projectId);
        return ResponseEntity.ok(milestoneDto);
    }

    @PutMapping("/milestones/{milestoneId}")
    public ResponseEntity<?> updateMilestone(@PathVariable Long milestoneId, @RequestBody MilestoneCreateDto milestoneCreateDto) {
        milestoneService.updateMilestoneById(milestoneId, milestoneCreateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/milestones/{milestoneId}")
    public ResponseEntity<?> deleteMilestone(@PathVariable Long milestoneId) {
        milestoneService.deleteMilestoneById(milestoneId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/milesstones/{milestoneId}/tasks/{taskId}")
    public ResponseEntity<?> createMilestoneTask(@PathVariable Long milestoneId, @PathVariable Long taskId) {
        milestoneService.createMilestoneTask(milestoneId, taskId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/milesstones/{milestoneId}/tasks/{taskId}")
    public ResponseEntity<?> deleteMilestoneTask(@PathVariable Long milestoneId, @PathVariable Long taskId) {
        milestoneService.deleteMilestoneTask(milestoneId, taskId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("milestones/{milestoneId}/tasks")
    public ResponseEntity<String> getMilestoneTask(@PathVariable Long milestoneId) {
        String taskDto = milestoneService.getMilestoneByTaskId(milestoneId);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("milestones/{milestoneId}/tasks/{taskId}")
    public ResponseEntity<String> getTaskByMilestoneId(@PathVariable Long milestoneId) {
        String taskDto = milestoneService.getTasksByMilestoneId(milestoneId);
        return ResponseEntity.ok(taskDto);
    }


    @GetMapping("/projects/{projectId}")
    public ResponseEntity<String> getProjectById(@PathVariable Long projectId) {
        String projectDto = projectService.getProjectById(projectId);
        return ResponseEntity.ok(projectDto);
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable Long projectId, @RequestBody ProjectDto projectDto) {
        projectService.updateProjectById(projectId, projectDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProjectById(projectId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/projects/{projectId}/users/{userUUID}")
    public ResponseEntity<?> createProjectUser(@PathVariable Long projectId, @PathVariable String userUUID) {
        projectService.createProjectUser(projectId, userUUID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/projects/{projectId}/users/{userUUID}")
    public ResponseEntity<?> deleteProjectUser(@PathVariable Long projectId, @PathVariable String userUUID) {
        projectService.deleteProjectUser(projectId, userUUID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/projects/{projectId}/users")
    public ResponseEntity<?> deleteProjectUsers(@PathVariable Long projectId) {
        projectService.deleteProjectUserByProjectId(projectId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/projects/{projectId}/users")
    public ResponseEntity<String> getProjectUsers(@PathVariable Long projectId) {
        String userDto = projectService.getProjectUserAll(projectId);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/tags")
    public ResponseEntity<?> createTag(@RequestBody TagCreateDto tagCreateDto) {
        tagService.createTag(tagCreateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTagById(tagId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/tags/{tagId}")
    public ResponseEntity<?> updateTag(@PathVariable Long tagId, @RequestBody TagCreateDto tagCreateDto) {
        tagService.updateTagById(tagId, tagCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<?> getTagById(@PathVariable Long tagId) {
        String tagDto = tagService.getTagById(tagId);
        return ResponseEntity.ok(tagDto);
    }

    @GetMapping("/tags/{projectId}/tags")
    public ResponseEntity<?> getTagByProjectId(@PathVariable Long projectId) {
        String tagDto = tagService.getTagByProjectId(projectId);
        return ResponseEntity.ok(tagDto);
    }

    @GetMapping("/tags/{tagId}/tasks")
    public ResponseEntity<?> getTaskByTagId(@PathVariable Long tagId) {
        String taskDto = tagService.getTasksByTag(tagId);
        return ResponseEntity.ok(taskDto);
    }


    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody TaskCreateDto taskCreateDto) {
        taskService.createTask(taskCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Long taskId) {
        String taskDto = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/tasks/{projectId}/tasks")
    public ResponseEntity<?> getTaskByProjectId(@PathVariable Long projectId) {
        String taskDto = taskService.getTaskByProjectId(projectId);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/tasks/{userUUId}/admin/tasks")
    public ResponseEntity<?> getTaskByUserUUId(@PathVariable String userUUId) {
        String taskDto = taskService.getTaskByAdminUserUUID(userUUId);
        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        taskService.updateTaskById(taskId, taskDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTaskById(taskId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tasks/{taskId}/tags/{tagId}")
    public ResponseEntity<?> createTaskTag(@PathVariable Long taskId, @PathVariable Long tagId) {
        taskService.createTaskTag(taskId, tagId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tags/{tagId}/tasks")
    public ResponseEntity<?> getTasksByTagId(@PathVariable Long tagId) {
        String taskDto = taskService.getTasksByTag(tagId);
        return ResponseEntity.ok(taskDto);
    }

    @DeleteMapping("/tasks/{taskId}/tags/{tagId}")
    public ResponseEntity<?> deleteTaskTag(@PathVariable Long taskId, @PathVariable Long tagId) {
        taskService.deleteTaskTag(taskId, tagId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tasks/{taskId}/users/{userUUID}")
    public ResponseEntity<?> createTaskUser(@PathVariable Long taskId, @PathVariable String userUUID) {
        taskService.createUserTask(taskId, userUUID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tasks/{taskId}/users")
    public ResponseEntity<?> getTaskUser(@PathVariable Long taskId) {
        String userDto = taskService.getUserTaskByTaskId(taskId);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/tasks/{taskId}/users/{userUUID}")
    public ResponseEntity<?> deleteTaskUser(@PathVariable Long taskId, @PathVariable String userUUID) {
        taskService.deleteUserTask(taskId, userUUID);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto userCreateDto) {
        userService.createUser(userCreateDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserCreateDto userCreateDto) {
        userService.updateUserById(userId, userCreateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/users/{userUUID}/projects")
    public ResponseEntity<?> getAllProjectsByUserUUID(@PathVariable String userUUID) {
        String projectDto = userService.getAllProjects(userUUID);
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping("/tasks/taskId}/users")
    public ResponseEntity<?> getAllUsersByTaskId(@PathVariable Long taskId) {
        String userDto = userService.StringgetUserTaskByTaskId(taskId);
        return ResponseEntity.ok(userDto);
    }

}
