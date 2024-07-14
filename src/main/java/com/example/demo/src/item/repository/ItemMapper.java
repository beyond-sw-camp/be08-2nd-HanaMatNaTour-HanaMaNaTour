package com.example.demo.src.item.repository;

import com.example.demo.src.item.dto.ItemDto;
import com.example.demo.src.item.dto.ItemSaveReq;
import com.example.demo.src.item.dto.ItemSearchReq;
import com.example.demo.src.item.dto.ItemUpdateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void save(ItemSaveReq itemSaveReq);

    void update(ItemUpdateReq itemUpdateReq);

    Optional<ItemDto> findById(Long id);

    List<ItemDto> findAll(ItemSearchReq itemSearchReq);



}
