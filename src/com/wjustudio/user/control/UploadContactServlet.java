package com.wjustudio.user.control;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.wjustudio.common.ProjectConstant;
import com.wjustudio.user.dao.UserContactDao;
import com.wjustudio.user.dao.impls.UserContactImpls;
import com.wjustudio.user.dao.impls.UserImpls;
import com.wjustudio.user.domain.UserContact;

public class UploadContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String path;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
	
		
		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 设置文件上传路径
		String upload = this.getServletContext().getRealPath("\\");
		System.out.println("upload:"+upload);
		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("java.io.tmpdir");
		// 设置缓冲区大小为 5M
		factory.setSizeThreshold(1024 * 1024 * 5);
		// 设置临时文件夹为temp
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		String result;
		String fileName = null ;
		String userName = null;
		// 解析结果放在List中
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);

			for (FileItem item : list) {
				String name = item.getFieldName();
				fileName = item.getName();
				System.out.println("name:"+name);
				System.out.println("fileName:"+fileName);
				if (fileName != null) {
					userName = getUserName(fileName);
					System.out.println("userName:"+userName);
				}
				InputStream is = item.getInputStream();
				
				if (name.equals("content")) {
					System.out.println(inputStream2String(is));
				} else if (name.equals("json")) {
					try {
						path = upload+"\\"+item.getName();
						inputStream2File(is, path);
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("path:"+path);
			String serverPath = ProjectConstant.BASE_URL+fileName;
			
			UserImpls impls = new UserImpls();
			int userId = impls.getUserId(userName);
			//写入数据库
			UserContactDao userContactDao = new UserContactImpls();
			userContactDao.add(new UserContact(userId, serverPath));
		    result = getGsonString(1,serverPath);
		    
		    System.out.println("result:"+result);
			out.write(result);  //这里我把服务端成功后，返回给客户端的是上传成功后路径
		} catch (FileUploadException e) {
			e.printStackTrace();
			result = getGsonString(0, "failure");
			System.out.println(result);
			out.write(result);
		}

		out.flush();
		out.close();
	}

	private String getUserName(String name) {
		
		return name.substring(0, name.indexOf("_"));
	}

	private String getGsonString(int status,String path) {
		String result = "";
		if (status == 0) {
			result = "{'status':'0','statusMessage':'上传失败'," +
					"'fileUrl':'"+path+"'}";
			
		}else if (status == 1) {
			
			result = "{'status':'1','statusMessage':'上传成功'," +
					"'fileUrl':'"+path+"'}";
		}
		
		return result;
	}

	// 流转化成字符串
	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	// 流转化成文件
	public static void inputStream2File(InputStream is, String savePath) throws Exception {
		System.out.println("文件保存路径为:" + savePath);
		File file = new File(savePath);
		InputStream inputSteam = is;
		BufferedInputStream fis = new BufferedInputStream(inputSteam);
		FileOutputStream fos = new FileOutputStream(file);
		int f;
		while ((f = fis.read()) != -1) {
			fos.write(f);
		}
		fos.flush();
		fos.close();
		fis.close();
		inputSteam.close();
	}

}
