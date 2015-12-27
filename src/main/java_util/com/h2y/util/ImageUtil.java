package com.h2y.util;

import com.h2y.security.MD5Util;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 生成图片
 * 对原始map进行处理，获取需要在Android客户端展示使用的map

 * @author：段晓刚

 * @update：2015年4月7日 上午11:21:57

 * @Email：
 */
@SuppressWarnings("restriction")
public class ImageUtil {

    /**
     * 获取配置信息
     */
    private static PropertiesUtil mPropertiesUtil;

    //加载Image时候需要的使用的servlet
    private static String showImageUrl = "";

    private static String mImgPath = "";

    private static String mBasePath = "";

    private static Logger logger = Logger.getLogger(ImageUtil.class);

    static {

        mPropertiesUtil = PropertiesUtil.getInstance("/config.properties");
        if (mPropertiesUtil != null) {
            showImageUrl = mPropertiesUtil.getValueByKey("webservice.showimage.url");
            String sp = mPropertiesUtil.getValueByKey("config.project.path");
            if (sp != null && !"null".equals(sp) && !"".equals(sp))
                mBasePath = sp;
            String cmp = mPropertiesUtil.getValueByKey("config.img.path");
            if (cmp != null && !"null".equals(cmp) && !"".equals(cmp)) {
                mImgPath = cmp;
            }
            if (!new File(mImgPath).exists()) {
                new File(mImgPath).mkdirs();
            }
        }
    }

    /**
     * 传进正文生成客户端列表图片和缩略图片
     * 返回值为客户端列表展示图片
     * 生成图片名称以原始图片名称的MD5为名称，后缀不明的一律以jpg命名
     *
     * @param content
     * @return
     */
    public static String createShowImage(String content) {
        String listImgPath = "";

        if (content == null || "".equals(content) || "null".equals(content)) {

        } else {
            List<String> mImgList = HtmlUtil.getImagTagValues(content);

            if (mImgList != null && mImgList.size() > 0) {
                int len = mImgList.size();
                try {
                    for (int i = 0; i < len; i++) {
                        String mImgPath = mImgList.get(i);
                        if (i == 0) {
                            listImgPath = createShowImag(mImgPath, ShowImgType.ShowList);
                        }
                        createShowImag(mImgPath, ShowImgType.ShowSmall);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        if (listImgPath.equals("")) {
            String savePath = mImgPath + File.separator + "img" + File.separator;
            if (content == null || "".equals(content) || "null".equals(content)) {
                content = "空";
            }
            String imageValue = content.length() >= 4 ? content.substring(0, 4) : content;

            String saveName = MD5Util.getMD5(imageValue) + ".jpg";
            savePath += Math.abs(saveName.hashCode()) + File.separator;
            savePath += Math.abs(savePath.hashCode()) + File.separator;
            savePath += Math.abs(savePath.hashCode()) + File.separator;
            File mFile = new File(savePath);
            if (!mFile.exists())
                mFile.mkdirs();
            File mFile2 = new File(savePath, saveName);
            if (!mFile2.exists())
                createImage(imageValue, savePath + saveName);
            listImgPath = savePath + saveName;
        }

        listImgPath = listImgPath.replaceAll("\\\\", "/");
        listImgPath = listImgPath.replaceAll("//", "/");
        return listImgPath;
    }

    private static String createShowImag(String mImgPath, ShowImgType mShowImgType) throws IOException {
        if (mImgPath != null) {
            mImgPath = mImgPath.trim();
            if (mImgPath.startsWith("http:/")) {

            } else {
                mImgPath = mBasePath + mImgPath;
            }
            return getCreateShowImageByURL(mImgPath, mShowImgType);
        } else {
            return null;
        }
    }

    private static String getCreateShowImageByURL(String urlPath, ShowImgType mShowImgType) throws IOException {
        String saveName = MD5Util.getMD5(urlPath) + "";
        String str = urlPath.substring(urlPath.lastIndexOf(".") + 1);
        String regStr = "[Gg][Ii][Ff]|[Jj][Pp][Gg]|[Bb][Mm][Pp]|[Jj][Pp][Ee][Gg]";
        if (!str.matches(regStr)) {
            saveName += ".jpg";
        } else {
            saveName += "." + str;
        }
        String tmpSaveName = "tmp_" + saveName;

        String tmpSavePath = mImgPath + File.separator + "tmp_img" + File.separator;
        tmpSavePath += Math.abs(tmpSaveName.hashCode()) + File.separator;
        tmpSavePath += Math.abs(tmpSavePath.hashCode()) + File.separator;
        tmpSavePath += Math.abs(tmpSavePath.hashCode()) + File.separator;

        String savePath = mImgPath + File.separator + "img" + File.separator;
        savePath += Math.abs(saveName.hashCode()) + File.separator;
        savePath += Math.abs(savePath.hashCode()) + File.separator;
        savePath += Math.abs(savePath.hashCode()) + File.separator;

        File mTmpFile = new File(tmpSavePath);
        if (!mTmpFile.exists())
            mTmpFile.mkdirs();
        File mSaveFile = new File(savePath);
        if (!mSaveFile.exists())
            mSaveFile.mkdirs();

        //判断文件是否已经存在
        File mFile = new File(tmpSavePath, tmpSaveName);
        if (!mFile.exists()) {
            //获取网络临时文件
            try {
                getTmpImgByURL(urlPath, tmpSavePath, tmpSaveName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }

        return createShowImgByFile(tmpSavePath, savePath, saveName, mShowImgType);
    }

    /**
     * 临时文件已经在的情况下进行图片
     *
     * @param savePath
     * @param saveName
     * @param mShowImgType
     * @return
     * @throws java.io.IOException
     */
    private static String createShowImgByFile(String tmpSavePath, String savePath, String saveName, ShowImgType mShowImgType) throws IOException {

        String tmpSaveName = "tmp_" + saveName;
        String listSaveName = "list_" + saveName;
        String smallSaveName = "small_" + saveName;
        CompressImageUtil mCompressImageUtil = CompressImageUtil.getInstance();

        File mTmpFile = new File(tmpSavePath, tmpSaveName);
        Image img = ImageIO.read(mTmpFile);
        // 判断图片格式是否正确
        if (img.getWidth(null) == -1) {
            logger.debug(" can't read,retry!" + "<BR>");
            try {
                if (!new File(saveName, smallSaveName).exists()) {
                    copyFile(tmpSavePath + tmpSaveName, saveName + smallSaveName);
                }
                if (!new File(saveName, listSaveName).exists()) {
                    copyFile(tmpSavePath + tmpSaveName, savePath + listSaveName);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                logger.debug(e.getMessage(), e);
            }
        } else {
            int newWidth = img.getWidth(null);
            int newHeight = img.getHeight(null);

            if (newWidth > 300 || newHeight > 300) {
                //进行图片压缩以适合手机上使用
                if (mShowImgType.showImgType.equals(ShowImgType.ShowSmall.showImgType)) {
                    if (!new File(savePath, smallSaveName).exists())
                        mCompressImageUtil.compressPic(tmpSavePath, tmpSaveName, savePath, smallSaveName, 300, 300, true);
                } else {
                    if (!new File(savePath, listSaveName).exists()) {
                        String flg = mCompressImageUtil.compressPic(tmpSavePath, tmpSaveName, savePath, "1_" + listSaveName, 160, 160, true);
                        if (flg != null && "ok".equals(flg))
                            ImageUtil.cutImage(savePath + "1_" + listSaveName, savePath + listSaveName, 120, 120);
                    }
                }
            } else {
                try {
                    if (!new File(saveName, smallSaveName).exists()) {
                        copyFile(tmpSavePath + tmpSaveName, saveName + smallSaveName);
                    }
                    if (!new File(saveName, listSaveName).exists()) {
                        copyFile(tmpSavePath + tmpSaveName, savePath + "1_" + listSaveName);
                        ImageUtil.cutImage("1_" + listSaveName, listSaveName, 120, 120);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    logger.debug(e.getMessage(), e);
                }
            }
        }
        if (mShowImgType.showImgType.equals(ShowImgType.ShowSmall)) {
            return savePath + smallSaveName;
        } else {
            return savePath + listSaveName;
        }
    }

    /**
     * 通过网络获取临时文件
     *
     * @param urlPath 网络地址
     * @return 临时文件存放地址
     * @throws Exception
     */
    private static void getTmpImgByURL(String urlPath, String savePath, String saveName) throws Exception {
        //文件不存在的情况下开始准备文件生成
        URLConnection mUrlConnection = null;
        URL url = new URL(urlPath);
        mUrlConnection = url.openConnection();

        if (mUrlConnection != null) {
            //此连接的 URL 引用的资源的内容长度，或者如果内容长度未知，则返回 -1。
            long fileSize = mUrlConnection.getContentLength();
            if (fileSize <= 0) {
                return;
            }
            BufferedInputStream bis = new BufferedInputStream(mUrlConnection.getInputStream());

            //生成临时文件
            ImageUtil.copyByInputStream(bis, savePath, saveName);
        }
    }

    /**
     * 通过输入字符串s以及图片路径filepath截取s的前四个字符串升级对应的图片
     *
     * @param s        需要写入图片中的字符串
     * @param filepath 图片生成或存放的地址
     */
    public static void createImage(String s, String filepath) {
        int width = 120;
        int height = 120;
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();

        // ----------   增加下面的代码使得背景透明   -----------------
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
        // ----------   背景透明代码结束   -----------------

        // 画图
        g2d.setColor(new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(1));

        int len = s.length();

        Font mFont = new Font("宋体", Font.PLAIN, 40);//默认字体
        if (len == 1) {
            mFont = new Font("宋体", Font.PLAIN, 80);//默认字体
        }
        if (len == 2) {
            mFont = new Font("宋体", Font.PLAIN, 50);//默认字体
        }
        g2d.setColor(new Color(Integer.parseInt("000000", 16)));
        g2d.setFont(mFont);

        if (s == null || "".equals(s))
            return;
        else {
            if (len == 1) {
                g2d.drawString(s, 20, 85);
            }
            if (len == 2) {
                g2d.drawString(s, 10, 75);
            }
            if (len > 2) {
                g2d.drawString(s.substring(0, 2), 20, 50);
            }
            if (len >= 3) {
                g2d.drawString(s.substring(2, len >= 4 ? 4 : 3), 20, 90);
            }
            //			g2d.drawString("段晓", 20, 50);
            //			g2d.drawString("你好", 20, 90);
            //释放对象
            g2d.dispose();
        }


        // 保存文件
        try {
            ImageIO.write(image, "png", new File(filepath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 进行图片切割获取最中间部分图片
     *
     * @param imgPath
     * @param destDir
     * @param width
     * @param height
     */
    public static void cutImage(String imgPath, String destDir, int width, int height) {
        //开始剪裁的坐标位置
        int x = 0;
        int y = 0;

        try {
            Image img = ImageIO.read(new File(imgPath));
            int w = img.getWidth(null);
            int h = img.getHeight(null);
            if (w < width || h < width) {
                width = height = w > h ? h : w;
            } else {
                x = (w - height) / 2;
                y = (h - width) / 2;
            }
            img.flush();

            //cutImage(imgPath, destDir,x,y, width, height);
            cutImageByJPEGImageEncoder(imgPath, destDir, x, y, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 截图
     *
     * @param imgPath
     * @param destDir
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public static void cutImageByJPEGImageEncoder(String imgPath, String destDir, int x, int y, int width, int height) {
        BufferedImage distinImage = null;
        try {

            //Image img = ImageIO.read(new File(imgPath));

            distinImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // 获取Graphics2D
            Graphics2D g2d = distinImage.createGraphics();

			/*
             * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
			 * 优先级比速度高 生成的图片质量比较好 但速度慢
			 * */
            g2d.drawImage(ImageIO.read(new File(imgPath)), 0, 0, width, height, x, y, x + width, y + height, null);

            g2d.dispose();

            if (distinImage != null) {

                try {
                    distinImage.flush();
                    FileOutputStream fos = new FileOutputStream(destDir);

                    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
                    encoder.encode(distinImage);

                    if (fos != null)
                        fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 截图
     *
     * @param imgPath
     * @param destDir
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public static void cutImage(String imgPath, String destDir, int x, int y, int width, int height) {
        BufferedImage bImage = null;

        BufferedImage distinImage = null;
        try {

            bImage = ImageIO.read(new File(imgPath));

            distinImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // 获取Graphics2D
            Graphics2D g2d = distinImage.createGraphics();

            //			// ----------   增加下面的代码使得背景透明   -----------------
            distinImage = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = distinImage.createGraphics();
            // ----------   背景透明代码结束   -----------------
            // 原图的大小，以及截图的坐标及大小
            g2d.drawImage(bImage, 0, 0, width, height, x, y, x + width, y + height, null);
            g2d.dispose();

            if (distinImage != null) {

                try {
                    bImage.flush();
                    distinImage.flush();
                    FileOutputStream fos = new FileOutputStream(destDir);

                    ImageIO.write(distinImage, "png", fos);
                    if (fos != null)
                        fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 加入在列表展示是使用的图片的url地址；
     * 如果map中有大字段在获取图片信息之后对该大字段进行制空
     *
     * @param key        获取图片的Web正文(从中抽取图片进行列表展示)
     * @param imageTitle 在正文无法展示数据时通过该字段值生成填充图片
     * @param map        查询出的原始map
     * @return
     */
    public static Map<String, Object> addImageUrl(String key, String imageTitle, Map<String, Object> map) throws UnsupportedEncodingException {

        if (map != null && !map.isEmpty()) {
            Object obj = map.get(key);
            if (obj != null) {
                String objS = (obj + "").trim();
                String imageS = HtmlUtil.getFirstImage(objS, showImageUrl);
                if (imageS == null || "".equals(imageS)) {
                    String ss = "";

                    if (imageTitle.length() > 4) {
                        ss = imageTitle.substring(0, 4);
                    } else
                        ss = imageTitle;

                    //System.out.println(ss);
                    String filename = MD5Util.getMD5(ss) + ".png";
                    //imageS = showImageUrl+"?URLPATH="+small_path+"/"+filename+"&IMAGEVALUE="+URLEncoder.encode(ss,"UTF-8")+"&SHOW_TYPE=LIST\"";
                    imageS = showImageUrl + "?URLPATH=/" + filename + "&IMAGEVALUE=" + URLEncoder.encode(ss, "UTF-8") + "&SHOW_TYPE=LIST\"";
                }
                map.remove(key);
                map.put("LISTIMAGE", imageS);
            }
            return map;
        } else
            return map;
    }

    /**
     * 文件Copy处理
     *
     * @param srcPath
     * @param destPath
     * @return
     * @throws Exception
     */
    public static void copyFile(String srcPath, String destPath) throws Exception {
        File srcFile = new File(srcPath);
        FileInputStream fis = new FileInputStream(srcFile);
        copyByInputStream(fis, destPath);
    }

    /**
     * 通过io流copy文件
     *
     * @param is       文件输入流
     * @param filepath 存储文件路径
     * @throws Exception
     */
    public static void copyByInputStream(InputStream is, String filepath) throws Exception {
        File file = new File(filepath);
        copyByInputStream(is, file.getParent(), file.getName());
    }

    /**
     * 通过io流copy文件
     *
     * @param is       文件输入流
     * @param filepath 存储文件路径
     * @param fileName 存储文件名称
     * @throws Exception
     */
    public static void copyByInputStream(InputStream is, String filepath, String fileName) throws Exception {
        //32K的数据缓冲
        byte[] bs = new byte[1024 * 32];
        // 读取到的数据长度
        int len;
        File file = new File(filepath);
        if (!file.exists())
            file.mkdirs();
        // 输出的文件流
        OutputStream os = new FileOutputStream(filepath + File.separator + fileName);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        if (os != null)
            os.close();
        if (is != null)
            is.close();
    }

    /**
     * 展示形式
     * <p/>
     * 作者：段晓刚
     * <p/>
     * 时间：2013-12-3 下午3:27:59
     * <p/>
     * 电子邮件：
     * <p/>
     * QQ：2410960521
     * <p/>
     * Gmail :
     */
    public enum ShowImgType {

        ShowList("showlist"),
        ShowSmall("showsmall");

        public String showImgType = "";

        private ShowImgType(String showImgType) {
            this.showImgType = showImgType;
        }
    }
}