package com.huawei.olympics.service;

import com.huawei.olympics.entity.Country;
import com.huawei.olympics.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> getRanking() {
        return repository.findAllOrderedByRanking();
    }

    public Country create(String name, String code) {
        Country country = new Country(code, name);
        return repository.save(country);
    }
}
