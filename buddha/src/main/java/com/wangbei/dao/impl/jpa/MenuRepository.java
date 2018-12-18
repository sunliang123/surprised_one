package com.wangbei.dao.impl.jpa;

import com.wangbei.entity.Menu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author yuyidi 2017-06-28 13:56:37
* @class com.wangbei.dao.impl.jpa.MenuRepository
* @description 菜单jpa接口
*/
public interface MenuRepository extends Repository<Menu,Long> {

    Menu findById(Long id);

    Menu save(Menu menu);

}
