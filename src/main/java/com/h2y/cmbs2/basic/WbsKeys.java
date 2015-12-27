package com.h2y.cmbs2.basic;

/**
 * 常用参数处理
 * 
 * @author：段晓刚
 * 
 * @update：2015年4月3日 上午11:39:23
 * 
 * @Email：
 */
public class WbsKeys {

	/**
	 * 服务端调用参数
	 * 
	 * @author：段晓刚
	 * 
	 * @update：2015年3月27日 上午9:12:27
	 * 
	 * @Email：
	 */
	public enum SInvokeKeys {

		// 配合skey使用进行安全验证
		slock("slock"),
		// 配合slock使用
		skey("skey"),
		// 请求发出后的唯一标示
		sid("sid"),
		// 操作系统
		os("os"),
		// 系统操作类型
		osv("osv"),
		// app版本号
		appv("appv"),
		//硬件信息1
		postHd1("postHd1"),
		//硬件信息2
		postHd2("postHd2"),
		//访问标识
		postMark("postMark"),
		// 参数头部
		paramData("paramData"),
		// 业务参数
		postData("postData"),
		// 返回结果标示
		resultFlag("resultFlag"),
		// 返回结果信息
		resultMsg("resultMsg"),
		// 返回的业务逻辑
		resultData("resultData");

		private String _value;

		SInvokeKeys(String value) {
			_value = value;
		}

		public String value() {
			return _value;
		}
	}

	/**
	 * bean常量
	 * 
	 * @author：段晓刚
	 * 
	 * @update：2015年4月3日 上午11:43:26
	 * 
	 * @Email：
	 */
	public enum UserKeys {

		// 用户id
		userId("userId"),
		// 用户名字
		userName("userName"),
		// 昵称
		nickName("nickName"),
		// 账号
		account("account"),
		// 密码
		password("password");

		private String _value;

		UserKeys(String value) {
			_value = value;
		}

		public String value() {
			return _value;
		}
	}

	/**
	 * 单位信息键值
	 * 
	 * @author：段晓刚
	 * 
	 * @update：2015年4月7日 上午9:35:52
	 * 
	 * @Email：
	 */
	public enum UnitKeys {

		// 单位id
		unitId("unitId"),
		// 单位code
		unitCode("unitCode"),
		// 单位名称
		unitName("unitName"),
		// 地区编码
		zoneCode("zoneCode"),
		// 地区名称
		zoneName("zoneName");

		private String _value;

		UnitKeys(String value) {
			_value = value;
		}

		public String value() {
			return _value;
		}
	}

	/**
	 * 方法处理
	 * 
	 * @author：段晓刚
	 * 
	 * @update：2015年3月31日 下午4:41:40
	 * 
	 * @Email：
	 */
	public enum MethodKeys {

		// 注册
		register("user/register"),
		// 登录
		login("login"),
		// 用户退出
		exit("update"),
		// 商品数据
		goods("goods");

		private String _value;

		MethodKeys(String value) {
			_value = value;
		}

		public String value() {
			return _value;
		}
	}

	/**
	 * 服务端调用参数
	 * 
	 * @author：段晓刚
	 * 
	 * @update：2015年3月27日 上午9:12:27
	 * 
	 * @Email：
	 */
	public enum JydKeys {

		// 商品id
		goodsId("goodsId"),
		// 商品编码
		goodsCode("goodsCode"),
		// 商品名称
		goodsName("goodsName"),
		// 商品单位
		goodsUnit("goodsUnit"),
		// 商品规格
		goodsSpec("goodsSpec"),
		// 会员价格
		memberPrice("memberPrice"),
		// 市场价格
		marketPrice("marketPrice"),
		// 零售价格
		sellPrice("sellPrice"),
		// 商品活动价格
		activityPrice("activityPrice"),
		// 零售价格
		goodsIntrodoce("goodsIntrodoce"),
		//
		goodsIntrodoce2("goodsIntrodoce2"),
		// 仓库编码
		storageId("storageId"),
		// 仓库名称
		storageName("storageName"),
		// 仓库地址
		storageAddress("storageAddress"),
		// 店铺id
		shopId("shopId"),
		// 门店名称
		shopName("shopName"),
		// 主订单编号
		orderNo("orderNo"),
		// 子订单编号
		orderNo2("orderNo2"),
		// 数量
		count("count"),
		// 类型
		type("type"),
		// 时间单位s
		time("shopname"),
		// 支付类型，0虚拟货币支付、1现金支付、2pos支付、3支付宝、4微信支付、5其他支付
		payType("payType"),
		// 支付名称和paytype配合使用，主要显示对应的名称
		payName("payName"),
		// 应付金额，两位有效小数
		amount("amount"),
		// 支付账号
		payNo("payNo"),
		// 账号
		cardNo("cardNo"),
		// 单价
		price("price"),
		// 总价
		allPrice("allPrice");

		private String _value;

		JydKeys(String value) {
			_value = value;
		}
		
		public String value() {
			return _value;
		}
	}
}