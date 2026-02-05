package com.oms.service;

import com.oms.entity.Country;
import com.oms.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private ICountryRepository countryRepository;

    @Autowired
    public CountryService(ICountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    //find country in db when input customer data
    public Country findById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
    }

    //find all country in db to show in dropdown
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
