package com.h2y.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 解析xml字符串

 * @author：段晓刚

 * @update：2014年7月31日 下午4:08:47

 * @Email：Java198921@163.com
 */
public class Dom4jUtil {

	/**
	 * 解析支付宝xml返回结果
	 * @param xml
	 * @return
	 * @update：2014年7月31日 下午4:23:02
	 */
	public static Map<String,Object> readQueryAlipayXmlOut(String xml) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Document doc = null;
		try {
			// 将字符串转为XML
			doc = DocumentHelper.parseText(xml); 
			// 获取根节点
			Element rootElt = doc.getRootElement(); 
			
			//获取头部
			Element isSuccessElement = rootElt.element("is_success");
			String is_success = isSuccessElement.getData()+"";
			resultMap.put("is_success", is_success);

			if(is_success.equals("T")){
				//成功
				Map<String,Object> requestMap = new HashMap<String, Object>();
				//获取request节点数据
				Element reqElement = rootElt.element("request"); 
				// 遍历request节点
				// 获取子节点request下的子节点param
				@SuppressWarnings("unchecked")
				Iterator<Element> paramIt = reqElement.elementIterator("param"); 
				// 遍历Header节点下的Response节点
				while (paramIt.hasNext()) {
					Element itemEle = paramIt.next();
					requestMap.put(itemEle.attributeValue("name"), itemEle.getData());
				}
				//添加到结果Map中
				resultMap.put("request", requestMap);

				//获取response节点数据
				Element resElement = rootElt.element("response"); 

				Map<String,Object> responseMap = new HashMap<String, Object>();
				//获取根节点下的子节点trade
				Element tradeElement = resElement.element("trade"); 
				@SuppressWarnings("unchecked")
				Iterator<Element> tradeIt = tradeElement.elementIterator();
				// 遍历trade节点
				while (tradeIt.hasNext()) {
					Element itemEle = tradeIt.next();
					// 拿到body节点下的子节点result值
					responseMap.put(itemEle.getName(), itemEle.getData());
				}
				//添加到结果Map中
				resultMap.put("response", responseMap);

				//获取sign信息
				Element signElement = rootElt.element("sign");
				String sign = signElement.getData()+"";
				resultMap.put("sign", sign);

				Element sign_typeElement = rootElt.element("sign_type");
				String sign_type = sign_typeElement.getData()+"";
				resultMap.put("sign", sign_type);

			}else {
				//返回错误
				//获取错误数据
				Element errorElement = rootElt.element("error");
				String error = errorElement.getData()+"";
				resultMap.put("error", error);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}