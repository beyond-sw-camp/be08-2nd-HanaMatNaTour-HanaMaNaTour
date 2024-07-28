package com.example.demo.src.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequestDto {
    private String id;
    private String storeId;
    private String userId;
    private String name;
    private String image;
    private double price;
}
