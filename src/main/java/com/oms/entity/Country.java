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
@Table(name="country", schema="dbo")
public class Country {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="country_id")
    private Long countryId;
    @Column(name="country_name")
    private String countryName;

    @OneToMany(mappedBy="country")
    private List<City> cities;

    @OneToMany(mappedBy="country")
    private List<Customer> customers;
}
