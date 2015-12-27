package com.h2y.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 进行HTML内容中的标签替换

 * 作者：段晓刚

 * 时间：2013-6-27 上午11:07:43

 * 电子邮件：

 * QQ：2410960521

 * Gmail :
 */
public class HtmlUtil {


	/**
	 * 替换标签中属性值
	 * @param html		原Html内容
	 * @param beforeTag	需要替换的标签
	 * @param tagAttrib
	 * @param newValue
	 * @return
	 * 例如替换img标签 getImags(html,"img","src","123")
	 */
	public static String replaceTags(String html,String beforeTag,String tagAttrib,String newValue){
		String regxpForTag = "<\\s*"+beforeTag+"\\s+([^>]*)\\s*>";
		String regxpForTagAttrib = tagAttrib+"=\"([^\"]+)\"";
		Pattern patternForTag = Pattern.compile(regxpForTag);
		Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
		Matcher matcherForTag = patternForTag.matcher(html);
		StringBuffer sb = new StringBuffer();
		while (matcherForTag.find()) {
			StringBuffer sbreplace = new StringBuffer("<"+beforeTag+" ");
			Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
			String newImg = "";
			if (matcherForAttrib.find()) {
				String mg1 = matcherForAttrib.group(1);
				if(mg1.contains("http://")){
					matcherForAttrib.appendReplacement(sbreplace, tagAttrib+"=\""+ ""+newValue+"?URLPATH="+mg1 + "\" ");
					newImg = sbreplace.toString();
				}else{
					matcherForAttrib.appendReplacement(sbreplace, tagAttrib+"=\""+ ""+newValue+"?URLPATH="+mg1 + "\" ");
					newImg = sbreplace.toString();
					newImg = newImg.replace("//", "/");
					newImg = newImg.replace("\\\\\\", "/");
				}
			}

			matcherForTag.appendReplacement(sb, newImg+" />");
		}
		matcherForTag.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 替换标签中属性值
	 * @param html		原Html内容
	 * @param beforeTag	需要替换的标签
	 * @param tagAttrib
	 * @param newValue
	 * @return
	 * 例如替换img标签 getImags(html,"img","src","123")
	 */
	public static String replaceImaageValues(String html,String newValue){
		String beforeTag = "img";
		String tagAttrib = "src";

		return replaceTags(html, beforeTag, tagAttrib, newValue);
	}

	/**
	 * 获取字符串中说有的标签
	 * @param html		原Html内容
	 * @param beforeTag	需要替换的标签
	 * @param tagAttrib
	 * @param newValue
	 * @return
	 * 例如替换img标签 getImags(html,"img","src","123")
	 */
	public static List<String> getTagValues(String html,String beforeTag,String tagAttrib,String newValue){
		List<String> mImagList = new ArrayList<String>();

		String regxpForTag = "<\\s*"+beforeTag+"\\s+([^>]*)\\s*>";
		String regxpForTagAttrib = tagAttrib+"=\"([^\"]+)\"";
		Pattern patternForTag = Pattern.compile(regxpForTag);
		Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
		Matcher matcherForTag = patternForTag.matcher(html);

		while (matcherForTag.find()) {
			Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
			if (matcherForAttrib.find()) {
				String mg1 = matcherForAttrib.group(1);
				String newImg = "";
				if(newValue==null || newValue.equals(""))
					newImg = mg1;
				else{
					newImg = newValue+"?URLPATH="+mg1;
				}
				mImagList.add(newImg);
			}
		}

		return mImagList;
	}

	/**
	 * 替换标签中属性值
	 * @param html		原Html内容
	 * @param beforeTag	需要替换的标签
	 * @param tagAttrib
	 * @param newValue
	 * @return
	 * 例如替换img标签 getImags(html,"img","src","123")
	 */
	public static List<String> getImagTagValues(String html){
		List<String> mImagList = new ArrayList<String>();

		String beforeTag = "img";
		String tagAttrib = "src";

		mImagList = getTagValues(html, beforeTag, tagAttrib, "");

		return mImagList;
	}

	/**
	 * 获取html页面中第一个出现的beforeTag标签tagAttrib值，获取的值需要转交给对应的servlet处理
	 * @param html			需要查询的html页面
	 * @param newValue		该项目处理Image的servlet地址
	 * 如第一img标签 getImags(html,"img","src","123")
	 * @return
	 */
	public static String getFirstTag(String html,String beforeTag,String tagAttrib,String newValue){
		String newTag = "";

		String regxpForTag = "<\\s*"+beforeTag+"\\s+([^>]*)\\s*>";
		String regxpForTagAttrib = tagAttrib+"=\"([^\"]+)\"";
		Pattern patternForTag = Pattern.compile(regxpForTag);
		Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
		Matcher matcherForTag = patternForTag.matcher(html);

		boolean result = matcherForTag.find();
		if(result){
			Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));

			if (matcherForAttrib.find()) {
				String mg1 = matcherForAttrib.group(1);
				if(mg1.contains("http://")){
					if(newValue==null || newValue.equals("")){
						newTag = mg1;
					}else {
						newTag = newValue+"?URLPATH="+mg1;
					}
				}else{
					if(newValue==null || newValue.equals("")){
						newTag = mg1;
					}else {
						newTag = newValue+"?URLPATH="+mg1;
					}
				}
				newTag = newTag.replace("//", "/");
				newTag = newTag.replace("\\\\\\", "/");
				if(newTag.contains("http:/") && !newTag.contains("http://"))
					newTag = newTag.replace("http:/", "http://");
			}
		}
		return newTag;
	}

	/**
	 * 获取html页面中第一个出现的img标签src值，获取的值需要转交给对应的servlet处理
	 * @param html			需要查询的html页面
	 * @return
	 */
	public static String getFirstImage(String html,String newValue){
		String beforeTag = "img";
		String tagAttrib = "src";
		String newImg = getFirstTag(html, beforeTag, tagAttrib, newValue);

		return newImg;
	}


	//	public static void main(String args[]){
	//
	//		//String html = "<img src=\"/20130910/20130910115751_896.jpg\" /><img src=\"/20130910/123.jpg\" />";
	//		String html = "<img src=\"http://f.hiphotos.baidu.com/image/w%3D2048/sign=67308d671f178a82ce3c78a0c23b728d/63d9f2d3572c11df4cfac888622762d0f703c276.jpg\" width=\"8\" height=\"8\" />" +
	//				"sadfsad" +
	//				"<img src=\"http://e.hiphotos.baidu.com/image/w%3D2048/sign=5e915a60a9d3fd1f3609a53a0476241f/ac6eddc451da81cbe5c14a835366d0160924315d.jpg\" width=\"8\" height=\"8\" />123" +
	//				"<img src=\"http://e.hiphotos.baidu.com/image/w%3D2048/sign=f0462a1535a85edffa8cf9237d6c0823/3ac79f3df8dcd100f6d6bb64738b4710b9122f28.jpg\" width=\"8\" height=\"8\" />" +
	//				"<img src=\"http://a.hiphotos.baidu.com/image/w%3D2048/sign=52f092b30dd79123e0e09374990c5882/cf1b9d16fdfaaf516bae43048d5494eef01f7a38.jpg\" width=\"8\" height=\"8\" />";
	//		List<String> mImageValueList = getImagTagValues(html);
	//		for(String mImageValue:mImageValueList){
	//			System.out.println(mImageValue);
	//		}
	//		System.out.println(replaceImaageValues(html, "http://192.168.4.120:8080/"));
	//		System.out.println(getFirstImage(html, null));
	//	}
}