package jsu.sif.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import jsu.sif.domain.User;
import jsu.sif.util.JdbcUtil;

public class UserDao {
	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	//用户注册
	public void regist(User user) {
		String sql = "insert into user values(?,?,?,?,?)";
		try {
			qr.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getCountry(),user.getPhone());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//用户登录
	public User login(String name, String pwd) throws SQLException {
		//2.查询
		String sql = "select * from user where username=? and password=?";		
		String sql1 = "select * from user where phone=? and password=?";		
		//3.执行查询
		User user = null;
		user = qr.query(sql, new BeanHandler<User>(User.class),name,pwd);
		if(user==null)
			user = qr.query(sql1, new BeanHandler<User>(User.class),name,pwd);
		//返回查询结果
		return user;
	}
}
