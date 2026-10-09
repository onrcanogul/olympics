package com.huawei.olympics.controller.model;

import com.huawei.olympics.enumeration.MedalTypes;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateMedalRecordModel(
        @NotNull(message = "Sport can not be null") UUID sportId,
        @NotNull(message = "Athlete can not be null") UUID athleteId,
        @NotNull(message = "Medal Type can not be null") MedalTypes medalTypes
        ) {
}
