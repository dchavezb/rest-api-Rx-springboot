package com.dch.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RateExchangeResponse {
    private double amount;
    private double amountInCurrencyTarget;
    private String currencyFrom;
    private String currencyTo;
    private double rate;
}
