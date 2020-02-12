package com.dch.api.controllers;

import com.dch.api.dao.CurrencyExchangeRepository;
import com.dch.api.dao.RateRepository;
import com.dch.api.models.*;
import com.dch.api.service.RateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rx.Scheduler;
import rx.Single;
import rx.schedulers.Schedulers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CurrencyExchangeControllerRx {

    @Autowired
    private RateRepository repository;

    @Autowired
    private RateService rateService;

    @PostMapping("/saveRateRx")
    public Single<ResponseEntity<BaseWebResponse>>  saveRate(@RequestBody Rate currencyExchange)
    {
        return rateService.saveRate(currencyExchange).subscribeOn(Schedulers.io()).map(
                s -> ResponseEntity.created(URI.create("/saveRateRx/" + s))
                        .body(BaseWebResponse.successWithData("Rate saved")));
    }


    @GetMapping("/getAllRatesRx")
    public Single<ResponseEntity<BaseWebResponse<List<RateWebResponse>>>> getAll()
    {
        return rateService.getAllCurrencyExchanges()
                .subscribeOn(Schedulers.io())
                .map(rateResponses -> ResponseEntity.ok(BaseWebResponse.successWithData(toRateWebResponseList(rateResponses))));
    }
    private List<RateWebResponse> toRateWebResponseList(List<RateResponse> rateResponseList) {
        return rateResponseList
                .stream()
                .map(this::toRateWebResponse)
                .collect(Collectors.toList());
    }
    private RateWebResponse toRateWebResponse(RateResponse rateResponse) {
        RateWebResponse rateWebResponse = new RateWebResponse();
        BeanUtils.copyProperties(rateResponse, rateWebResponse);
        return rateWebResponse;
    }

    @RequestMapping("/getChangeRx")
    public Single<ResponseEntity<BaseWebResponse<RateExchangeWebResponse>>>  Change2(@RequestParam double amountFrom,
                                         @RequestParam String currencyFrom,
                                         @RequestParam String currencyTo)
    {

        return rateService.getExchange(currencyFrom, currencyTo, amountFrom)
                .subscribeOn(Schedulers.io())
                .map(rateResponse -> ResponseEntity.ok(BaseWebResponse.successWithData(toRateExchangeResponse(rateResponse))));

    }
    private RateExchangeWebResponse toRateExchangeResponse(RateExchangeResponse rateResponse) {
        RateExchangeWebResponse rateWebResponse = new RateExchangeWebResponse();
        BeanUtils.copyProperties(rateResponse, rateWebResponse);
        return rateWebResponse;
    }
}
