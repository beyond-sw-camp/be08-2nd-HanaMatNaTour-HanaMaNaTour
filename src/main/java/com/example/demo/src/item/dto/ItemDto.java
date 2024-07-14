package com.example.demo.src.item.dto;
import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
}
