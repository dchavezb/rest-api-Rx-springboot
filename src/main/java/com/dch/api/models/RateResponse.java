package com.dch.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RateResponse {
    private int id;
    private String currencyFrom;
    private String currencyTo;
    private double rate;
}
