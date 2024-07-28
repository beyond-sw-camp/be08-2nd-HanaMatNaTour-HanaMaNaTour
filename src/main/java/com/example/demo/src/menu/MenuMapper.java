package com.example.demo.src.menu;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> selectMenusByStoreId(String storeId);
    int insertMenu(Menu menu);
    int updateMenu(Menu menu);
    void deleteMenu(String menuId);
}
