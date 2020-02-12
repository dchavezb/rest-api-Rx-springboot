package com.dch.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RateWebResponse {
    private int id;
    private String currencyFrom;
    private String currencyTo;
    private double rate;
}
