package com.nhnacademy.minidooray_gateway.service.task;

import com.nhnacademy.minidooray_gateway.dto.task.CommentDto;
import com.nhnacademy.minidooray_gateway.utils.RestTemplateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final RestTemplateUtils restTemplateUtils;

    public CommentService(RestTemplateUtils restTemplateUtils) {
        this.restTemplateUtils = restTemplateUtils;
        //Object response = restTemplateUtils.requestApi("task", "/projcets", "POST", projectCreateDto, 200, null);
    }

    public void createComment(CommentDto commentDto) {
        restTemplateUtils.requestApi("task", "/comments", "POST", commentDto, 200, null);
    }

    public void updateCommentById(Long commentId, CommentDto commentDto) {
        restTemplateUtils.requestApi("task", "/comments/" + commentId, "PUT", commentDto, 200, null);
    }

    public void deleteCommentById(Long commentId) {
        restTemplateUtils.requestApi("task", "/comments/" + commentId, "DELETE", null, 200, null);
    }

    public CommentDto getCommentById(Long commentId) {
        Object response = restTemplateUtils.requestApi("task", "/comments/" + commentId, "GET", null, 200, CommentDto.class);
        return (CommentDto) response;
    }

    public List<CommentDto> getCommentByTaskId(Long taskId) {
        Object response = restTemplateUtils.requestApi("task", "/comments/" + taskId + "/comments", "GET", null, 200, List.class);
        return (List<CommentDto>) response;
    }

}
