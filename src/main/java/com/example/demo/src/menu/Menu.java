package com.example.demo.src.menu;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private String id;
    private String storeId;
    private String userId;
    private String name;
    private String image;
    private double price;

    // Constructor for initializing Menu using MenuRequestDto
    public Menu(MenuRequestDto requestDto) {
        this.storeId = requestDto.getStoreId();
        this.userId = requestDto.getUserId();
        this.name = requestDto.getName();
        this.image = requestDto.getImage();
        this.price = requestDto.getPrice();
    }
