package com.huawei.olympics.service;

import com.huawei.olympics.entity.Athlete;
import com.huawei.olympics.entity.MedalRecord;
import com.huawei.olympics.entity.Sport;
import com.huawei.olympics.enumeration.MedalTypes;
import com.huawei.olympics.exception.AthleteNotFoundException;
import com.huawei.olympics.exception.SportNotFoundException;
import com.huawei.olympics.repository.AthleteRepository;
import com.huawei.olympics.repository.MedalRecordRepository;
import com.huawei.olympics.repository.SportRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MedalRecordService {
    private final MedalRecordRepository repository;
    private final SportRepository sportRepository;
    private final AthleteRepository athleteRepository;

    public MedalRecordService(MedalRecordRepository repository, SportRepository sportRepository, AthleteRepository athleteRepository) {
        this.repository = repository;
        this.sportRepository = sportRepository;
        this.athleteRepository = athleteRepository;
    }

    public MedalRecord create(MedalTypes medalType, UUID athleteId, UUID sportId) {
        Sport sport = sportRepository.findById(sportId).orElseThrow(
                () -> new SportNotFoundException("Sport Not Found")
        );
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow(
                () -> new AthleteNotFoundException("Athlete Not Found")
        );
        MedalRecord medalRecord = new MedalRecord(sport, athlete, medalType);
        return repository.save(medalRecord);
    }
}
