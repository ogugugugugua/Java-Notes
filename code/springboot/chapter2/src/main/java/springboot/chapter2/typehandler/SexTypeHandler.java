package springboot.chapter2.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import springboot.chapter2.pojo.SexEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)  //声明JdbcType为数据库的整型
@MappedTypes(value = SexEnum.class) //声明JavaType为SexEnum
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {
    /**
     * 设置非空性别参数
     *
     * @param preparedStatement
     * @param i
     * @param sexEnum
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, sexEnum.getId());
    }

    /**
     * 根据列名读取性别
     *
     * @param resultSet
     * @param s         1/2
     * @return SexEnum
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int sex = resultSet.getInt(s);
        if (sex != 1 && sex != 2) {
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    /**
     * 根据下标读取性别
     *
     * @param resultSet
     * @param i         1/2
     * @return SexEnum
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int sex = resultSet.getInt(i);
        if (sex != 1 && sex != 2) {
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    /**
     * 根据存储过程读取性别
     *
     * @param callableStatement
     * @param i                 1/2
     * @return SexEnum
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int sex = callableStatement.getInt(i);
        if (sex != 1 && sex != 2) {
            return null;
        }
        return SexEnum.getEnumById(sex);
    }
}
