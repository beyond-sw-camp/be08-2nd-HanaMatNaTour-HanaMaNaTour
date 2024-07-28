package com.example.demo.src.menu;

import com.example.demo.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.response.BaseResponseStatus.NOT_FOUND_METHOD_ERROR;
import static com.example.demo.common.response.BaseResponseStatus.SUCCESS;

@RestController
@RequestMapping("/store/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "스토어 ID로 메뉴 조회")
    @GetMapping("/{storeId}")
    public BaseResponse<List<Menu>> getMenusByStoreId(
            @Parameter(description = "Store ID", example = "1") @PathVariable String storeId) {
        List<Menu> menus = menuService.getMenusByStoreId(storeId);
        if (menus != null && !menus.isEmpty()) {
            return new BaseResponse<>(menus);
        } else {
            return new BaseResponse<>(NOT_FOUND_METHOD_ERROR);
        }
    }

    @Operation(summary = "메뉴 생성")
    @PostMapping("/create")
    public BaseResponse<Menu> createMenu(@RequestBody MenuRequestDto requestDto) {
        Menu menu = new Menu(requestDto);
        menuService.saveMenu(menu);
        return new BaseResponse<>(menu);
    }

    @Operation(summary = "메뉴 수정")
    @PostMapping("/update")
    public BaseResponse<Menu> updateMenu(@RequestBody MenuRequestDto requestDto) {
        Menu menu = new Menu(requestDto);
        menuService.saveMenu(menu);
        return new BaseResponse<>(menu);
    }

    @Operation(summary = "메뉴 삭제")
    @DeleteMapping("/delete/{menuId}")
    public BaseResponse<Void> deleteMenu(
            @Parameter(description = "Menu ID", example = "1") @PathVariable String menuId) {
        menuService.deleteMenu(menuId);
        return new BaseResponse<>(SUCCESS);
    }
}