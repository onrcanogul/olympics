package com.huawei.olympics.service;

import com.huawei.olympics.controller.model.CreateAthleteModel;
import com.huawei.olympics.entity.Athlete;
import com.huawei.olympics.entity.Country;
import com.huawei.olympics.exception.CountryNotFoundException;
import com.huawei.olympics.repository.AthleteRepository;
import com.huawei.olympics.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {
    private final AthleteRepository repository;
    private final CountryRepository countryRepository;

    public AthleteService(AthleteRepository repository, CountryRepository countryRepository) {
        this.repository = repository;
        this.countryRepository = countryRepository;
    }

    public List<Athlete> getRanking() {
        return repository.findAllOrderedByRanking();
    }

    public Athlete create(CreateAthleteModel model) {
        Country country = countryRepository.findById(model.countryId()).orElseThrow(
                () -> new CountryNotFoundException("Country Not Found")
        );
        Athlete athlete = new Athlete(model.name(), country);
        return repository.save(athlete);
    }
}
