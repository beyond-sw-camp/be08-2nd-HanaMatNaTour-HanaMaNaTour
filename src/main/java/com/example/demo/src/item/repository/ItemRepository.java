package com.example.demo.src.item.repository;

import com.example.demo.src.item.dto.ItemDto;
import com.example.demo.src.item.dto.ItemSaveReq;
import com.example.demo.src.item.dto.ItemSearchReq;
import com.example.demo.src.item.dto.ItemUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepository implements ItemMapper{

    private final ItemMapper itemMapper;

    @Override
    public void save(ItemSaveReq itemSaveReq) {
        itemMapper.save(itemSaveReq);
    }

    @Override
    public void update(ItemUpdateReq itemUpdateReq) {
        itemMapper.update(itemUpdateReq);
    }

    @Override
    public Optional<ItemDto> findById(Long id) {
        return itemMapper.findById(id);
    }

    @Override
    public List<ItemDto> findAll(ItemSearchReq itemSearchReq) {
        return itemMapper.findAll(itemSearchReq);
    }
}
