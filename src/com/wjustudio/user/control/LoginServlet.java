package com.wjustudio.user.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjustudio.user.dao.impls.UserImpls;
import com.wjustudio.user.domain.User;

/**
 * 用户登录业务逻辑类
 * 判断用户登录
 * 
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String loginUser= request.getParameter("loginUser");
		//request.getSession().setAttribute("userName", loginUser);
		String loginPwd = request.getParameter("loginPwd");
		System.out.println("loginUser:"+loginUser +" loginPwd:"+loginPwd);
		UserImpls impls = new UserImpls();
		User dbUser = impls.getDbUser(loginUser);
		if (dbUser == null) {
			System.out.println("用户不存在");
			response.getOutputStream().write("1".getBytes("UTF-8"));
		}else {
			if (dbUser.getPassword().equals(loginPwd)) {
				System.out.println("登录成功");
				String path = getJson(2,dbUser.getHeadImgUrl());
				response.getOutputStream().write(path.getBytes("UTF-8"));
			}else {
				System.out.println("密码错误");
				response.getOutputStream().write("3".getBytes("UTF-8"));
			}
		}	
		
	}
		
	private String getJson(int i, String headImgUrl) {
		
		return "{code:2,headImgUrl:'"+headImgUrl+"'}";
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
