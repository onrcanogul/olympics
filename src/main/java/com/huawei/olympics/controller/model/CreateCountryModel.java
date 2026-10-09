package com.huawei.olympics.controller.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateCountryModel (
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String code
) {
}
