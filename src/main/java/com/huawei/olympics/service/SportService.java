package com.huawei.olympics.service;

import com.huawei.olympics.entity.Sport;
import com.huawei.olympics.enumeration.MedalTypes;
import com.huawei.olympics.model.MedalCounts;
import com.huawei.olympics.repository.SportRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SportService {
    private final SportRepository repository;

    public SportService(SportRepository repository) {
        this.repository = repository;
    }

    public List<Sport> getRanking() {
        List<Sport> sports = repository.findAll();
        HashMap<Sport, MedalCounts> mappedList = new HashMap<>();

        sports.forEach(s -> {
            MedalCounts medalCounts = new MedalCounts();

            s.getRecords().forEach(mr -> {
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

            mappedList.put(s, medalCounts);
        });

        List<Map.Entry<Sport, MedalCounts>> sortedList =
                mappedList.entrySet()
                        .stream()
                        .sorted(
                                Comparator
                                        .<Map.Entry<Sport, MedalCounts>>comparingInt(
                                                e -> e.getValue().getTotalCount()
                                        )
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

    public Sport create(String name) {
        Sport sport = new Sport(name);
        return repository.save(sport);
    }
}
