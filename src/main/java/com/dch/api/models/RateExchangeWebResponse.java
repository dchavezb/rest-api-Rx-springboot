package com.dch.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RateExchangeWebResponse {
    private double amount;
    private double amountInCurrencyTarget;
    private String currencyFrom;
    private String currencyTo;
    private double rate;
}
