package com.example.demo.src.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateReq {
    private int id;
    private String itemName;
    private Integer price;
    private Integer quantity;
}
