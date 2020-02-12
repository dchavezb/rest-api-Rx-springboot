package com.dch.api.dao;

import java.util.List;
import com.dch.api.models.CurrencyExchange;
import com.dch.api.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Integer>
{
    List<CurrencyExchange> findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
}
