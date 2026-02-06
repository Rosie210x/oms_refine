package com.oms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //only generated getters and setters for private fields
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse { // this class is used to show customer details aligns with order

    private String firstname, lastname, email, phoneNumber, cityName,
            region, postalCode, countryName;

}
