package com.wjustudio.user.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjustudio.user.dao.UserContactDao;
import com.wjustudio.user.dao.UserDao;
import com.wjustudio.user.dao.impls.UserContactImpls;
import com.wjustudio.user.dao.impls.UserImpls;
import com.wjustudio.user.domain.UserContact;

/**
 * 用户登录业务逻辑类
 * 判断用户登录
 * 
 */
public class GetContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String userName= request.getParameter("userName");
		UserDao userDao = new UserImpls();
		int userId = userDao.getUserId(userName);
		System.out.println("userName:"+userName +" userId:"+userId);
		UserContactDao userContactDao = new UserContactImpls();
		List<UserContact> userContacts = userContactDao.getAllContacts(userId);
		String contactsJson = getJson(userContacts);
		response.getOutputStream().write(contactsJson.getBytes("UTF-8"));
	}
		
	private String getJson( List<UserContact> userContacts) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("{ 'userContact':[");
		for (UserContact userContact : userContacts) {
			sBuilder.append("{'jsonUrl':'")
			.append(userContact.getContactJsonUrl())
			.append("'},");
			
		}
		sBuilder.deleteCharAt(sBuilder.toString().lastIndexOf(","));
		sBuilder.append("]}");
		System.out.println("sBuilder:"+sBuilder.toString());
		return sBuilder.toString();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
