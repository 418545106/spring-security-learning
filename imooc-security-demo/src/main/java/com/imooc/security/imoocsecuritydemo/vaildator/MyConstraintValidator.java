package com.imooc.security.imoocsecuritydemo.vaildator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @auther: zpd
 * @Date: 2019/1/16 0016 15:30
 * @Description:
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {
    /**
     * 校验器初始化时做的工作
     * @param constraintAnnotation
     */
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("initValidator");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        System.out.println(o);

        return false;
    }
}
