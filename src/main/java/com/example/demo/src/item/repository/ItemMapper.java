package com.example.demo.src.item.repository;

import com.example.demo.src.item.dto.ItemSaveReq;
import com.example.demo.src.item.dto.ItemUpdateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMapper {

    void save(ItemSaveReq itemSaveReq);

    void update(ItemUpdateReq itemUpdateReq);

}
