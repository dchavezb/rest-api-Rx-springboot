package com.dch.api.service;

import com.dch.api.models.CurrencyExchange;
import com.dch.api.models.Rate;
import com.dch.api.models.RateExchangeResponse;
import com.dch.api.models.RateResponse;
import rx.Single;

import java.util.List;

public interface RateService {

    Single<String> saveRate(Rate currencyExchange);
    Single<List<RateResponse>> getAllCurrencyExchanges();
    Single<List<RateResponse>> getAllCurrencyExchanges(String currencyFrom, String currencyTo);
    Single<RateExchangeResponse> getExchange(String currencyFrom, String currencyTo, double amount);
}
