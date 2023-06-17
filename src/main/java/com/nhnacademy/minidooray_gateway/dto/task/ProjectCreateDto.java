package com.nhnacademy.minidooray_gateway.dto.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectCreateDto {

    private String projectName;

    private String projectDescription;

    private ProjectStatusEnum projectStatus;

    private String userUUID;
}
