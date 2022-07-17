package ru.Belov.springREST_API.Currencies.model;

import java.util.HashMap;
import java.util.Map;

public class ExchangeModel {
    String base;
    Map<String,Double> rates=new HashMap<>();

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
