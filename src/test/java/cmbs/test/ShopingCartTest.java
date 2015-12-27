package cmbs.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.h2y.cmbs2.basic.WbsKeys.SInvokeKeys;
import com.h2y.util.HttpTookit;
import com.h2y.util.JSONUtil;

public class ShopingCartTest {

	
	public static void main(String[] args) {
		
		add();
		
//		reduce();
		
//		update();
		
//		getList();
		
//		syn();
		
//		delete();
	}
	
	
	/**
	 * memberId:会员id, goodsPriceId:商品定价id, goodsId:商品id（暂不使用）
	 */
	public static void add(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/shopingcart/add.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("memberId", 28);
		paraInfo.put("goodsPriceId", "4");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
	
	/**
	 * memberId:会员id, goodsPriceId:商品定价id, goodsId:商品id（暂不使用）
	 */
	public static void reduce(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/shopingcart/reduce.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("memberId", 28);
		paraInfo.put("goodsPriceId", "2");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
	
	/**
	 * memberId:会员id, goodsPriceId:商品定价id, goodsCount:商品数量, goodsId:商品id（暂不使用）
	 */
	public static void update(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/shopingcart/update.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("memberId", 28);
		paraInfo.put("goodsPriceId", "4");
		paraInfo.put("goodsCount", "10");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
	
	/**
	 * memberId:会员id, goodsPriceId:商品定价id, goodsId:商品id（暂不使用）
	 */
	public static void delete(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/shopingcart/delete.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("memberId", 28);
		paraInfo.put("goodsPriceId", "4");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
	
	/**
	 * memberId:会员id, page:页码, pagesize:也显示最大记录数
	 */
	public static void getList(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/shopingcart/getList.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("memberId", 28);
		paraInfo.put("page", "1");
		paraInfo.put("pagesize", "5");
		paraInfo.put("zoneCode", "370000000");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
	
	/**
	 * memberId:会员id, page:页码, pagesize:也显示最大记录数
	 */
	public static void syn(){
		
		String url = "http://10.10.10.182:80/h2ycmbs2/cmbs/shopingcart/syn.htm";
		
		Map<String,Object> paraInfo = new HashMap<String, Object>();
		paraInfo.put("memberId", 28);
		paraInfo.put("page", "1");
		paraInfo.put("pagesize", "2");
		paraInfo.put("zoneCode", "370000000");
		List<Map<String,Object>> goodsList = new ArrayList<Map<String,Object>>();
		paraInfo.put("goodsList", goodsList);
		Map<String,Object> goods1 = new HashMap<String, Object>();
		goods1.put("goodsPriceId", 4);
		goods1.put("goodsCount", 4);
		
		Map<String,Object> goods2 = new HashMap<String, Object>();
		goods2.put("goodsPriceId", 7);
		goods2.put("goodsCount", 5);
		
		Map<String,Object> goods3 = new HashMap<String, Object>();
		goods3.put("goodsPriceId", 8);
		goods3.put("goodsCount", 6);
		goodsList.add(goods1);
		goodsList.add(goods2);
		goodsList.add(goods3);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(paraInfo));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果："+result);
	}
	
}
