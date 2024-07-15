package com.example.demo.src.item.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemSaveReq {

    @NotNull
    private String itemName;

    @NotNull
    private Integer price;

    @NotNull
    private Integer quantity;
}
