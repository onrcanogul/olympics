package com.huawei.olympics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sports")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "sport_id")
    private List<MedalRecord> records;

    public Sport(String name) {
        this.name = name;
    }

    public Sport() {}

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<MedalRecord> getRecords() {
        return records;
    }
    public void setRecords(List<MedalRecord> records) {
        this.records = records;
    }
}