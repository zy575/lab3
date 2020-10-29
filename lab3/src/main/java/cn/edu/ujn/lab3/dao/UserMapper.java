package cn.edu.ujn.lab3.dao;

import cn.edu.ujn.lab3.dao.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);
    
    User findUser(String usercode, String password);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}