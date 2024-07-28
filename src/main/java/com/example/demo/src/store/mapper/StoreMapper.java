package com.example.demo.src.store.mapper;

import com.example.demo.src.store.model.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    List<Store> getAllStores();

    Store getStoreById(int id);

    int addStore(Store store);

    int updateStore(Store store);

    int deleteStore(int id);
}
