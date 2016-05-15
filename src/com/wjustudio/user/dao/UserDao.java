package com.wjustudio.user.dao;

import com.wjustudio.user.domain.User;


/**
 * 用户添加等
 * interface
 */
public interface UserDao {
	/**
	 * 添加用户
	 * @param user
	 */
	public int add(User user);
	
	
	/**
	 * 由用户的身份（姓名或邮箱）获取用户信息
	 * @param LoginIdentity
	 * @return
	 */
	public User getDbUser(String LoginIdentity);
	/**
	 * 重新输入密码
	 * @param userEmail
	 * @param userPassword
	 * @return
	 */
	public int resetPassword(String userEmail,String userPassword);
	
	/**
	 * 更新用户的checkCode
	 * @param email
	 * @param checkCode
	 * @return
	 */
	public int updateCheckCode(String email, String checkCode);

	/**
	 * 检验校验码
	 * @param userEmail
	 * @param checkCode
	 * @return
	 */
	public boolean isCheckCode(String userEmail,String checkCode);

	/**
	 * 更新头像信息
	 * @param name
	 * @param imgUrl
	 * @return
	 */
	public int updateUserImgUrl(String name, String imgUrl);
	
	
	/**
	 * 通过用户名获得userId
	 * @param name
	 * @return
	 */
	public int getUserId(String name);
}
