package com.wjustudio.common;

/**
 * 项目用到的常数
 *
 */
public class ProjectConstant {

	//发送邮件相关
	//发送邮箱的地址
	public static final String EMAIL_FROM = "xxx@163.com";
	//发送邮箱的名称，@之前
	public static final String EMAIL_USER = "xxx";
	//发送邮箱的密码
	public static final String EMAIL_PWD = "xxx";
	public static final String EMAIL_REGISTER_TEXT = 
			"<p>亲爱的用户，您好！感谢您注册手机管家，希望我们的产品能带给您快乐，让您的生活更加的多姿多彩。</p><p>随手涂鸦</p>";
	public static final String EMAIL_REGISTER_TITLE = "手机管家:注册";
	public static final String EMAIL_FINDPWD_TEXT = "亲爱的用户：您好！您此次重置密码的验证码如下，如非本人操作，请忽略此文件</ br>";
	public static final String EMAIL_FINDPWD_TITLE = "手机管家:找回密码校验码";
	public static final String EMAIL_PORT = "smtp.163.com";//或smtp.qq.com

	//数据库相关

	//加载驱动
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	//数据库的链接
	public static final String DB_URL = "jdbc:mysql://xxx:3306/bike";
	//数据库用户名	
	public static final String DB_USER_NAME = "xxx";
	//数据库password
	public static final String DB_PASSWORD = "xxx";
	//数据库表名
	public static final String USER_TABLE = "phone_manager_user";
	
	public static final String USER_CONTACT_TABLE = "phone_manager_contect";
	
	public static final String BASE_URL = "http://www.wjustudio.com/PhoneManagerServer/";
//	public static final String BASE_URL = "http://192.168.191.1/PhoneManagerServer/";
}
