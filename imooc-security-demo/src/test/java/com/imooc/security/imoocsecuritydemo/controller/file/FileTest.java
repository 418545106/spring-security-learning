package com.imooc.security.imoocsecuritydemo.controller.file;

import com.imooc.security.imoocsecuritydemo.controller.UserControllerTest;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @auther: zpd
 * @Date: 2019/1/18 0018 14:22
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}
