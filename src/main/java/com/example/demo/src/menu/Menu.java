package com.example.demo.src.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private int menuId;
    private int storeId;
    private String menuName;
    private String image;
    private double price;

    // Constructor for initializing Menu using MenuRequestDto
    public Menu(MenuRequestDto requestDto) {
        this.storeId = requestDto.getStoreId();
        this.menuName = requestDto.getMenuName();
        this.image = requestDto.getImage();
        this.price = requestDto.getPrice();
    }
}
