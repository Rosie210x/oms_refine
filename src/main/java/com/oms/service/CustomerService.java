package com.oms.service;

import com.oms.dto.CustomerRequest;
import com.oms.dto.CustomerResponse;
import com.oms.entity.City;
import com.oms.entity.Country;
import com.oms.entity.Customer;

import com.oms.repository.ICityRepository;
import com.oms.repository.ICountryRepository;
import com.oms.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService{

    private ICustomerRepository customerRepository;
    private ICityRepository cityRepository;
    private ICountryRepository countryRepository;
    private CountryService countryService;

    @Autowired
    public CustomerService(ICustomerRepository customerRepository,
                           ICityRepository cityRepository,
                           ICountryRepository countryRepository,
                           CountryService countryService) {
        this.customerRepository = customerRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.countryService = countryService;
    }

    public Customer saveCustomer(CustomerRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Customer with email: " + request.getEmail() + " already exists");
        }

        Customer customer = new Customer(
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getAddressLine1(),
                request.getAddressLine2(),
                request.getRegion(),
                request.getPostalCode()
        );

        //validate country input
        if (request.getCountryId() == null) {
            throw new RuntimeException("Country cannot be empty");
        }
        Country country = countryService.findById(request.getCountryId());
        customer.setCountry(country);

        if (request.getCityName() == null || request.getCityName().isBlank()) {
            throw new RuntimeException("City cannot be empty");
        }
        City city = cityRepository.findByCityNameIgnoreCase(request.getCityName().trim())
                .orElseThrow(() -> new IllegalArgumentException("City not found"));
        customer.setCity(city);
        return customerRepository.save(customer);
    }

    public CustomerResponse mapToResponse(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        String cityName = customer.getCity() != null ? customer.getCity().getCityName() : null;
        String countryName = customer.getCountry() != null ? customer.getCountry().getCountryName() : null;
        CustomerResponse response = new CustomerResponse(
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddressLine1(),
                customer.getAddressLine2(),
                cityName,
                customer.getRegion(),
                customer.getPostalCode(),
                countryName
        );

        return response;
    }

    public boolean isCustomerExist(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return customerRepository.existsByEmail(email);
    }

}
