package com.example.models;

import lombok.Data;

@Data
public class Cards {

    private String namePayCard;
    private String numberPayCard;
    private Integer balance;

    public Cards(String namePayCard, String numberPayCard, Integer balance) {
        this.namePayCard = namePayCard;
        this.numberPayCard = numberPayCard;
        this.balance = balance;
    }
}
