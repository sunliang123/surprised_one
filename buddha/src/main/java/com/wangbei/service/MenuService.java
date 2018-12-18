package com.wangbei.service;

import com.wangbei.dao.MenuDao;
import com.wangbei.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuyidi 2017-06-28 14:00:31
 * @class com.wangbei.service.MenuService
 * @description 菜单业务实现层
 */
@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    public Menu getMenuInfo(Long id) {
        return menuDao.retrieveMenuById(id);
    }

    @Transactional
    public Menu addMenu(Menu menu) {
        return menuDao.createMenu(menu);
    }

    @Transactional
    public Menu modifyMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }
}
