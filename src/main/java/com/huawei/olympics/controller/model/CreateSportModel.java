package com.huawei.olympics.controller.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateSportModel(
        @NotNull @NotEmpty String name
) {
}
