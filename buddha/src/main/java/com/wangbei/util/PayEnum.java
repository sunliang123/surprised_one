package com.wangbei.util;

/**
 * @author Created by yuyidi on 2017/6/27.
 * @desc
 */
public enum PayEnum implements CommonalityEnum {
    WECHAT(0, "微信"), ALIPAY(1, "支付宝");


    PayEnum(Integer index, String payment) {
        this.index = index;
        this.payment = payment;
    }

    private Integer index;
    private String payment;

    @Override
    public Integer getIndex() {
        return this.index;
    }
    public String getPayment() {
        return payment;
    }
}
