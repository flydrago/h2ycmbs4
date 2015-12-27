package com.h2y.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.h2y.cmbs2.basic.WbsKeys.SInvokeKeys;

/**
 * 项目名称：h2ycmbs2 Maven Webapp  
 * 类名称：LogUtil  
 * 类描述：日志工具类  
 * 创建人：侯飞龙  
 * 创建时间：2015年5月29日 下午4:59:58  
 * 修改人：侯飞龙
 * 修改时间：2015年5月29日 下午4:59:58  
 * 修改备注：  
 * @version
 */
public class LogUtil {
	
	private final static Logger logger = Logger.getLogger(LogUtil.class);

	public static void addLog(Map<String,Object> params,HttpServletRequest request,Date startDate,String result){
		
		try {
			
			Map<String,Object> reqParams = new HashMap<String, Object>();
			reqParams.putAll(params);
			String postData_str = reqParams.get(SInvokeKeys.postData.value())+"";
			
			Map<String,Object> postData = new HashMap<String, Object>();
			postData.put(SInvokeKeys.postData.value(), postData_str);
			
			if (null!=result && !result.equals("") && !result.equalsIgnoreCase("null")) {
				
				Map<String,Object> resultData = JSONUtil.getMap(result);
				postData.put(SInvokeKeys.resultFlag.value(), resultData.get(SInvokeKeys.resultFlag.value()));
				postData.put(SInvokeKeys.resultMsg.value(), resultData.get(SInvokeKeys.resultMsg.value()));
			}
			postData.put("startDate", startDate);
			postData.put("endDate", DateUtil.getSystemTime());
			postData.put("postUrl", request.getServletPath());
			postData.put("postIp", request.getRemoteAddr());
			reqParams.put(SInvokeKeys.postData.value(), JSONUtil.getJson(postData));
			
			HttpTookit.doPost(ParamUtil.os_log_url+"log/addOsLog.htm", reqParams);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 生成访问标识
	 * @return
	 */
	public static String getPostMark(){
		
		String postMark="";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Random random = new Random();
		String randomCode = "";
		postMark=df.format(new Date());
		for ( int i = 0; i < 6; i++ ){
			randomCode += Integer.toString(random.nextInt(9));
		}
		postMark+=randomCode;
		return postMark;
	}
}
