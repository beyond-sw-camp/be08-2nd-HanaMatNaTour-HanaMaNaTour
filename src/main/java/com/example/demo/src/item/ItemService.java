package com.example.demo.src.item;


import com.example.demo.src.item.dto.ItemSaveReq;
import com.example.demo.src.item.dto.ItemUpdateReq;
import com.example.demo.src.item.repository.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemMapper itemMapper;

    public void saveItem(ItemSaveReq item){
        itemMapper.save(item);
    }

    public void updateItem(ItemUpdateReq item) {
        itemMapper.update(item);
    }
}
