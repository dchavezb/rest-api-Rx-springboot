package com.dch.api.controllers;

import com.dch.api.dao.CurrencyExchangeRepository;
import com.dch.api.models.CurrencyExchange;
import com.dch.api.models.CurrencyExchangeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository repository;

    @PostMapping("/saveRate")
    public String saveRate(@RequestBody CurrencyExchange currencyExchange)
    {
        //currencyExchange.setId(currencyExchange.getCurrencyFrom() +
        repository.save(currencyExchange);
        return "Rate saved...."; // + currencyExchange.getCurrencyFrom();
    }

    @GetMapping("/getAllRates")
    public List<CurrencyExchange> getAll()
    {
        return repository.findAll();
    }
    @RequestMapping("/getChange")
    public CurrencyExchangeResult Change(@RequestParam double amountFrom,
                           @RequestParam String currencyFrom,
                           @RequestParam String currencyTo)
    {
        //ExampleMatcher ignoreExampleMatcher = ExampleMatcher.matchingAny()
                //.withMatcher("rate", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
        //        .withIgnorePaths("rate");
       // Example<CurrencyExchange> example = Example.of(CurrencyExchange.from(currencyFrom,currencyTo,null), ignoreExampleMatcher);
        //repository.
        //return amountFrom*3;
        List<CurrencyExchange> list = repository.findByCurrencyFromAndCurrencyTo(currencyFrom,currencyTo);
        // we are considering data is there, so get 0 as index
        CurrencyExchange cuE = list.get(0);
        CurrencyExchangeResult resultE = new CurrencyExchangeResult(amountFrom, cuE.getRate()*amountFrom, currencyFrom, currencyTo, cuE.getRate());

        return resultE;
    }

}
