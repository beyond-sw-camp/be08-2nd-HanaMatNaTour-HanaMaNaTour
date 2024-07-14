package com.example.demo.src.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemSaveReq {
    private String itemName;
    private Integer price;
    private Integer quantity;
}
