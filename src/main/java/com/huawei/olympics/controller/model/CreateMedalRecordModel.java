package com.huawei.olympics.controller.model;

import com.huawei.olympics.enumeration.MedalTypes;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateMedalRecordModel(
        @NotNull UUID sportId,
        @NotNull UUID athleteId,
        @NotNull MedalTypes medalTypes
        ) {
}
