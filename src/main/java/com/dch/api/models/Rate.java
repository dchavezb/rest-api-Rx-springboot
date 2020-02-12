package com.dch.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="rates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "currencyFrom")
    private String currencyFrom;

    @Column(name = "currencyTo")
    private String currencyTo;

    @Column(name = "rate")
    private double rate;
}
