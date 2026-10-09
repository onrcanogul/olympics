package com.huawei.olympics.service;

import com.huawei.olympics.entity.Country;
import com.huawei.olympics.enumeration.MedalTypes;
import com.huawei.olympics.model.MedalCounts;
import com.huawei.olympics.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountryService {
    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> getRanking() {
        List<Country> countries = repository.findAll();
        HashMap<Country, MedalCounts> mappedList = new HashMap<>();
        countries
                .forEach(c -> {
                    MedalCounts medalCounts = new MedalCounts();
                    c.getRecords().forEach(mr -> {
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
                    mappedList.put(c, medalCounts);
                });

        List<Map.Entry<Country, MedalCounts>> sortedList =
                mappedList.entrySet()
                        .stream()
                        .sorted(
                                Comparator
                                        .<Map.Entry<Country, MedalCounts>>comparingInt(
                                                e -> e.getValue().goldCount
                                        )
                                        .thenComparingInt(e -> e.getValue().silverCount)
                                        .thenComparingInt(e -> e.getValue().bronzeCount)
                                        .reversed()
                                        .thenComparing(
                                                e -> e.getKey().getName(),
                                                String.CASE_INSENSITIVE_ORDER
                                        )
                        )
                        .toList();

        return sortedList.stream().map(Map.Entry::getKey).toList();
    }

    public Country create(String name, String code) {
        Country country = new Country(code, name);
        return repository.save(country);
    }
}
