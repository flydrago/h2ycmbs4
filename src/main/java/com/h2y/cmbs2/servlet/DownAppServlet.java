package com.h2y.cmbs2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.h2y.util.HttpTookit;
import com.h2y.util.JSONUtil;
import com.h2y.util.ParamUtil;

/**
 * 使用Servlet进行文件下载

 * 作者：段晓刚

 * 时间：2013-2-1 下午12:05:30

 * 电子邮件：duanxg@hwttnet.com

 * QQ：2410960521

 * Gmail :
 */
public class DownAppServlet extends HttpServlet {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Constructor of the object.
	 */
	public DownAppServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 下载部分
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String t = request.getParameter("t");
		String y = request.getParameter("y"); 
		PrintWriter out = response.getWriter();
		String json = request.getParameter("params");
		//http://d.jydapp.com:88/cmbs/servlet/AppDownloadServlet?y=jk&t=1
		if (t.equals("1")) {
			response.sendRedirect("http://d.jydapp.com:88/cmbs/down.html");
		}else if (t.equals("0")) {
			if (json == null || json.equals("")) {
				response.sendRedirect("http://d.jydapp.com:88/cmbs/down.html");
			}else{
				Map<String, Object> postMap = JSONUtil.getMap(json);
				postMap.put("uuid", y);
				String result = HttpTookit.doPost(ParamUtil.os_user_url, postMap);
				out.print(result);
			}
		}
	}

	/**
	 * 初始化方法
	 * @throws ServletException
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}