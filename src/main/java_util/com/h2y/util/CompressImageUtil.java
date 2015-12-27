package com.h2y.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 等比例压缩图片

 * 作者：段晓刚

 * 时间：2013-6-26 下午9:20:19

 * 电子邮件：

 * QQ：2410960521

 * Gmail :
 */
@SuppressWarnings("restriction")
public class CompressImageUtil {     

	//文件对象 
	private File file = null; 
	//输入图路径
	//private String inputDir; 
	// 输入图文件名
	//private String inputFileName; 
	// 输出图路径
	private String outputDir; 

	// 输出图文件名
	private String outputFileName; 
	// 默认输出图片宽
	private int outputWidth = 100; 
	// 默认输出图片高
	private int outputHeight = 100; 
	// 是否等比缩放标记(默认为等比缩放)
	private boolean proportion = true;

	private static CompressImageUtil compressPicUtil;

	private CompressImageUtil() { 
		// 初始化变量                 
		//inputDir = "";    
		//inputFileName = ""; 
		outputDir = "";                  
		outputFileName = "";                  
		outputWidth = 100;                  
		outputHeight = 100;          
	}

	public static CompressImageUtil getInstance(){
		compressPicUtil = new CompressImageUtil();
		return compressPicUtil;
	}

	/*           
	 * 获得图片大小          
	 * 传入参数 String path ：图片路径         
	 */          
	public long getPicSize(String path) {
		file = new File(path);
		return file.length();          
	}

	//图片处理          
	public String compressPic(InputStream is) {
		try {                          

			Image img = ImageIO.read(is);                          
			// 判断图片格式是否正确                          
			if (img.getWidth(null) == -1) {
				System.out.println(" can't read,retry!" + "<BR>");
				return "no";                          
			} else {                                  
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放 ,同时保证不放大图片                                 
				if (this.proportion == true && (img.getWidth(null)>outputWidth && img.getHeight(null)>outputHeight)) {
					// 为等比缩放计算输出的图片宽度及高度                                          
					double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
					double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
					// 根据缩放比率大的进行缩放控制                                         
					double rate = rate1 > rate2 ? rate1 : rate2;  
					newWidth = (int) (((double) img.getWidth(null)) / rate); 
					newHeight = (int) (((double) img.getHeight(null)) / rate); 
				} else {
					newWidth = img.getWidth(null); 
					// 输出的图片宽度                                          
					newHeight = img.getHeight(null); 
					// 输出的图片高度                                  
				}                                  
				BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
				/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
				 * 优先级比速度高 生成的图片质量比较好 但速度慢
				 * */ 
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);  
				FileOutputStream out = new FileOutputStream(outputDir + outputFileName); 
				//JPEGImageEncoder可适用于其他图片类型的转换                                
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
				encoder.encode(tag);   
				if(out!=null)
					out.close();                          
			}                  
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if(is!=null)
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return "ok";         
	}          

	public String compressPic(String inputDir, String inputFileName,String outputDir,  String outputFileName) {
		//获得源文件    
		file = new File(inputDir + inputFileName);
		if (!file.exists()) {
			return "";                          
		}           
		// 输入图路径                  
		//this.inputDir = inputDir;                  
		// 输出图路径                  
		this.outputDir = outputDir;                  
		// 输入图文件名                  
		//this.inputFileName = inputFileName;
		// 输出图文件名                
		this.outputFileName = outputFileName;
		try {
			return compressPic(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}          
	}


	public String compressPic(String inputDir, String inputFileName,String outputDir, String outputFileName, int width, int height, boolean gp) {
		//获得源文件    
		file = new File(inputDir + inputFileName);
		if (!file.exists()) {
			return "";                          
		}      
		// 输入图路径                 
		//this.inputDir = inputDir;                  
		// 输出图路径                  
		this.outputDir = outputDir;                  
		// 输入图文件名                  
		//this.inputFileName = inputFileName;                  
		// 输出图文件名                  
		this.outputFileName = outputFileName;
		// 设置图片长宽                
		setWidthAndHeight(width, height);
		// 是否是等比缩放 标记                  
		this.proportion = gp;
		try {
			return compressPic(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}           
	}

	public String compressPic(InputStream is, String outputDir, String outputFileName, int width, int height, boolean gp) {

		// 输出图路径                  
		this.outputDir = outputDir;                  
		// 输入图文件名                  
		//this.inputFileName = inputFileName;                  
		// 输出图文件名                  
		this.outputFileName = outputFileName;
		if(new File(outputDir,outputFileName).exists())
			return null;
		// 设置图片长宽                
		setWidthAndHeight(width, height);
		// 是否是等比缩放 标记                  
		this.proportion = gp;
		return compressPic(is);
	}

	//	public void setInputDir(String inputDir) {
	//		this.inputDir = inputDir;          
	//	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;          
	}

	//	public void setInputFileName(String inputFileName) {
	//		this.inputFileName = inputFileName;         
	//	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;          
	}

	public void setOutputWidth(int outputWidth) {
		this.outputWidth = outputWidth;          
	}

	public void setOutputHeight(int outputHeight) {
		this.outputHeight = outputHeight;          
	}

	public void setWidthAndHeight(int width, int height) {
		this.outputWidth = width;                 
		this.outputHeight = height;          
	}
}