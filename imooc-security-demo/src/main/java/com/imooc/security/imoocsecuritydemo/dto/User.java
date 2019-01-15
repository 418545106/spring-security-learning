package com.imooc.security.imoocsecuritydemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @auther: zpd
 * @Date: 2019/1/15 0015 17:14
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private String password;
}
