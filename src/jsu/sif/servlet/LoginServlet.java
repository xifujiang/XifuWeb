package jsu.sif.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey.On;

import jsu.sif.domain.User;
import jsu.sif.service.UserService;
import jsu.sif.util.JdbcUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.先匹配验证码是否正确
		
		//1.1接收账号，密码，匹配是否相同
		//1.2 相同，跳转到首页
		//1.3不相同，提示+跳回登录界面
		//2.验证码出错，重新跳回登录界面
		//设置请求编码
		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		
		//获取参数验证码
		String code =request.getParameter("code");
		System.out.println("code:"+code);
		//获取服务器生成的验证码 checkCode是在全局变量里
		String word = (String) request.getSession().getAttribute("checkcode_session");
		//String word = (String) this.getServletContext().getAttribute("checkCode");
		System.out.println("word="+word);
		//判断输入的验证(word存在session里)
		if(code.equals(word)){
			//如果正确
			//1.接收所有参数
			String name = request.getParameter("username");
			String pwd = request.getParameter("password");
			System.out.println("验证码输入正确");

			//4.写到数据库
			UserService userService = new UserService();
			try {
				User user = userService.login(name,pwd);
				if(user!=null){
					//保存用户信息
					String is =request.getParameter("isRemember");
					if("on".equals(is)){
						HttpSession session = request.getSession();
						session.setAttribute("user", user);
//						ServletContext content = this.getServletContext();
//						content.setAttribute("name", user);
						
						System.out.println("存储成功"+user.toString());
					}
					System.out.println("登录成功");
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}else{
					//跳转到登录页面
					System.out.println("账号密码不匹配");
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}else{
			//不正确，告诉用户验证码错误
			response.getWriter().write("验证码错误");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}
}
