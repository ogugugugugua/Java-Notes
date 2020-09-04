package springboot.chapter2.aspect;

import springboot.chapter2.pojo.User;

public interface UserValidator {
    public boolean validate(User user);
}
