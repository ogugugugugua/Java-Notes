package springboot.chapter2.aspect.Impl;

import springboot.chapter2.aspect.UserValidator;
import springboot.chapter2.pojo.User;

public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean validate(User user) {
        System.out.println("引入新的接口 " + UserValidator.class.getSimpleName());
        return null != user;
    }
}
