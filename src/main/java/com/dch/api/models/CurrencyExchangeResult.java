package com.dch.api.models;

public class CurrencyExchangeResult {
    private double amount;
    private double amountInCurrencyTarget;
    private String currencyFrom;
    private String currencyTo;
    private double rate;

    public CurrencyExchangeResult(double amount, double amountInCurrencyTarget, String currencyFrom, String currencyTo, double rate )
    {
        this.amount = amount;
        this.amountInCurrencyTarget = amountInCurrencyTarget;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }

    public double getAmount() {
        return amount;
    }

    public double getAmountInCurrencyTarget() {
        return amountInCurrencyTarget;
    }
    public String getCurrencyFrom() {
        return currencyFrom;
    }
    public String getCurrencyTo()
    {
        return currencyTo;
    }

    public double getRate() {
        return rate;
    }
}
