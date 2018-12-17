package jsu.sif.service;

import java.sql.SQLException;

import jsu.sif.dao.UserDao;
import jsu.sif.domain.User;

public class UserService {
	UserDao userDao = new UserDao();
	//注册
	public void regist(User user) {
		userDao.regist(user);
	}
	//登录
	public User login(String name, String pwd) throws SQLException {
		User user = userDao.login(name,pwd);
		return user;
	}
}
