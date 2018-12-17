package jsu.sif.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;


import jsu.sif.domain.User;
import jsu.sif.service.UserService;


/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.接收用户名和密码（使用映射关系）
//		String name = request.getParameter("username");
//		String pwd = request.getParameter("userpwd");
//		String country = request.getParameter("country");
//		String phone = request.getParameter("tel");
//		System.out.println(name+"   "+pwd);
		Map<String, String[]> parameterMap = (Map<String, String[]>)request.getParameterMap();
		System.out.println(parameterMap.keySet());
		User user = new User();
		try {
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		String param = request.getParameter("username"); 
        String name = new String(param.getBytes("ISO-8859-1"), "UTF-8"); 
		user.setUsername(name);
//		System.out.println(user.toString());
		user.setUid(UUID.randomUUID().toString());
		
		UserService userService = new UserService();
		userService.regist(user);
		//4.保存完成，跳转到首页
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}
}
