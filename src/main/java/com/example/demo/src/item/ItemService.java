package com.example.demo.src.item;


import com.example.demo.src.item.dto.ItemSaveReq;
import com.example.demo.src.item.dto.ItemUpdateReq;
import com.example.demo.src.item.repository.ItemMapper;
import com.example.demo.src.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(ItemSaveReq item){
        itemRepository.save(item);
    }

    public void updateItem(ItemUpdateReq item) {
        itemRepository.update(item);
    }
}
