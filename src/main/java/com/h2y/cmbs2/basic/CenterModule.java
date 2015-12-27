package com.h2y.cmbs2.basic;

import java.util.ArrayList;
import java.util.List;

import com.h2y.util.PropertiesUtil;

/**
 * 项目名称：h2ycmbs2 Maven Webapp  
 * 类名称：CenterModule  
 * 类描述：中心模块类，检测是哪个服务的模块，进而控制转发  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月24日 上午8:19:42  
 * 修改人：侯飞龙
 * 修改时间：2015年4月24日 上午8:19:42  
 * 修改备注：  
 * @version
 */
public class CenterModule {

	private static List<String> gdsModules = new ArrayList<String>();

	private static List<String> userModules = new ArrayList<String>();

	private static List<String> orderModules = new ArrayList<String>();

	private static List<String> msgModules = new ArrayList<String>();

	private static List<String> logModules = new ArrayList<String>();

	private static List<String> xghModules = new ArrayList<String>();

	private static List<String> payModules = new ArrayList<String>();
	
	static{

		PropertiesUtil mPropertiesUtil = PropertiesUtil.getInstance("/module.properties");

		if (mPropertiesUtil.getValueByKey("server.gdsModules") != null) {
			addModuleToList(gdsModules, mPropertiesUtil.getValueByKey("server.gdsModules"));
		}

		if (mPropertiesUtil.getValueByKey("server.userModules") != null) {	
			addModuleToList(userModules, mPropertiesUtil.getValueByKey("server.userModules"));
		}

		if (mPropertiesUtil.getValueByKey("server.orderModules") != null) {
			addModuleToList(orderModules, mPropertiesUtil.getValueByKey("server.orderModules"));
		}

		if (mPropertiesUtil.getValueByKey("server.msgModules") != null) {
			addModuleToList(msgModules, mPropertiesUtil.getValueByKey("server.msgModules"));
		}

		if (mPropertiesUtil.getValueByKey("server.logModules") != null) {
			addModuleToList(logModules, mPropertiesUtil.getValueByKey("server.logModules"));
		}

		if (mPropertiesUtil.getValueByKey("server.xghModules") != null) {
			addModuleToList(xghModules, mPropertiesUtil.getValueByKey("server.xghModules"));
		}
		
		if(mPropertiesUtil.getValueByKey("server.payModules") != null){
			addModuleToList(payModules, mPropertiesUtil.getValueByKey("server.payModules"));
		}
	}


	/**
	 * 添加模块到集合
	 * @param modules 存储模块的集合
	 * @param modulesStr 模块字符串（用:分割）
	 */
	private static void addModuleToList(List<String> modules,String modulesStr){

		String gdsBeans_array [] = modulesStr.split(":");
		if (null!=gdsBeans_array) {
			for (String bean : gdsBeans_array) {
				modules.add(bean);
			}
		}
	}


	/**
	 * 判断是否为商品服务
	 * @param moduleName 模块名称（url中第一个通配符{className} eg:cmbs/advert）
	 * @return
	 */
	public static boolean isGoodsService(String moduleName){

		return gdsModules.contains(moduleName);
	}


	/**
	 * 判断是否为会员服务
	 * @param moduleName 模块名称（url中第一个通配符{className} eg:cmbs/advert）
	 * @return
	 */
	public static boolean isUserService(String moduleName){

		return userModules.contains(moduleName);
	}


	/**
	 * 判断是否为订单服务
	 * @param moduleName 模块名称（url中第一个通配符{className} eg:cmbs/advert）
	 * @return
	 */
	public static boolean isOrderService(String moduleName){

		return orderModules.contains(moduleName);
	}

	/**
	 * 判断是否为消息服务
	 * @param moduleName
	 * @return
	 */
	public static boolean isMsgService(String moduleName){

		return msgModules.contains(moduleName);
	}

	/**
	 * 判断是否为象过河服务
	 * @param moduleName
	 * @return
	 */
	public static boolean isXghService(String moduleName){

		return xghModules.contains(moduleName);
	}
	
	/**
	 * 判断是否为日志服务
	 * @param moduleName
	 * @return
	 */
	public static boolean isLogService(String moduleName){

		return logModules.contains(moduleName);
	}
	
	/**
	 * 判断是否为支付服务
	 * @param moduleName
	 * @return
	 */
	public static boolean isPayService(String moduleName){
		
		return payModules.contains(moduleName);
	}
}