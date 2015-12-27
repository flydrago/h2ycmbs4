package com.h2y.util;

/**
 * 参数配置

 * @author：段晓刚

 * @update：2015年4月7日 上午9:40:03

 * @Email：
 */
public class ParamUtil {

	public static String os_user_url = "http://10.10.10.10:8090/member/server/";

	//商品中心
	public static String os_goods_url = "";

	//订单中心
	public static String os_order_url = "";

	//推送消息、短信发送
	public static String os_msg_url = null;
	
	//日志
	public static String os_log_url = null;
	
	//象过河
	public static String os_xgh_url = null;

	//支付
	public static String os_pay_url = null;
	
	//配合skey使用进行权限验证
	public static String slock = null;

	//配合slock使用
	public static String skey = null;

	//app文件上传路径
	public static String app_upload_path = null;

	static {

		PropertiesUtil mPropertiesUtil = PropertiesUtil.getInstance("/config.properties");

		if (mPropertiesUtil.getValueByKey("os.user.url") != null) {
			os_user_url = mPropertiesUtil.getValueByKey("os.user.url");
		}
		if (mPropertiesUtil.getValueByKey("os.goods.url") != null) {
			os_goods_url = mPropertiesUtil.getValueByKey("os.goods.url");
		}
		if (mPropertiesUtil.getValueByKey("os.order.url") != null) {
			os_order_url = mPropertiesUtil.getValueByKey("os.order.url");
		}
		if (mPropertiesUtil.getValueByKey("os.msg.url") != null) {
			os_msg_url = mPropertiesUtil.getValueByKey("os.msg.url");
		}
		if (mPropertiesUtil.getValueByKey("os.log.url") != null) {
			os_log_url = mPropertiesUtil.getValueByKey("os.log.url");
		}
		if (mPropertiesUtil.getValueByKey("os.xgh.url") != null) {
			os_xgh_url = mPropertiesUtil.getValueByKey("os.xgh.url");
		}
		if (mPropertiesUtil.getValueByKey("os.pay.url") != null){
			os_pay_url = mPropertiesUtil.getValueByKey("os.pay.url");
		}
		if (mPropertiesUtil.getValueByKey("slock") != null){
			slock = mPropertiesUtil.getValueByKey("slock");
		}
		if (mPropertiesUtil.getValueByKey("skey") != null){
			skey = mPropertiesUtil.getValueByKey("skey");
		}
		if (mPropertiesUtil.getValueByKey("app.upload.path") != null){
			app_upload_path = mPropertiesUtil.getValueByKey("app.upload.path");
		}
	}

}
