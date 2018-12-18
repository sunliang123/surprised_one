package com.wangbei.dao.impl;

import com.wangbei.dao.MenuDao;
import com.wangbei.dao.impl.jpa.MenuRepository;
import com.wangbei.entity.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author yuyidi 2017-06-28 13:57:26
 * @class com.wangbei.dao.impl.MenuDaoImpl
 * @description 菜单持久层静态代理实现  主要是整合调用不同的orm框架
 */
@Repository
public class MenuDaoImpl implements MenuDao {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Menu retrieveMenuById(Long id) {
        return menuRepository.findById(id);
    }

    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu updateMenu(Menu menu) {
        logger.info("更新菜单");
        Menu result = menuRepository.save(menu);
        logger.info("结束更新菜单:{}", result);
        return result;
    }
}
