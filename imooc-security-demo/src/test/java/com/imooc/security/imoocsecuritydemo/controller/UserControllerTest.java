package com.imooc.security.imoocsecuritydemo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


/**
 * @auther: zpd
 * @Date: 2019/1/15 0015 16:29
 * @Description:
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserControllerTest {

    /*@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    *//**
     * 测试接口返回json对象数组长度为3
     * @throws Exception
     *//*
    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(3));
    }

    *//**
     * 测试Get方法user接口返回userName 是 uuz
     * @throws Exception
     *//*
    @Test
    public void whenGetInfoSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userName").value("uuz"));
    }

    *//**
     * 测试Get方法user接口只接收 int/long 型参数
     * @throws Exception
     *//*
    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/abc")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().is4xxClientError());
    }

    *//**
     * 测试JsonView视图配置测试数据，在使用全部查询时，不应该输出用户的密码
     * @throws Exception
     *//*
    @Test
    public void whenSimpleQuerySuccessResponse() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
        logger.info("--------------->Simple Json result: {}",result);
    }

    *//**
     * 测试JsonView视图配置测试数据，在使用详情查询时，应该输出用户的密码
     * @throws Exception
     *//*
    @Test
    public void whenDetailQuerySuccessResponse() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
        logger.info("--------------->Detail Json result: {}",result);
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        .content("{\"userName\":\"uuz\",\"password\":\"ym\"}"))
        .content("{\"userName\":\"uuz\",\"password\":null}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userId").value(10));
    }*/

}
