package com.nhnacademy.minidooray_gateway.dto.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ProjectDto {

    public enum ProjectStatusEnum {
        ACTIVE, INACTIVE, COMPLETED;
    }

    private Long projectId;
    private String projectName;
    private String projectDescription;
    private ProjectStatusEnum projectStatus;
    private Long userId;

}
