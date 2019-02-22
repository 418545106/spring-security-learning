package com.imooc.security.imoocsecuritydemo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.security.imoocsecuritycore.properties.SecurityProperties;
import com.imooc.security.imoocsecuritydemo.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zpd
 * @Date: 2019/1/15 0015 17:11
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

//    @Autowired
//    private TestService testService;

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(){
        List<User> users = new ArrayList<>();
        users.add(new User(1L,"uuz","1"));
        users.add(new User(2L,"uuz","1"));
        users.add(new User(3L,"uuz","1"));
        return users;
    }

    @GetMapping("/{userId:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable Long userId) throws Exception {

        logger.info("--method parameter:{}",userId);

        User user =   new User();
        user.setPassword("123456");
        user.setUserName("uuz");
        return user;
    }

    @PostMapping
    public User create(/*@Valid*/ @RequestBody User user/*, BindingResult errors*/){

        /*if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
        }*/

        logger.info("userName:{}",user.getUserName());
        logger.info("password:{}",user.getPassword());

        user.setUserId(10L);
        return user;
    }

    @PostMapping("/{userId:\\d+}")
    public void update(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fe = (FieldError)error;
                System.out.println(fe.getField() + " : " + error.getDefaultMessage());
            });
        }

        logger.info("userName:{}",user.getUserName());
        logger.info("password:{}",user.getPassword());

        user.setUserId(10L);
    }

    @DeleteMapping("/{userId:\\d+}")
    public void delete(@PathVariable Long userId){
        logger.info("--------------->delete id : {}",userId);
    }

//    @GetMapping("/test")
//    public void test(){
//        String str = testService.testFunction();
//        logger.info("###################### {}",str);
//    }

    @GetMapping("/me")
    public Object me(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {

        String hedder = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(hedder,"bearer ");

        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
            .parseClaimsJws(token).getBody();

        String company = claims.get("company").toString();

        logger.info("--> company : {}",company);

        return user;
    }
}
