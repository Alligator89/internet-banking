package com.example.models;

import lombok.Data;

@Data
public class Transfer {
    private String fromNumberCard;
    private String toNumberCard;
    private Integer amount;
}
