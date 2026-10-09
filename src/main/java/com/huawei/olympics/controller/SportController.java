package com.huawei.olympics.controller;

import com.huawei.olympics.controller.model.CreateSportModel;
import com.huawei.olympics.entity.Sport;
import com.huawei.olympics.service.SportService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/sport")
public class SportController {
    private final SportService service;

    public SportController(SportService service) {
        this.service = service;
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<Sport>> getRanking() {
        return ResponseEntity.ok(service.getRanking());
    }

    @PostMapping
    public ResponseEntity<Sport> create(@Valid CreateSportModel model) {
        return ResponseEntity.status(201).body(service.create(model.name()));
    }
}
