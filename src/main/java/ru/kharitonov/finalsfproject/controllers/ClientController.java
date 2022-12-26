package ru.kharitonov.finalsfproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kharitonov.finalsfproject.dtos.ClientDto;
import ru.kharitonov.finalsfproject.dtos.TransactionDto;
import ru.kharitonov.finalsfproject.entities.ClientEntity;
import ru.kharitonov.finalsfproject.entities.TransactionEntity;
import ru.kharitonov.finalsfproject.services.ClientService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private final ClientService clientService;

    @GetMapping("/{id}")
    @ResponseBody
    public String getBalance(@PathVariable Long id) {
        return clientService.getMoney(id);
    }

    @PutMapping("/takemoney")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String takeMoney(@RequestParam Long id, @RequestParam Double money) {
        return clientService.takeMoney(id, money);
    }

    @PutMapping("/putmoney")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String putMoney(@RequestParam Long id, @RequestParam Double money) {
        return clientService.putMoney(id, money);
    }

    @GetMapping("/getoperations/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TransactionDto> getOperationList(@PathVariable Long id,
                                                 @RequestParam(required = false) String minDate,
                                                 @RequestParam(required = false) String maxDate) {
        List<TransactionDto> transactionDtos = new ArrayList<>();
        Set<TransactionDto> transactionEntitySet = clientService.getOperationList(id, minDate, maxDate);
        transactionDtos.addAll(transactionEntitySet);
        System.out.println(transactionDtos);
        return transactionDtos;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ClientDto> getAllClients() {
        return clientService.allClients();
    }
}