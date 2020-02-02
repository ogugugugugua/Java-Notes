package dao;

import com.yulin.dao.UserMapper;
import com.yulin.pojo.User;
import com.yulin.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

public class UserMapperTest {

    static Logger logger = Logger.getLogger(UserMapperTest.class);


    @Test
    public void getUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.getUserById(1);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void testLog4j(){
        logger.info("info: testLog4j");
        logger.warn("warn: testLog4j");
        logger.debug("debug: testLog4j");
        logger.error("error: testLog4j");
    }

}
