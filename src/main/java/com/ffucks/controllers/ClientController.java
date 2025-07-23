package com.ffucks.controllers;

import com.ffucks.dtos.ClientDTO;
import com.ffucks.entities.Client;
import com.ffucks.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping
    public ResponseEntity getClients() {
        List<Client> clients = repository.findAll();
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody @Valid ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setPhoneNumber(clientDTO.phoneNumber());

        repository.save(client);
        return ResponseEntity.ok("Client created successfully");
    }
}
