package com.dch.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchange {
    @Id
    @GeneratedValue
    private int id;
    private String currencyFrom;
    private String currencyTo;
    private double rate;
}
