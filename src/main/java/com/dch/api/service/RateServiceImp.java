package com.dch.api.service;

import com.dch.api.dao.CurrencyExchangeRepository;
import com.dch.api.dao.RateRepository;
import com.dch.api.models.CurrencyExchange;
import com.dch.api.models.Rate;
import com.dch.api.models.RateExchangeResponse;
import com.dch.api.models.RateResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RateServiceImp implements RateService{

    @Autowired
    private RateRepository rateRepository;


    @Override
    public Single<String> saveRate(Rate currencyExchange)
    {
        return saveRateToRepository(currencyExchange);
    }

    private Single<String>  saveRateToRepository(Rate currencyExchange)
    {
        return Single.create(singleSubscriber -> {
            //just in case we handle a case where the exception needs to be thrown
            //singleSubscriber.onError(new Exception());
            rateRepository.save(currencyExchange);
            singleSubscriber.onSuccess("saved");
        });
    }


    @Override
    public Single<List<RateResponse>> getAllCurrencyExchanges()
    {
        return findAllRatesInRepository()
                .map(this::toRateResponseList);
    }

    private Single<List<Rate>> findAllRatesInRepository()
    {
        return Single.create(singleSubscriber -> {
            List<Rate> rates = rateRepository.findAll();//.getContent();
            singleSubscriber.onSuccess(rates);
        });
    }
    private List<RateResponse> toRateResponseList(List<Rate> rateList) {
        return rateList
                .stream()
                .map(this::toRateResponse)
                .collect(Collectors.toList());
    }
    private RateResponse toRateResponse(Rate rate) {
        RateResponse rateResponse = new RateResponse();
        BeanUtils.copyProperties(rate, rateResponse);
        return rateResponse;
    }


    @Override
    public Single<List<RateResponse>> getAllCurrencyExchanges(String currencyFrom, String currencyTo)
    {
        return findAllRatesInRepository(currencyFrom,currencyTo)
                .map(this::toRateResponseList);
    }
    private Single<List<Rate>> findAllRatesInRepository(String currencyFrom, String currencyTo)
    {
        return Single.create(singleSubscriber -> {
            List<Rate> rates = rateRepository.findByCurrencyFromAndCurrencyTo(currencyFrom, currencyTo);//.getContent();
            singleSubscriber.onSuccess(rates);
        });
    }

    @Override
    public Single<RateExchangeResponse> getExchange(String currencyFrom, String currencyTo, double amount)
    {
        return findExchangeInRepository(currencyFrom, currencyTo, amount);
    }

    private Single<RateExchangeResponse> findExchangeInRepository(String currencyFrom, String currencyTo, double amount)
    {
        return Single.create(singleSubscriber -> {
            List<Rate> rates = rateRepository.findByCurrencyFromAndCurrencyTo(currencyFrom, currencyTo);
                RateExchangeResponse rateExchangeResponse = toRateResponse(rates.get(0), amount);
                singleSubscriber.onSuccess(rateExchangeResponse);
        });
    }

    private RateExchangeResponse toRateResponse(Rate rate, double amount) {
        RateExchangeResponse rateExchangeResponse = new RateExchangeResponse();
        //BeanUtils.copyProperties(rate, rateResponse);
        rateExchangeResponse.setAmount(amount);
        rateExchangeResponse.setCurrencyFrom(rate.getCurrencyFrom());
        rateExchangeResponse.setCurrencyTo(rate.getCurrencyTo());
        rateExchangeResponse.setRate(rate.getRate());
        rateExchangeResponse.setAmountInCurrencyTarget(amount * rate.getRate());
        return rateExchangeResponse;
    }
}
