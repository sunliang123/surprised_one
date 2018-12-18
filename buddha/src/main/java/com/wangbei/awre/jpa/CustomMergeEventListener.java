package com.wangbei.awre.jpa;

import org.hibernate.bytecode.instrumentation.spi.LazyPropertyInitializer;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.jpa.event.internal.core.JpaMergeEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.property.access.internal.PropertyAccessStrategyBackRefImpl;
import org.hibernate.type.AssociationType;
import org.hibernate.type.ForeignKeyDirection;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Created by yuyidi on 2017/7/4.
 * @desc
 */
@Component
public class CustomMergeEventListener extends JpaMergeEventListener {

    @Override
    protected void copyValues(EntityPersister persister, Object entity, Object target, SessionImplementor source, Map
            copyCache) {
        Object[] copiedValues = replace(persister.getPropertyValues(entity), persister.getPropertyValues(target),
                persister.getPropertyTypes(), source, target, copyCache);
        persister.setPropertyValues(target, copiedValues);
    }


    private Object[] replace(Object[] original, Object[] target, Type[] types, SessionImplementor session, Object
            owner, Map copyCache) {
        Object[] copied = new Object[original.length];
        for (int i = 0; i < types.length; ++i) {
            if (original[i] != LazyPropertyInitializer.UNFETCHED_PROPERTY && original[i] !=
                    PropertyAccessStrategyBackRefImpl.UNKNOWN) {
                if (target[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY) {
                    if (types[i].isMutable()) {
                        copied[i] = types[i].deepCopy(original[i], session.getFactory());
                    } else {
                        copied[i] = original[i];
                        // 若 original 为空  target 不为空 则将target 中的数值赋值到新的 copied 数组中
                        if (original[i] == null && target[i] != null) {
                            copied[i] = target[i];
                        }
                    }
                } else {
//                    copied[i] = types[i].replace(original[i], target[i], session, owner, copyCache);
                    copied[i] = original[i];
                    if (original[i] == null && target[i] != null) {
                        copied[i] = target[i];
                    }
                }
            } else {
                copied[i] = target[i];
            }
        }
        return copied;
    }



 }
