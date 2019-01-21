package com.imooc.security.imoocsecuritybrowser.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @auther: zpd
 * @Date: 2019/1/21 0021 10:47
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private String name;
    private String password;
}
