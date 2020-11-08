package cn.edu.ujn.lab3.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.edu.ujn.lab3.core.service.UserService;
import cn.edu.ujn.lab3.dao.User;
import cn.edu.ujn.lab3.dao.UserMapper;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userDao;

	@Override
	public User findUser(String usercode, String password) {
		// TODO Auto-generated method stub
		User user=this.userDao.findUser(usercode,password);
		return user;
		
		
	}


}
