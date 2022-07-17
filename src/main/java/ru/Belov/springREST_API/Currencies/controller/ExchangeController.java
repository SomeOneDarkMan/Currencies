package ru.Belov.springREST_API.Currencies.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.Belov.springREST_API.Currencies.DTO.ExchangeDTO;
import ru.Belov.springREST_API.Currencies.srvices.ExchangeService;
import ru.Belov.springREST_API.Currencies.until.ExchangeErrors;
import ru.Belov.springREST_API.Currencies.until.ExchangeThrow;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ExchangeController {
    private final ModelMapper modelMapper;
    public final ExchangeService exchangeService;
    @Autowired
    public ExchangeController(ModelMapper modelMapper, ExchangeService exchangeService) {
        this.modelMapper = modelMapper;
        this.exchangeService = exchangeService;
    }

    @GetMapping("/endpoint")
    public ResponseEntity<String> knowIt(@RequestBody @Valid ExchangeDTO exchangeDTO,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder stringBuilder=new StringBuilder();
            List<FieldError> field=  bindingResult.getFieldErrors();
            for (FieldError error:field){
            stringBuilder.append(error.getField()+"-"+ error.getDefaultMessage());

            }
            throw new ExchangeThrow(stringBuilder.toString());
        }
        System.out.println(exchangeDTO.getByIndex());
        String urlGif=exchangeService.getCurse(exchangeDTO.getByIndex());

        return new ResponseEntity<String>( urlGif,HttpStatus.OK);
    }
    @ExceptionHandler
    public ResponseEntity<ExchangeErrors> throwError(ExchangeThrow error){
        ExchangeErrors ex=new ExchangeErrors(error.getMessage());
       System.out.println("Я смог");

        return new ResponseEntity<>(ex,HttpStatus.NOT_FOUND);
    }

}
