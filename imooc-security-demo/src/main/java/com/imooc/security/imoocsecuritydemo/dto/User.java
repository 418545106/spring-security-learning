package com.imooc.security.imoocsecuritydemo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.security.imoocsecuritydemo.vaildator.MyConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

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

    public interface UserSimpleView{};

    public interface UserDetailView extends UserSimpleView{};

    @JsonView(UserSimpleView.class)
    private Long userId;

    @MyConstraint(message = "This is a test")
    @JsonView(UserSimpleView.class)
    private String userName;

    @NotBlank
    @JsonView(UserDetailView.class)
    private String password;
}
