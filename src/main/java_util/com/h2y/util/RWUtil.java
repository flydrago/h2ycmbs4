package com.h2y.util;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读写工具类

 * 作者：段晓刚

 * 时间：2014年5月16日 上午11:43:53

 * 电子邮件：851751942@qq.com

 * QQ：851751942

 * Gmail :
 */
public class RWUtil {

	/**
	 * 字符串写入文件
	 * @param filePath
	 * @param str
	 * @throws IOException
	 */
	public static void writeString(String filePath,String str,boolean append,boolean autoFlush) throws IOException{

		File file = new File(filePath);

		//构建路径
		if(file.getParentFile()!=null && !file.getParentFile().exists())
			file.getParentFile().mkdirs();

		if(!file.exists())
			file.createNewFile();

		PrintWriter pw = new PrintWriter(new FileWriter(filePath,append),autoFlush);

		pw.println(str);

		//关闭流
		pw.close();
	}

	/**
	 * 从文件中读出字符串
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static List<String> readString(String filePath) throws IOException{

		if(!new File(filePath).exists())
			return null;
		List<String> list = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(filePath));

		String str = null;
		while((str=br.readLine())!=null){
			list.add(str);
		}

		//关闭流
		br.close();

		return list;
	}

	public static String readFormatStr(String filePath) throws IOException{

		BufferedReader br = new BufferedReader(new FileReader(filePath));

		String str = null;

		String vString = "";
		while((str=br.readLine())!=null){

			if(vString.equals(""))
				vString = str+"</br>";
			else {
				vString = vString+str+"</br>";
			}
		}

		//关闭流
		br.close();

		return vString;
	}

	/**
	 * 对象写入文件
	 * @param filePath
	 * @param obj
	 * @throws IOException
	 */
	public static void writeObject(String filePath,Object obj) throws IOException{

		File file = new File(filePath);

		//构建路径
		if(file.getParentFile()!=null && !file.getParentFile().exists())
			file.getParentFile().mkdirs();

		if(!file.exists())
			file.createNewFile();

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));

		objectOutputStream.writeObject(obj);

		//关闭流
		objectOutputStream.close();
	}

	/**
	 * 对象对出文件
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public static Object readObject(String filePath) throws IOException, ClassNotFoundException{

		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
			
			Object object  = ois.readObject();

			//关闭流
			ois.close();

			return object;
		}catch(EOFException e){
			return null;
		}
	}

	//	public static void main(String args[]){
	//		
	//		try {
	//			writeString("d://test.txt", "duanxg"+System.currentTimeMillis(),false,true);
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}
}
