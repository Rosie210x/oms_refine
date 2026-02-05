package com.oms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customers", schema="dbo")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId; //Long in db is bigint

    @Column(name="firstname", nullable=false)
    private String firstname;

    @Column(name="lastname", nullable=false)
    private String lastname;

    @Column(name="email", unique=true, nullable=false)
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address_line1", nullable=false)
    private String addressLine1;

    @Column(name="address_line2")
    private String addressLine2;

    @Column(name="region")
    private String region;

    @Column(name="postal_code")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    @OneToOne(mappedBy="customer")
    private Order order;

    public Customer(String firstname, String lastname, String email, String phoneNumber, String addressLine1, String addressLine2, String region, String postalCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.region = region;
        this.postalCode = postalCode;
    }
}
