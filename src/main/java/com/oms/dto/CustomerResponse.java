package com.oms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //only generated getters and setters for private fields
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse { // this class may be used in the future version

    private String firstname, lastname, email, phoneNumber,
            addressLine1, addressLine2, cityName,
            region, postalCode, countryName;
}
