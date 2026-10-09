package com.huawei.olympics.controller.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateSportModel(
        @NotNull(message = "Name can not be null") @NotEmpty(message = "Name can not be empty") String name
) {
}
