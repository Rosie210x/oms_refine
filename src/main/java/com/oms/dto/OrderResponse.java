package com.oms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse { //this class is used to show orders's list and details

    private String orderCode;

    private String orderDate;

    private CustomerResponse customerResponse;

}