package com.wangbei.controller;

import com.wangbei.entity.Menu;
import com.wangbei.service.MenuService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/6/28.
 * @desc
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    public MenuService menuService;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
    * @author yuyidi 2017-07-05 17:05:29
    * @method fetchMenu
    * @param id
    * @return com.wangbei.entity.Menu
    * @description 根据菜单id获取菜单信息
    */
    @GetMapping("/{id}")
    public Menu fetchMenu(@PathVariable Long id) {
        return menuService.getMenuInfo(id);
    }

    /**
    * @author yuyidi 2017-07-05 17:05:13
    * @method additionMenu
    * @param menu
    * @return com.wangbei.entity.Menu
    * @description 添加菜单
    */
    @PostMapping("/")
    public Menu additionMenu(Menu menu) {
        return menuService.addMenu(menu);
    }

    /***
    * @author yuyidi 2017-07-05 17:04:49
    * @method modificationMenu
    * @param menu
    * @return com.wangbei.entity.Menu
    * @description 修改菜单
    */
    @PutMapping("/")
    public Menu modificationMenu(Menu menu) {
        return menuService.modifyMenu(menu);
    }

    /**
    * @author yuyidi 2017-07-05 17:06:44
    * @method menus
    * @param
    * @return java.util.List<com.wangbei.entity.Menu>
    * @description 菜单列表
    */
    public List<Menu> menus() {
        return null;
    }
}
