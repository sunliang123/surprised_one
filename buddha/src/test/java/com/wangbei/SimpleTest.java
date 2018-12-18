package com.wangbei;

import org.hibernate.bytecode.instrumentation.spi.LazyPropertyInitializer;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Created by yuyidi on 2017/7/4.
 * @desc
 */
public class SimpleTest {

    @Test
    public void testLazy() {
        System.out.println(LazyPropertyInitializer.UNFETCHED_PROPERTY);
    }

    @Test
    public void testSub() {
        BigDecimal b1 = new BigDecimal(-1);
        BigDecimal b2 = new BigDecimal(10);
        System.out.println(b1.divide(b2).doubleValue());
    }
}
