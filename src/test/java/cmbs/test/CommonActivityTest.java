package cmbs.test;

import java.util.HashMap;
import java.util.Map;

import com.h2y.cmbs2.basic.WbsKeys.SInvokeKeys;
import com.h2y.memcached.MemcachedUtil;
import com.h2y.util.HttpTookit;
import com.h2y.util.JSONUtil;

public class CommonActivityTest {

	
	public static void main(String[] args) {
		
//		getList();
//		getGoodsList();
		
		Integer a = null;
		int b = 0;
		int c;
		
		System.out.println("a:"+a+" b:"+b+"c:");
	}
	
	
	public static void getList(){
		
		String url = "http://10.10.10.182/h2ycmbs2/cmbs/commonacitvity/getList.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("zoneCode", "370000000");
		paraInfo.put("activityType", "index");
		paraInfo.put("page", "1");
		paraInfo.put("pagesize", "10");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
	/**
	 * zoneCode:区域编码,
	 * dataId:活动或主题Id,
	 * dataType:数据类型（0：活动、1：主题）
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * appVersion:app版本号,
	 * appSystem:（android:安卓、ios:苹果、wechat:微信）
	 */
	public static void getGoodsList(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/commonacitvity/getGoodsList.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("zoneCode", "370000000");
		paraInfo.put("dataId", "9");
		paraInfo.put("dataType", "1");
		paraInfo.put("page", "1");
		paraInfo.put("pagesize", "10");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
}
