package com.example.demo.src.favoriteList.mapper;

import com.example.demo.src.favoriteList.dto.FavoriteListRequest;
import com.example.demo.src.favoriteList.dto.FavoriteListResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ListMapper {

    void createFavoriteList(@Param("userUuid") String userUuid, @Param("favoriteListRequest") FavoriteListRequest favoriteListRequest);

    void deleteFavoriteList(@Param("userUuid") String userUuid, @Param("listId") int listId);

    void addStoreToFavoriteList(@Param("listId") int listId, @Param("storeId") int storeId);

    void removeStoreFromFavoriteList(@Param("listId") int listId, @Param("storeId") int storeId);

    List<FavoriteListResponse> getUserFavoriteLists(@Param("userUuid") String userUuid);

    FavoriteListResponse getFavoriteListById(@Param("listId") int listId);

    String getOwnerByListId(@Param("listId") int listId);

    List<FavoriteListResponse> getFavoriteListsAll(@Param("offset") int offset, @Param("size") int size);

}
