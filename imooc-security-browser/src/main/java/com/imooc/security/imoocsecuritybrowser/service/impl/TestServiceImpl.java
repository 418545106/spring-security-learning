package com.imooc.security.imoocsecuritybrowser.service.impl;

import com.imooc.security.imoocsecuritybrowser.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @auther: zpd
 * @Date: 2019/1/21 0021 11:51
 * @Description:
 */
@Service
public class TestServiceImpl implements TestService {

    private Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public String testFunction() {
        logger.info("###############This is other model");
        return "browser";
    }
}
