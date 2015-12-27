package com.h2y.cmbs2.entity;

import java.io.Serializable;

/**
 * App升级原型

 * @author：段晓刚

 * @update：2015年4月3日 下午5:53:05

 * @Email：
 */
public class SysApp implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keySysApp";
	private long id;
	private String code;
	private long versionCode;
	private String versionName;
	private String versionUpdateMsg;
	private String apkName;
	private String apkSaveName;
	private String diskPath;
	private String diskSaveName;
	private String downUrl;
	private String md5Code;
	private String apkIcon;
	private String apkPackagename;
	private long apkLength;
	private String apkSize;
	private String apkCom;
	private String updateDate;
	private long ifWork;
	private long ifDelete;
	private String encryptCode;
	private String signature;
	private String memo;

	public SysApp() {
		super();
	}

	public SysApp(long id) {
		super();
		this.id = id;
	}

	public SysApp(long id, String code, long versionCode, String versionName,
			String versionUpdateMsg, String apkName, String apkSaveName,
			String diskPath, String diskSaveName, String downUrl,
			String md5Code, String apkIcon, String apkPackagename,
			long apkLength, String apkSize, String apkCom, String updateDate,
			long ifWork, long ifDelete, String encryptCode, String signature,
			String memo) {
		super();
		this.id = id;
		this.code = code;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.versionUpdateMsg = versionUpdateMsg;
		this.apkName = apkName;
		this.apkSaveName = apkSaveName;
		this.diskPath = diskPath;
		this.diskSaveName = diskSaveName;
		this.downUrl = downUrl;
		this.md5Code = md5Code;
		this.apkIcon = apkIcon;
		this.apkPackagename = apkPackagename;
		this.apkLength = apkLength;
		this.apkSize = apkSize;
		this.apkCom = apkCom;
		this.updateDate = updateDate;
		this.ifWork = ifWork;
		this.ifDelete = ifDelete;
		this.encryptCode = encryptCode;
		this.signature = signature;
		this.memo = memo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(long versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionUpdateMsg() {
		return versionUpdateMsg;
	}

	public void setVersionUpdateMsg(String versionUpdateMsg) {
		this.versionUpdateMsg = versionUpdateMsg;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public String getApkSaveName() {
		return apkSaveName;
	}

	public void setApkSaveName(String apkSaveName) {
		this.apkSaveName = apkSaveName;
	}

	public String getDiskPath() {
		return diskPath;
	}

	public void setDiskPath(String diskPath) {
		this.diskPath = diskPath;
	}

	public String getDiskSaveName() {
		return diskSaveName;
	}

	public void setDiskSaveName(String diskSaveName) {
		this.diskSaveName = diskSaveName;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getMd5Code() {
		return md5Code;
	}

	public void setMd5Code(String md5Code) {
		this.md5Code = md5Code;
	}

	public String getApkIcon() {
		return apkIcon;
	}

	public void setApkIcon(String apkIcon) {
		this.apkIcon = apkIcon;
	}

	public String getApkPackagename() {
		return apkPackagename;
	}

	public void setApkPackagename(String apkPackagename) {
		this.apkPackagename = apkPackagename;
	}

	public long getApkLength() {
		return apkLength;
	}

	public void setApkLength(long apkLength) {
		this.apkLength = apkLength;
	}

	public String getApkSize() {
		return apkSize;
	}

	public void setApkSize(String apkSize) {
		this.apkSize = apkSize;
	}

	public String getApkCom() {
		return apkCom;
	}

	public void setApkCom(String apkCom) {
		this.apkCom = apkCom;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public long getIfWork() {
		return ifWork;
	}

	public void setIfWork(long ifWork) {
		this.ifWork = ifWork;
	}

	public long getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(long ifDelete) {
		this.ifDelete = ifDelete;
	}

	public String getEncryptCode() {
		return encryptCode;
	}

	public void setEncryptCode(String encryptCode) {
		this.encryptCode = encryptCode;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String toString() {
		return "id:" + id + "\t" + "code:" + code + "\t" + "versionCode:"
				+ versionCode + "\t" + "versionName:" + versionName + "\t"
				+ "versionUpdateMsg:" + versionUpdateMsg + "\t" + "apkName:"
				+ apkName + "\t" + "apkSaveName:" + apkSaveName + "\t"
				+ "diskPath:" + diskPath + "\t" + "diskSaveName:"
				+ diskSaveName + "\t" + "downUrl:" + downUrl + "\t"
				+ "md5Code:" + md5Code + "\t" + "apkIcon:" + apkIcon + "\t"
				+ "apkPackagename:" + apkPackagename + "\t" + "apkLength:"
				+ apkLength + "\t" + "apkSize:" + apkSize + "\t" + "apkCom:"
				+ apkCom + "\t" + "updateDate:" + updateDate + "\t" + "ifWork:"
				+ ifWork + "\t" + "ifDelete:" + ifDelete + "\t"
				+ "encryptCode:" + encryptCode + "\t" + "signature:"
				+ signature + "\t" + "memo:" + memo;
	}
}