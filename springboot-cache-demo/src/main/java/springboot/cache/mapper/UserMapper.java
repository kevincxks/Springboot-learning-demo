package springboot.cache.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import springboot.cache.model.User;

import java.util.List;


public interface UserMapper {

    @Select("select * from user")
    List<User> list();

    @Select("select * from user where id = #{id}")
    User findUserById(@Param("id") Integer id);
}
