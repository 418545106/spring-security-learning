package com.imooc.security.imoocsecuritybrowser.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @auther: zpd
 * @Date: 2019/1/21 0021 10:44
 * @Description:
 */
@Mapper
@Repository
public interface UserDao {

    @Select("select password from user where name = #{userName}")
    String getUserByName(String userName);
}
