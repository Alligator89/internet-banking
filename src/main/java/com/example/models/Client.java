package com.example.models;

import lombok.Data;

@Data
public class Client {
    private Cards cards;
    private Integer id;
    private String name;
    private String surName;
    private Integer age;
}
