package com.huawei.olympics.controller;

import com.huawei.olympics.controller.model.CreateCountryModel;
import com.huawei.olympics.entity.Country;
import com.huawei.olympics.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/country")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<Country>> getRanking() {
        return ResponseEntity.ok(service.getRanking());
    }

    @PostMapping
    public ResponseEntity<Country> create(@Valid @RequestBody CreateCountryModel model) {
        return ResponseEntity.status(201).body(service.create(model.name(), model.code()));
    }
}
