package com.example.demo.src.menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenusByStoreId(String storeId);
    int saveMenu(Menu menu);
    void deleteMenu(String menuId);
}