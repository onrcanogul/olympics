package com.huawei.olympics.controller;

import com.huawei.olympics.controller.model.CreateAthleteModel;
import com.huawei.olympics.entity.Athlete;
import com.huawei.olympics.service.AthleteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/athletes")
public class AthleteController {
    private final AthleteService service;

    public AthleteController(AthleteService service) {
        this.service = service;
    }


    @GetMapping("/ranking")
    public ResponseEntity<List<Athlete>> getRanking() {
        return ResponseEntity.ok(service.getRanking());
    }

    @PostMapping
    public ResponseEntity<Athlete> create(@Valid CreateAthleteModel model) {
        return ResponseEntity.status(201).body(service.create(model));
    }
}
