package com.example.controller;

import com.example.models.Cards;
import com.example.models.Client;
import com.example.models.Transfer;
import com.example.sevice.IBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class IBController {

    private final IBService ibService;

    public IBController(IBService ibService) {
        this.ibService = ibService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients = ibService.getInfoClients();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Integer id) {
        Client client = ibService.getInfoClient(id);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cards")
    public ResponseEntity<List<Cards>> getInfoCards() {
        List<Cards> cards = ibService.getCards();
        if (cards != null) {
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createClient(@RequestBody Client client) {
        Client clientSave = ibService.createClient(client);
        Client clientResult = ibService.getInfoClient(clientSave.getId());
        if (clientResult != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateClient(@RequestBody Client client) {
        ibService.updateClient(client);
        Client clientResult = ibService.getInfoClient(client.getId());
        if (client.equals(clientResult)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Integer id) {
        Client clientUpdated = ibService.getInfoClient(id);
        ibService.deleteClient(id);
        Client client = ibService.getInfoClient(id);
        if (client == null && clientUpdated != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/transfer")
    public ResponseEntity<HttpStatus> transferMoney(@RequestBody Transfer transfer) {
        Client client = ibService.findClient(transfer.getFromNumberCard());
        Client client1 = ibService.findClient(transfer.getToNumberCard());
        boolean flag = transfer.getAmount() <= client.getCards().getBalance() && client1 != null;
        if (flag) {
            ibService.transferMoney(client, client1, transfer);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

