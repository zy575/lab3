package cn.edu.ujn.lab3.dao;

import org.apache.ibatis.annotations.Param;

import cn.edu.ujn.lab3.dao.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);
    
    public User findUser(@Param("usercode") String usercode,	@Param("password") String password);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}