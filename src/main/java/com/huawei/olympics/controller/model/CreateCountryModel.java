package com.huawei.olympics.controller.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateCountryModel (
        @NotNull(message = "Name can not be null") @NotEmpty(message = "Name can not be empty") String name,
        @NotNull(message = "Code can not be null") @NotEmpty(message = "Code can not be empty") String code
) {
}
