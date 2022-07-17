package ru.Belov.springREST_API.Currencies.srvices;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.Belov.springREST_API.Currencies.model.ExchangeModel;
import ru.Belov.springREST_API.Currencies.model.SomeGift;

import java.util.Date;
import java.util.Map;

@Service
public class ExchangeService {

    public String getCurse(String byIndex) {
        RestTemplate response =new RestTemplate();
        String token="0a7ad077c3d542509debf94332f86685";
        String URL="https://openexchangerates.org/api/latest.json?app_id="+token+"/";//отношение одной валюты к другой
        Date nowDate=new Date();
        String forJsonNowDate=(1900+nowDate.getYear())+"-"+"0"+(+1+nowDate.getMonth())+"-"+nowDate.getDate();
        String forJsonLastDay=(1900+nowDate.getYear())+"-"+"0"+(+1+nowDate.getMonth())+"-"+(nowDate.getDate()-1);
        String URLDate="https://openexchangerates.org/api/historical/"+forJsonNowDate+".json?app_id="+token;
        String URLDateLastDay="https://openexchangerates.org/api/historical/"+forJsonLastDay+".json?app_id="+token;
        //System.out.println(URLDate);
        // System.out.println(URLDateLastDay);
        ExchangeModel nowValue=response.getForObject(URLDate, ExchangeModel.class);
        ExchangeModel lastDayValue =response.getForObject(URLDateLastDay,ExchangeModel.class);
        Double nV=nowValue.getRates().get(byIndex);
        Double lDV=lastDayValue.getRates().get(byIndex);
        if(nV<lDV){
           return ifgetsomeGive()+" :-мы бедны";//мы бедны
        }else if (lDV<nV){
            return  ifgetsomeGive()+" :-мы богаты";//мы богаты
        }
        return  ifgetsomeGive()+" :-ожидаем";//ожидаем
        // yyyy-mm-dd
    }

    private String ifgetsomeGive(){
        RestTemplate response =new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token="a19eAdXqHlZcal5kMhO7prAu4Jcj80o3";
        headers.add("api_key",token);
        //headers.setContentDisposition(ContentDisposition.parse("GIPHY API Key."));
        String URl="https://api.giphy.com/v1/stickers/random";
        HttpEntity<Map<String,String>> request=new HttpEntity<>(headers);
        HttpEntity<SomeGift> responseToMe=response.exchange(URl, HttpMethod.GET,request, SomeGift.class);
       return responseToMe.getBody().getData().getUrl();

    }
}
