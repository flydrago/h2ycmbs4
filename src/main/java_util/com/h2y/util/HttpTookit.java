package com.h2y.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.h2y.cmbs2.basic.CenterModule;
import com.h2y.cmbs2.basic.WbsKeys.SInvokeKeys;


/**
 * 模拟进行Http请求

 * @author：段晓刚

 * @update：2014年6月12日 下午6:18:34

 * @Email：Java198921@163.com
 */
public final class HttpTookit {
	
	private final static Logger logger = Logger.getLogger(HttpTookit.class);

	private static RequestConfig.Builder getRequestConfig(){

		RequestConfig.Builder config = RequestConfig.custom();
		//连接超时
		config.setConnectTimeout(30000);
		//请求超时
		config.setSocketTimeout(30000);
		return config;
	}

	/**
	 * 模拟使用get请求
	 * 多参数数据提交
	 * @param url
	 * @param params	Map中的键值作为键值使用
	 * @return
	 * @update：2014年7月24日 下午4:35:55
	 */
	public static String doGet(String url,Map<String,Object> params) {
		String serverResponse = "";

		String paramstr = "";
		if(params!=null && !params.isEmpty()){
			Set<Map.Entry<String, Object>> set = params.entrySet();
			Iterator<Map.Entry<String, Object>> it = set.iterator();

			while(it.hasNext()){
				Map.Entry<String, Object> entry = it.next();
				if(paramstr.equals("")){
					paramstr = entry.getKey()+"="+entry.getValue();
				}else {
					paramstr+="&"+entry.getKey()+"="+entry.getValue();
				}
			}
		}
		// 新建HttpPost对象
		HttpGet httpGet = new HttpGet(paramstr.equals("")?url:url+"?"+paramstr);
		// 获取HttpClient对象
		HttpClient httpClient = HttpClients.createDefault();//new DefaultHttpClient();

		httpGet.setConfig(getRequestConfig().build());

		// 获取HttpResponse实例
		HttpResponse httpResp = null;
		try {
			httpResp = httpClient.execute(httpGet);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// 判断是够请求成功
		if (httpResp.getStatusLine().getStatusCode() == 200) {
			// 获取返回的数据
			try {
				serverResponse = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		}

		return serverResponse;
	}

	/**
	 * 模拟使用post请求
	 * 多参数数据提交
	 * @param url
	 * @param params	Map中的键值作为键值使用
	 * @return
	 * @update：2014年7月24日 下午4:36:30
	 */
	public static String doPost(String url,Map<String,Object> params) {
		String serverResponse = "";

		// 新建HttpPost对象
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		if(params!=null && !params.isEmpty()){
			Set<Map.Entry<String, Object>> set = params.entrySet();
			Iterator<Map.Entry<String, Object>> it = set.iterator();
			while(it.hasNext()){
				Map.Entry<String, Object> entry = it.next();
				params2.add(new BasicNameValuePair(entry.getKey(), entry.getValue()+""));
			}
		}
		// 设置字符集
		HttpEntity entity = new UrlEncodedFormEntity(params2, Charset.forName("UTF-8"));

		// 设置参数实体
		httpPost.setEntity(entity);
		// 获取HttpClient对象
		HttpClient httpClient = HttpClients.createDefault();//new DefaultHttpClient();

		httpPost.setConfig(getRequestConfig().build());

		// 获取HttpResponse实例
		HttpResponse httpResp = null;
		try {
			httpResp = httpClient.execute(httpPost);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// 判断是够请求成功
		if (httpResp.getStatusLine().getStatusCode() == 200) {
			// 获取返回的数据
			try {
				serverResponse = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		}

		return serverResponse;
	}


	/**
	 * 商品服务转发参数同一封装
	 * @param urlSuffix 访问url后缀
	 * @param request 当前请求
	 * @return
	 */
	public static String doGoodsPost(String urlSuffix,Map<String,Object> params) {

		return doPost(ParamUtil.os_goods_url+urlSuffix, params);
	}


	/**
	 * 订单服务转发参数同一封装
	 * @param urlSuffix 访问url后缀
	 * @param request 当前请求
	 * @return
	 */
//	public static String doOrderPost(String urlSuffix,Map<String,Object> params) {
//
//		return doPost(ParamUtil.os_order_url+urlSuffix, params);
//	}

	/**
	 * 会员服务转发参数同一封装
	 * @param urlSuffix
	 * @param params
	 * @return
	 */
	public static String doUserPost(String urlSuffix,Map<String,Object> params) {

		return doPost(ParamUtil.os_user_url+urlSuffix, params);
	}

	/**
	 * 调用服务
	 * @param moduleName 模块名称 （url路径中统配的第一个变量）
	 * @param methodName url路径中统配的第二个变量
	 * @param params 访问单数
	 * @return
	 */
	public static String doServicePost(String moduleName,String methodName,Map<String,Object> params,HttpServletRequest request) {

		String result = "";

		if (CenterModule.isLogService(moduleName)) {//日志服务
			
			try {
				
				result = doPost(ParamUtil.os_log_url+moduleName+"/"+methodName+".htm", params);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}else {
			
			Date startDate = DateUtil.getSystemTime();
			try {
				
				params.put(SInvokeKeys.postMark.value(), LogUtil.getPostMark());

				if (CenterModule.isGoodsService(moduleName)) {//商品服务

					result = doPost(ParamUtil.os_goods_url+moduleName+"/"+methodName+".htm", params);
				}else if (CenterModule.isOrderService(moduleName)) {//订单服务

					result = doPost(ParamUtil.os_order_url+moduleName+"/"+methodName+".htm", params);
				}else if(CenterModule.isUserService(moduleName)){//会员服务

					result = doPost(ParamUtil.os_user_url+moduleName+"/"+methodName+".htm", params);
				}else if(CenterModule.isMsgService(moduleName)){//消息服务

					result = doPost(ParamUtil.os_msg_url+moduleName+"/"+methodName+".htm", params);
				}else if(CenterModule.isXghService(moduleName)){//象过河服务

					result = doPost(ParamUtil.os_xgh_url+moduleName+"/"+methodName+".htm", params);
				}else if(CenterModule.isPayService(moduleName)){//支付服务
					
					result = doPost(ParamUtil.os_pay_url+moduleName+"/"+methodName+".htm", params);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}finally{
				LogUtil.addLog(params, request, startDate, result);
			}
		}

		return result;
	}


}