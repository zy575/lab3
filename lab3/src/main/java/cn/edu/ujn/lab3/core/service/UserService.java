package cn.edu.ujn.lab3.core.service;

import cn.edu.ujn.lab3.dao.User;

public interface UserService {

	// 通过账号和密码查询用户
		public User findUser(String usercode,String password);
}
