package com.nhnacademy.minidooray_gateway.dto.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class MileStoneDto {

    public enum MilestoneStatusEnum {
        ACTIVE, INACTIVE, COMPLETED;
    }

    private Long milestoneId;
    private String milestoneName;
    private LocalDateTime milestoneStartDate;
    private LocalDateTime milestoneEndDate;
    private MilestoneStatusEnum milestoneStatus;
    private Long projectId;
}
