package com.huawei.olympics.entity;

import com.huawei.olympics.enumeration.MedalTypes;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "medal_records")
public class MedalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Sport sport;
    @ManyToOne
    private Country country;

    @Enumerated(EnumType.STRING)
    private MedalTypes medalType;

    @ManyToOne
    private Athlete athlete;

    public MedalRecord(Sport sport, Athlete athlete, MedalTypes medalType) {
        this.sport = sport;
        this.athlete = athlete;
        this.country = athlete.getCountry();
        this.medalType = medalType;
    }

    public MedalRecord() {}

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public MedalTypes getMedalType() {
        return medalType;
    }

    public void setMedalType(MedalTypes medalType) {
        this.medalType = medalType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }
}

//COUNTY -> ATHLETES
//ATHLETE -> RECORDS
//RECORD -> SPOR, SPOR -> RECORDS