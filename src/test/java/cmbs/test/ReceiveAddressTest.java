package cmbs.test;

import java.util.HashMap;
import java.util.Map;

import com.h2y.cmbs2.basic.WbsKeys.SInvokeKeys;
import com.h2y.util.HttpTookit;
import com.h2y.util.JSONUtil;

public class ReceiveAddressTest {

	
	public static void main(String[] args) {
		
		add();
		
//		update();
		
//		setDefault();
		
//		delete();
//		getList();
		
//		System.out.println(new BigDecimal("34.76727300000000000").doubleValue());
		
	}
	
	
	/**
	 * receiverZoneCode:收获地址区域编码, 
	 * memberId:会员id, 
	 * receiverZoneDetail:收货地址详细, 
	 * receiverZoneName:收货地址名称, 
	 * longitude:收货地址经度, 
	 * latitude:收货地址纬度, 
	 * receiverName:收货地址名称, 
	 * receiverMobile:收货地址手机号码, 
	 * receiverTelephone:收货地址固话, 
	 * receiverMail:收货地址邮件, 
	 * isDefault:是否为默认地址（0：否、1：是）
	 */
	public static void add(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/receiveaddress/add.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("memberId", 28);
		paraInfo.put("receiverZoneCode", "410102000003");
		paraInfo.put("receiverZoneDetail", "陈寨北街");
		paraInfo.put("receiverZoneName", "河南省郑州市文化路北环交叉口");
		paraInfo.put("longitude", "114.61968");
		paraInfo.put("latitude", "35.767273");
		paraInfo.put("receiverName", "武兵");
		paraInfo.put("receiverMobile", "15838226036");
		paraInfo.put("receiverTelephone", "0371-55513032");
		paraInfo.put("receiverMail", "15838226036@139.com");
		paraInfo.put("isDefault", 0);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
	
	/**
	 * id:收货地址主键id, 
	 * receiverZoneCode:收获地址区域编码, 
	 * memberId:会员id, 
	 * receiverZoneDetail:收货地址详细, 
	 * receiverZoneName:收货地址名称, 
	 * longitude:收货地址经度, 
	 * latitude:收货地址纬度, 
	 * receiverName:收货地址名称, 
	 * receiverMobile:收货地址手机号码, 
	 * receiverTelephone:收货地址固话, 
	 * receiverMail:收货地址邮件, 
	 * isDefault:是否为默认地址（0：否、1：是）
	 */
	public static void update(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/receiveaddress/update.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("id", 2);
		paraInfo.put("receiverZoneCode", "410102001003");
		paraInfo.put("receiverZoneDetail", "陈寨北街1");
		paraInfo.put("receiverZoneName", "河南省郑州市文化路北环交叉口1");
		paraInfo.put("longitude", "114.619681");
		paraInfo.put("latitude", "35.7672731");
		paraInfo.put("receiverName", "侯飞龙");
		paraInfo.put("receiverMobile", "15838226037");
		paraInfo.put("receiverTelephone", "0371-55513031");
		paraInfo.put("receiverMail", "15838226037@139.com");
		paraInfo.put("isDefault", 1);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
	
	
	/**
	 * {memberId:会员id 
	 * page:页码, 
	 * pagesize:也显示最大记录数}
	 */
	public static void getList(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/receiveaddress/getList.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("memberId", 28);
		paraInfo.put("page", 2);
		paraInfo.put("pagesize", 1);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
	
	/**
	 * id:收货地址主键id, 
	 */
	public static void setDefault(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/receiveaddress/setDefault.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("id", 1);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
	
	/**
	 * id:收货地址主键id
	 */
	public static void delete(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/receiveaddress/delete.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("id", 1);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
}
