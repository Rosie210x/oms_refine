package com.oms.service;

import com.oms.dto.CustomerRequest;
import com.oms.dto.CustomerResponse;
import com.oms.entity.City;
import com.oms.entity.Country;
import com.oms.entity.Customer;

import com.oms.repository.ICityRepository;
import com.oms.repository.ICountryRepository;
import com.oms.repository.ICustomerRepository;
import com.oms.utils.EmailValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    //map request to entity and save to db
    public Customer saveCustomer(CustomerRequest request) {
        //don't need to check null bc always create req in controller bf calling service
//        if (request == null) {
//            throw new IllegalArgumentException("Customer cannot be null");
//        }

        //validate not null fields input
        validateNotBlank(request.getFirstname(), "Firstname");
        validateNotBlank(request.getLastname(), "Lastname");
        validateNotBlank(request.getEmail(), "Email");
        EmailValidation.validateEmail(request.getEmail());

        Optional<Customer> existingCustomer = findByEmail(request.getEmail());
        if (existingCustomer.isPresent()) {
            return existingCustomer.get();
        }

        //map to customer
        Customer customer = new Customer(
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getRegion(),
                request.getPostalCode()
        );

        //validate country input
        if (request.getCountryId() == null) {
            throw new IllegalArgumentException("Country ID cannot be null");
        }
        Country country = countryService.findById(request.getCountryId());
        customer.setCountry(country);

        //validate city input
        validateNotBlank(request.getCityName(), "City");
        //bc save city in db therefore need to create new city if db not available
        City city = cityRepository.findByCityNameIgnoreCase(request.getCityName().trim())
                .orElseGet(() -> {
                    City newCity = new City();
                    newCity.setCityName(request.getCityName().trim());
                    newCity.setCountry(country);
                    return cityRepository.save(newCity);
                });
        customer.setCity(city);
        return customerRepository.save(customer);
    }

    //map entity to response use when want to show order details
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
                cityName,
                customer.getRegion(),
                customer.getPostalCode(),
                countryName
        );

        return response;
    }

    private void validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }


    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
