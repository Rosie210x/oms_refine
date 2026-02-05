package com.oms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest { //this class is used to input customer data

    private String firstname, lastname, email, phoneNumber,
            addressLine1, addressLine2, cityName,
            region, postalCode;
    private Long countryId;
}
