package com.h2y.cmbs2.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.h2y.util.FileUploadKey;
import com.h2y.util.JSONUtil;
import com.h2y.util.PropertiesUtil;

public class WbsUploadServlet extends HttpServlet {


	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 1L;

	private  Logger logger = Logger.getLogger(WbsUploadServlet.class);

//	private static String base_savePath = "F:/deliveryLog/upload/";
	private static String base_savePath = "/opt/nfs/upload/logger/";


	/**
	 * Constructor of the object.
	 */
	public WbsUploadServlet() {
		super();
		PropertiesUtil mPropertiesUtil = PropertiesUtil.getInstance("/config.properties");
		if(mPropertiesUtil!=null){
			String sp = mPropertiesUtil.getValueByKey("upload.path");
			if(sp!=null && !"null".equals(sp) && !"".equals(sp))
				base_savePath = sp;
		}
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
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text;charset=UTF-8");//内容类型

		String pathType= request.getParameter(FileUploadKey.FILEUPLOAD_PATHTYPE);
		@SuppressWarnings("unused")
		String userkey = request.getParameter("userKey")+"";
		String account =  request.getParameter("account")+"";
		String module = request.getParameter("module");
		if(module==null || "".equals(module))
			module = "";
		
		//System.out.println("POST= ");
		PrintWriter out = response.getWriter();
		Map<String,Object> resultMap = new HashMap<String, Object>();


		String fileName = "";
		String saveName = "";
		Calendar now = Calendar.getInstance();  
		//根目录
		String sp = base_savePath+module;
		String savePath = sp+File.separator+ now.get(Calendar.YEAR)
				+File.separator+ (now.get(Calendar.MONTH) + 1)+""
				+File.separator+ now.get(Calendar.DAY_OF_MONTH);

		if(pathType!=null && !pathType.trim().equals(""))
			savePath = savePath+File.separator+pathType;
		savePath = savePath + File.separator;

		File file = new File(savePath);
		if(!file.exists())
			file.mkdirs();
		/** 
		 * 在isMultipartContent方法中同时检测了是否是post提交 
		 * 如果不是post提交则返回false 
		 */ 
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			try {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				@SuppressWarnings("unchecked")
				List<FileItem> items = upload.parseRequest(request);
				// 从request中解析出若干表单项
				Iterator<FileItem> iter = items.iterator();

				String oldFileName = "";
				while (iter.hasNext()) {// 遍历每一个表单项
					FileItem item = iter.next();
					if (item.isFormField()) {// 如果是普通字段
						String fieldName = item.getFieldName();// 获取字段名(name)
						String value = item.getString();// 获取字段值(value)
						//为解决httmime实现上传中文乱码问题
						if(fieldName!=null && fieldName.equals("httpmime_fileName")){
							oldFileName = value;
						}
					} else { // 不是普通字段, 就是上传文件
						//为解决httmime实现上传中文乱码问题
						if(oldFileName.equals(""))
							fileName = new File(item.getName()).getName();// 获取上传文件的文件名(有些浏览器可能会带着路径)
						else {
							fileName = oldFileName;
							oldFileName="";
						}
						//if("logger".equals(pathType))
						//	saveName = account+"_"+fileName;
						//else
						//	saveName = getNewFileName(fileName);
						saveName = account+"_"+fileName;
						File uploadedFile = new File(savePath, saveName);// 在Upload文件夹中创建文件
						item.write(uploadedFile);// 写出数据
					}
				}
				resultMap.put(FileUploadKey.FILEUPLOAD_RESULT, FileUploadKey.FILEUPLOAD_SUCCESS);
				resultMap.put(FileUploadKey.FILEUPLOAD_FILENAME, fileName);
				resultMap.put(FileUploadKey.FILEUPLOAD_SAVEPATH, savePath.replace(sp, "").replace(saveName, ""));
				resultMap.put(FileUploadKey.FILEUPLOAD_SAVENAME, saveName);
				String resultStr = JSONUtil.getJson(resultMap);
				out.println(resultStr);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e.getMessage(),e);
			}
		}
		resultMap.put(FileUploadKey.FILEUPLOAD_RESULT, FileUploadKey.FILEUPLOAD_FAIL);
		resultMap.put(FileUploadKey.FILEUPLOAD_FILENAME, fileName);
		String resultStr = JSONUtil.getJson(resultMap);
		out.println(resultStr);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	private String getNewFileName(String fileName){

		String fn;

		fn = System.currentTimeMillis()+"";
		if(fileName.contains("."))
			fn = fn + fileName.substring(fileName.lastIndexOf("."), fileName.length());
		else {
		}

		return fn;
	}
}