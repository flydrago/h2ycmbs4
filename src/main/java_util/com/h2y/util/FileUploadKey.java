package com.h2y.util;

public class FileUploadKey{

	/**
	 * 文件上传成功失败标记
	 */
	public static String FILEUPLOAD_RESULT = "FILEUPLOAD_RESULT";

	/**
	 * 文件上传成功失败标记
	 */
	public static int FILEUPLOAD_SUCCESS = 1;

	/**
	 * 文件上传成功失败标记
	 */
	public static int FILEUPLOAD_FAIL = 0;

	/**
	 * 文件不存在
	 */
	public static int FILEUPLOAD_NOTEXISTS = -3;

	/**
	 * 文件过大
	 */
	public static int FILEUPLOAD_OVERSIZE = 2;

	/**
	 * 授权失败
	 */
	public static int FILEUPLOAD_ACCESSERROR = -1;

	/**
	 * 上传文件名
	 */
	public static String FILEUPLOAD_FILENAME = "FILENAME";

	/**
	 * 保存路径
	 */
	public static String FILEUPLOAD_SAVEPATH = "SAVEPATH";

	/**
	 * 文件保存名字
	 */
	public static String FILEUPLOAD_SAVENAME = "SAVENAME";

	/**
	 * 文件地址类型
	 */
	public static String FILEUPLOAD_PATHTYPE = "PATHTYPE";
}