package com.example.demo.src.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequestDto {
    private String menuId;
    private int storeId;
    private String userUuid;
    private String menuName;
    private String image;
    private double price;
}
