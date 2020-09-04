package springboot.chapter2.service.Impl;

import org.springframework.stereotype.Service;
import springboot.chapter2.pojo.User;
import springboot.chapter2.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void print(User user) {
        System.out.println(user);
        if (user.getUserName().equals(" ") || null == user.getUserName())
            throw new RuntimeException("exception.");
    }
}
