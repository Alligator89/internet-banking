package com.example.repository;

import com.example.models.Cards;
import com.example.models.Client;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IBRepository {
    private final Map<Integer, Client> clientList = new HashMap<>();

    private final Map<Integer, Cards> cardsList = new HashMap<>();

    {
        Client client = new Client();
        client.setCards(new Cards("Alpha-Bank", "4534", 2000));
        client.setId(1);
        client.setName("Dima");
        client.setSurName("Ivanov");
        client.setAge(23);

        Client client1 = new Client();
        client1.setCards(new Cards("Prior-Bank", "6789", 3000));
        client1.setId(2);
        client1.setName("Pavel");
        client1.setSurName("Petrov");
        client1.setAge(30);

        clientList.put(1, client);
        clientList.put(2, client1);
    }

    {
        cardsList.put(1, new Cards("Alpha-Bank", "4534", 2000));
        cardsList.put(2, new Cards("Prior-Bank", "6789", 3000));
    }


    public Client findById(Integer id) {
        return clientList.get(id);
    }

    public List<Client> getAllClients() {
        return clientList.values().stream().toList();
    }

    public List<Cards> getInfoCards() {
        return cardsList.values().stream().toList();
    }

    public void save(Client client) {
        clientList.put(client.getId(), client);
    }

    public void delete(Integer id) {
        clientList.remove(id);
    }

    public Client findClientCard(String numberCard) {
        Client cardNumber = null;
        for (Client client : clientList.values()) {
            if (client.getCards().getNumberPayCard().equals(numberCard)) {
                cardNumber = client;
            }
        }
        return cardNumber;
    }

    public void transfer(Client client, Client client1) {
        clientList.put(client.getId(), client);
        clientList.put(client1.getId(), client1);
    }
}
