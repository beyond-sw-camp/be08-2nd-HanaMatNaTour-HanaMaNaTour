package com.example.demo.src.menu;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> selectMenusByStoreId(int storeId);
    int insertMenu(Menu menu);
    int updateMenu(Menu menu);
    void deleteMenu(int menuId);
}
