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

    @Column(name="email", unique = true, nullable=false)
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

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

    @OneToMany(mappedBy="customer")
    private List<Order> orders;

    public Customer(String firstname, String lastname, String email, String phoneNumber, String region, String postalCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.region = region;
        this.postalCode = postalCode;
    }
}
