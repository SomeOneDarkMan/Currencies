package ru.Belov.springREST_API.Currencies.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

public class ExchangeDTO {


    @NotEmpty(message = "byIndex did not have to   empty'")
     @Pattern(regexp = "[A-Z]{3}",message = "byIndex must like 'USD'")
    String byIndex;

    public String getByIndex() {
        return byIndex;
    }

    public void setByIndex(String byIndex) {
        this.byIndex = byIndex;
    }








}
