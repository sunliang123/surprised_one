package com.wangbei.awre;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.Properties;

/**
* @author yuyidi 2017-07-03 16:42:03
* @class com.wangbei.awre.DataSourceConfigure
* @description 关于数据源的一些配置(事务/查询顺序)
*/
@EnableTransactionManagement
@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE,
                        basePackages = "com.wangbei.dao.impl.jpa")
@Configuration
public class DataSourceConfigure {
    //query-lookup-strategy :
    // create               通过解析方法名查询  命名查询或者@Query查询都将被忽略
    // create-if-not-found  若标注@Query   则使用,若没有则查找符合条件的@NameQuery(命名查询),否则 就通过解析方法名查询
    // use-declared-query   若标注@Query   则使用,若没有则查找符合条件的@NameQuery(明明查询),否则 就报错

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory getSessionFactory() {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

}
