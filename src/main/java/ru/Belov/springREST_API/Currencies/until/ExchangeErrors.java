package ru.Belov.springREST_API.Currencies.until;

public class ExchangeErrors {
    String massege;

    public ExchangeErrors(String massege) {
        this.massege = massege;
    }

    public String getMassege() {
        return massege;
    }

    public void setMassege(String massege) {
        this.massege = massege;
    }
}
