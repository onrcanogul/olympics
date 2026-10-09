package com.huawei.olympics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "athletes")
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne
    private Country country;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "athlete_id")
    private List<MedalRecord> records;

    public Athlete(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Athlete() {

    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

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

//COUNTY -> ATHLETES
//ATHLETE -> RECORDS
//RECORD -> SPOR, SPOR -> RECORDS