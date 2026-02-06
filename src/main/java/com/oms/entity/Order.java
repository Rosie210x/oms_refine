package com.oms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders", schema="dbo")
public class Order {

    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderId;

    @Column(name="order_code")
    private String orderCode;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name="address_line1", nullable=false)
    private String addressLine1;

    @Column(name="address_line2")
    private String addressLine2;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    public Order(LocalDateTime orderDate, Customer customer, String addressLine1, String addressLine2) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
    }

}
