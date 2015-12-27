package com.h2y.cmbs2.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.h2y.cmbs2.basic.WbsKeys.SInvokeKeys;
import com.h2y.util.JSONUtil;
import com.h2y.util.PropertiesUtil;

/**
 * 基础Controller
 * 
 * 使用Controller提供服务处理，注意权限控制的重要性
 * 
 * @author：段晓刚
 * 
 * @update：2015年4月1日 下午10:04:25
 * 
 * @Email：
 */
@Controller
public class BaseController {

	protected DecimalFormat format = new DecimalFormat("0.00");
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;

	// private Map<String, Object> mUserEntity = new HashMap<String, Object>();

	// url通知
	private Map<String, String> urlMap = new HashMap<String, String>();

	protected Charset utf_8 = Charset.forName("UTF-8");

	// private Logger logger = Logger.getLogger(BaseController.class);

	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	protected Map<String, Object> getCurrentUser() {

		return null;
	}

	/**
	 * 获取URL配置信息
	 * 
	 * @return
	 * @throws java.io.IOException
	 * @update：2014年8月4日 下午8:42:09
	 */
	protected Map<String, String> getURLConfing() throws IOException {

		if (urlMap == null || urlMap.isEmpty())
			urlMap = PropertiesUtil.getInstance("/config.properties").getMap();
		return urlMap;
	}

	/**
	 * @ModelAttribute放置在方法上面：表示请求该类的每个Action前都会首先执行它， 你可以将一些准备数据的操作放置在该方法里面
	 */
	@ModelAttribute
	public void setReqAndResp(HttpServletResponse response,
			HttpServletRequest request) {
		this.response = response;
		this.request = request;
		this.session = request.getSession();
	}

	protected void outJson(Object obj) {
		out(JSONUtil.getJson(obj));
	}

	/**
	 * 输出数据
	 * 
	 * @param value
	 */
	protected void out(String value) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.println(value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 获取参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @update：2015年4月7日 下午1:45:43
	 */
	protected Map<String, Object> getReqMap() {

		Map<String, Object> reqMap = new HashMap<String, Object>();

		// 获取参数
		reqMap.put(SInvokeKeys.slock.value(),request.getParameter(SInvokeKeys.slock.value()));
		reqMap.put(SInvokeKeys.skey.value(),request.getParameter(SInvokeKeys.skey.value()));
		reqMap.put(SInvokeKeys.sid.value(),request.getParameter(SInvokeKeys.sid.value()));
		reqMap.put(SInvokeKeys.os.value(),request.getParameter(SInvokeKeys.os.value()));
		reqMap.put(SInvokeKeys.osv.value(),request.getParameter(SInvokeKeys.osv.value()));
		reqMap.put(SInvokeKeys.appv.value(),request.getParameter(SInvokeKeys.appv.value()));
		reqMap.put(SInvokeKeys.postHd1.value(),request.getParameter(SInvokeKeys.postHd1.value()));
		reqMap.put(SInvokeKeys.postHd2.value(),request.getParameter(SInvokeKeys.postHd2.value()));
		reqMap.put(SInvokeKeys.postData.value(),request.getParameter(SInvokeKeys.postData.value()));
		reqMap.put(SInvokeKeys.postMark.value(),request.getParameter(SInvokeKeys.postMark.value()));

		return reqMap;
	}

	/**
	 * 获取业务参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @update：2015年4月7日 下午1:51:24
	 */
	protected Map<String, Object> getPostMap() {
		Map<String, Object> postMap = new HashMap<String, Object>();

		Map<String, Object> reqMap = getReqMap();

		if (reqMap != null) {
			Object _post = reqMap.get(SInvokeKeys.postData.value());
			postMap = JSONUtil.getMap(_post+"");
		}

		return postMap;
	}

	/**
	 * 获取返回主体参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	protected Map<String, Object> getResultMap() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> reqMap = getReqMap();

		if (reqMap != null) {
			resultMap.put(SInvokeKeys.slock.value(),reqMap.get(SInvokeKeys.slock.value()));
			resultMap.put(SInvokeKeys.skey.value(),reqMap.get(SInvokeKeys.skey.value()));
			resultMap.put(SInvokeKeys.sid.value(),reqMap.get(SInvokeKeys.sid.value()));
			resultMap.put(SInvokeKeys.os.value(),reqMap.get(SInvokeKeys.os.value()));
			resultMap.put(SInvokeKeys.osv.value(),reqMap.get(SInvokeKeys.osv.value()));
			resultMap.put(SInvokeKeys.appv.value(),reqMap.get(SInvokeKeys.appv.value()));
		}

		return resultMap;
	}
}