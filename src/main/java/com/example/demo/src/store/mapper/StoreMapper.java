package com.example.demo.src.store.mapper;

import com.example.demo.src.store.dto.StoreResponse;
import com.example.demo.src.store.model.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {

    List<Store> getAllStores(@Param("offset") int offset, @Param("size") int size);

    Store getStoreById(int id);

    int addStore(Store store);

    int updateStore(Store store);

    int deleteStore(int id);

    List<Store> getStoresByCategory(@Param("offset") int offset, @Param("size") int size, @Param("category") String category);

    boolean isStoreExist(String storeName, String storeAddress);

    Double getRatingAverage(int id);
}
