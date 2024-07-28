package com.example.demo.src.menu;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<Menu> getMenusByStoreId(String storeId) {
        return menuMapper.selectMenusByStoreId(storeId);
    }

    @Override
    public int saveMenu(Menu menu) {

        int result = 0;

        if (menu.getId() != null) {
            // update
            result = menuMapper.updateMenu(menu);
        } else {
            // insert
            result = menuMapper.insertMenu(menu);
        }

        return result;
    }

    @Override
    public void deleteMenu(String menuId) {
        menuMapper.deleteMenu(menuId);
    }
}