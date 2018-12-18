package com.wangbei.awre;

import com.wangbei.awre.jpa.CustomMergeEventListener;
import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerGroup;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author yuyidi 2017-07-05 17:08:58
 * @class com.wangbei.awre.ApplicationContextBeanFactory
 * @description applicationContext工具类
 */
@Component
public class ApplicationContextBeanFactory implements ApplicationContextAware {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private CustomMergeEventListener mergeEventListener;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        init();
    }

    public void init() {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
                EventListenerRegistry.class);
        EventListenerGroup group = registry.getEventListenerGroup(EventType.MERGE);
        group.clear();
        group.appendListener(mergeEventListener);
    }
}
