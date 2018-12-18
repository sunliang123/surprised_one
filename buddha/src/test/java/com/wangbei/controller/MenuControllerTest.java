package com.wangbei.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Created by yuyidi on 2017/6/28.
 * @desc
 */
public class MenuControllerTest extends BaseControllerTest {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void fetchMenu() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/menu/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void additionMenu() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/menu/")
                .param("name", "测试")
                .param("level", "1")
                .param("createTime", sdf.format(new Date()))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void modificationMenu() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/menu/")
                .param("id", "12")
                .param("name", "测试菜单")
                .param("level", "3")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(12))
                .andExpect(jsonPath("$.name").value("测试菜单"));
    }
}