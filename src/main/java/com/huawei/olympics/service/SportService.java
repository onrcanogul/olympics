package com.huawei.olympics.service;

import com.huawei.olympics.entity.Sport;
import com.huawei.olympics.repository.SportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService {
    private final SportRepository repository;

    public SportService(SportRepository repository) {
        this.repository = repository;
    }

    public List<Sport> getRanking() {
        return repository.findAllOrderedByRanking();
    }

    public Sport create(String name) {
        Sport sport = new Sport(name);
        return repository.save(sport);
    }
}
