package com.dch.api.dao;

import com.dch.api.models.CurrencyExchange;
import com.dch.api.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Integer> {
    List<Rate> findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
}
