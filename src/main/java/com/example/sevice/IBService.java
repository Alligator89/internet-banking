package com.example.sevice;

import com.example.models.Cards;
import com.example.models.Client;
import com.example.models.Transfer;
import com.example.repository.IBRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBService {

    private Integer count = 2;

    private final IBRepository ibRepository;

    public IBService(IBRepository ibRepository) {
        this.ibRepository = ibRepository;
    }

    public List<Client> getInfoClients() {
        return ibRepository.getAllClients();
    }

    public Client getInfoClient(Integer id) {
        return ibRepository.findById(id);
    }

    public List<Cards> getCards() {
        return ibRepository.getInfoCards();
    }

    public Client createClient(Client client) {
        client.setId(count++);
        ibRepository.save(client);
        return client;
    }

    public void updateClient(Client client) {
        Client clientEdit = getInfoClient(client.getId());
        clientEdit.setCards(client.getCards());
        clientEdit.setId(client.getId());
        clientEdit.setName(client.getName());
        clientEdit.setSurName(client.getSurName());
        clientEdit.setAge(client.getAge());
        ibRepository.save(clientEdit);
    }

    public void deleteClient(Integer id) {
        ibRepository.delete(id);
    }

    public Client findClient(String numberCard) {
        return ibRepository.findClientCard(numberCard);
    }

    public void transferMoney(Client client, Client client1, Transfer transfer) {
        client.getCards().setBalance(client.getCards().getBalance() - transfer.getAmount());
        client1.getCards().setBalance(client1.getCards().getBalance() + transfer.getAmount());
        ibRepository.transfer(client, client1);
    }
}

