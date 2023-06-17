package com.nhnacademy.minidooray_gateway.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class MilestoneCreateDto {
    private String milestoneName;
    private LocalDateTime milestoneStartDate;
    private LocalDateTime milestoneEndDate;
    private MilestoneStatusEnum milestoneStatus;
    private Long projectId;
}
