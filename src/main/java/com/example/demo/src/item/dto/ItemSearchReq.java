package com.example.demo.src.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemSearchReq {

    private String itemName;
    private Integer maxPrice;

}
