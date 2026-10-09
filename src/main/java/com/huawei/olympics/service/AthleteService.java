package com.huawei.olympics.service;

import com.huawei.olympics.controller.model.CreateAthleteModel;
import com.huawei.olympics.entity.Athlete;
import com.huawei.olympics.entity.Country;
import com.huawei.olympics.enumeration.MedalTypes;
import com.huawei.olympics.exception.CountryNotFoundException;
import com.huawei.olympics.model.MedalCounts;
import com.huawei.olympics.repository.AthleteRepository;
import com.huawei.olympics.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AthleteService {
    private final AthleteRepository repository;
    private final CountryRepository countryRepository;

    public AthleteService(AthleteRepository repository, CountryRepository countryRepository) {
        this.repository = repository;
        this.countryRepository = countryRepository;
    }

    public List<Athlete> getRanking() {
        List<Athlete> athletes = repository.findAll();
        HashMap<Athlete, MedalCounts> mappedList = new HashMap<>();

        athletes.forEach(a -> {
            MedalCounts medalCounts = new MedalCounts();

            a.getRecords().forEach(mr -> {
                if (mr.getMedalType() == MedalTypes.BRONZE) {
                    medalCounts.increaseBronzeCount();
                }
                if (mr.getMedalType() == MedalTypes.SILVER) {
                    medalCounts.increaseSilverCount();
                }
                if (mr.getMedalType() == MedalTypes.GOLD) {
                    medalCounts.increaseGoldCount();
                }
            });

            mappedList.put(a, medalCounts);
        });

        List<Map.Entry<Athlete, MedalCounts>> sortedList =
                mappedList.entrySet()
                        .stream()
                        .sorted(
                                Comparator
                                        .<Map.Entry<Athlete, MedalCounts>>comparingInt(
                                                e -> e.getValue().getTotalCount()
                                        )
                                        .thenComparingInt(e -> e.getValue().goldCount)
                                        .thenComparingInt(e -> e.getValue().silverCount)
                                        .thenComparingInt(e -> e.getValue().bronzeCount)
                                        .reversed()
                                        .thenComparing(
                                                e -> e.getKey().getName(),
                                                String.CASE_INSENSITIVE_ORDER
                                        )
                        ).toList();

        return sortedList.stream()
                .map(Map.Entry::getKey)
                .toList();
    }

    public Athlete create(CreateAthleteModel model) {
        Country country = countryRepository.findById(model.countryId()).orElseThrow(
                () -> new CountryNotFoundException("Country Not Found")
        );
        Athlete athlete = new Athlete(model.name(), country);
        return repository.save(athlete);
    }
}
