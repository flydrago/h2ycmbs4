package com.h2y.cmbs2.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * MemberUser Model create
 * @author hwttnet
 * version:1.2
 * time:2014-11-12
 * email:info@hwttnet.com
 */

public class MemberUser implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyMemberUser";
    private long id;
    private String realName;
    private String nickName;
    private String account;
    private String password;
    private String sex;
    private String birDate;
    private String idCard;
    private String mailAdd;
    private String telePhone;
    private String zone;
    private String zoneDetail;
    private Date createDate;
    private String status;
    private String refOneName;
    private String refTwoName;
    private String qrPath;
    private String qrRelPath;
    private String uuid;
    private String headPath;

	public String getHeadPath() {
		return headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}

	public MemberUser(){
		super();
	}

	public MemberUser(long id){
		super();
		this.id = id;
	}

	public MemberUser(long id,String realName,String nickName,String account,String password,String sex,String birDate,String idCard,String mailAdd,String telePhone,String zone,String zoneDetail,Date createDate,String status,String refOneName,String refTwoName){
		super();
		this.id = id;
		this.realName = realName;
		this.nickName = nickName;
		this.account = account;
		this.password = password;
		this.sex = sex;
		this.birDate = birDate;
		this.idCard = idCard;
		this.mailAdd = mailAdd;
		this.telePhone = telePhone;
		this.zone = zone;
		this.zoneDetail = zoneDetail;
		this.createDate = createDate;
		this.status = status;
		this.refOneName = refOneName;
		this.refTwoName = refTwoName;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getRealName(){
      return realName;
    }
    
    public void setRealName(String realName){
      this.realName = realName;
    }


    public String getNickName(){
      return nickName;
    }
    
    public void setNickName(String nickName){
      this.nickName = nickName;
    }


    public String getAccount(){
      return account;
    }
    
    public void setAccount(String account){
      this.account = account;
    }


    public String getPassword(){
      return password;
    }
    
    public void setPassword(String password){
      this.password = password;
    }


    public String getSex(){
      return sex;
    }
    
    public void setSex(String sex){
      this.sex = sex;
    }


    public String getBirDate(){
      return birDate;
    }
    
    public void setBirDate(String birDate){
      this.birDate = birDate;
    }


    public String getIdCard(){
      return idCard;
    }
    
    public void setIdCard(String idCard){
      this.idCard = idCard;
    }


    public String getMailAdd(){
      return mailAdd;
    }
    
    public void setMailAdd(String mailAdd){
      this.mailAdd = mailAdd;
    }


    public String getTelePhone(){
      return telePhone;
    }
    
    public void setTelePhone(String telePhone){
      this.telePhone = telePhone;
    }


    public String getZone(){
      return zone;
    }
    
    public void setZone(String zone){
      this.zone = zone;
    }


    public String getZoneDetail(){
      return zoneDetail;
    }
    
    public void setZoneDetail(String zoneDetail){
      this.zoneDetail = zoneDetail;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public String getStatus(){
      return status;
    }
    
    public void setStatus(String status){
      this.status = status;
    }


    public String getRefOneName(){
      return refOneName;
    }
    
    public void setRefOneName(String refOneName){
      this.refOneName = refOneName;
    }


    public String getRefTwoName(){
      return refTwoName;
    }
    
    public void setRefTwoName(String refTwoName){
      this.refTwoName = refTwoName;
    }
    
    public String getQrPath() {
		return qrPath;
	}

	public void setQrPath(String qrPath) {
		this.qrPath = qrPath;
	}

	public String getQrRelPath() {
		return qrRelPath;
	}

	public void setQrRelPath(String qrRelPath) {
		this.qrRelPath = qrRelPath;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

    public String toString(){
	return "id:"+id+"\t"+"realName:"+realName+"\t"+"nickName:"+nickName+"\t"+"account:"+account+"\t"+"password:"+password+"\t"+"sex:"+sex+"\t"+"birDate:"+birDate+"\t"+"idCard:"+idCard+"\t"+"mailAdd:"+mailAdd+"\t"+"telePhone:"+telePhone+"\t"+"zone:"+zone+"\t"+"zoneDetail:"+zoneDetail+"\t"+"createDate:"+createDate+"\t"+"status:"+status+"\t"+"refOneName:"+refOneName+"\t"+"refTwoName:"+refTwoName;
    }
}