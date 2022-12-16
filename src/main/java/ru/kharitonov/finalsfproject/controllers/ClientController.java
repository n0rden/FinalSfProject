package ru.kharitonov.finalsfproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kharitonov.finalsfproject.entities.ClientEntity;
import ru.kharitonov.finalsfproject.services.ClientService;

import java.util.List;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    List<ClientEntity> getAllClients() {
        return clientService.allClients();
    }
}