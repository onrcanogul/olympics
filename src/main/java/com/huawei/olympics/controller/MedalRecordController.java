package com.huawei.olympics.controller;

import com.huawei.olympics.controller.model.CreateMedalRecordModel;
import com.huawei.olympics.entity.MedalRecord;
import com.huawei.olympics.service.MedalRecordService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/medal-record")
public class MedalRecordController {
    private final MedalRecordService service;

    public MedalRecordController(MedalRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MedalRecord> create(@Valid CreateMedalRecordModel model) {
        return ResponseEntity.status(201).body(service.create(model.medalTypes(), model.athleteId(), model.sportId()));
    }
}
