package com.nhnacademy.minidooray_gateway.service.task;

import com.nhnacademy.minidooray_gateway.dto.task.TagCreateDto;
import com.nhnacademy.minidooray_gateway.dto.task.TagDto;
import com.nhnacademy.minidooray_gateway.dto.task.TaskDto;
import com.nhnacademy.minidooray_gateway.utils.RestTemplateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final RestTemplateUtils restTemplateUtils;

    public TagService(RestTemplateUtils restTemplateUtils) {
        this.restTemplateUtils = restTemplateUtils;
    }

    public void createTag(TagCreateDto tagCreateDto) {
        Object response = restTemplateUtils.requestApi("task", "/tags", "POST", tagCreateDto, 200, null);
    }

    public void deleteTagById(Long tagId) {
        Object response = restTemplateUtils.requestApi("task", "/tags/" + tagId, "DELETE", null, 200, null);
    }

    public void updateTagById(Long tagId, TagCreateDto tagCreateDto) {
        Object response = restTemplateUtils.requestApi("task", "/tags/" + tagId, "PUT", tagCreateDto, 200, null);
    }

    public String getTagById(Long tagId) {
        String response = restTemplateUtils.requestApi("task", "/tags/" + tagId, "GET", null, 200, TagDto.class);
        return response;
    }

    public String getTagByProjectId(Long projectId) {
        String response = restTemplateUtils.requestApi("task", "/tags/" + projectId + "/tags", "GET", null, 200, List.class);
        return response;
    }

    public String getTasksByTag(Long tagId) {
        String response = restTemplateUtils.requestApi("task", "/tags/" + tagId + "/tasks", "GET", null, 200, List.class);
        return response;
    }
}
