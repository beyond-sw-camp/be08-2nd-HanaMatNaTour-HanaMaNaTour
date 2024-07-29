package com.example.demo.src.hanamoa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearchParam {
    private int size;
    private int offset;
    private String keyword;
}
