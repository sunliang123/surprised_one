package com.wangbei.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Created by yuyidi on 2017/6/28.
 * @desc
 */
public class SystemControllerTest extends BaseControllerTest {

    @Test
    public void welcome() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/system/")
                .param("payEnum", "1").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("支付宝")));
    }

    @Test
    public void index() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk());
    }

}