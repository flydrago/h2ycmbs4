package com.h2y.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.h2y.util.PropertiesUtil;

import java.util.Map;

/**
 * Memcached处理
 * <p/>
 * 作者：段晓刚
 * <p/>
 * 时间：2012-8-28 上午09:53:00
 * <p/>
 * 电子邮件：duanxg@hwttnet.com
 * <p/>
 * QQ：2410960521
 * <p/>
 * Gmail :
 */
public class MemcachedConn {

    private static String memcached_file = "/config.properties";

    private static String[] this_servers = new String[1];
    
    //设置初始连接数10
    private static int initialConnections = 10;
    
    //最小连接数 5
    private static int minSpareConnections = 5;

    // 最大连接数 50
    private static int maxSpareConnections = 50;

    private static long maxIdleTime = 1000 * 60 * 30; // 30 minutes

    private static long maxBusyTime = 1000 * 60 * 5; // 5 minutes


    //设置主线程睡眠时间 每隔30秒醒来  然后开始维护 连接数大小
    private static long maintThreadSleep = 1000 * 5; // 5 seconds

    //设置 读取 超时3秒钟
    private static int socketTimeOut = 1000 * 3; // 3 seconds to block on reads

    private static int socketConnectTO = 1000 * 3; // 3 seconds to block on initial

    // 关闭nagle算法
    private static boolean nagleAlg = false; // turn off Nagle's algorithm on all sockets in

    static String keyFlg = "";

    static {

        PropertiesUtil mPropertiesUtil = PropertiesUtil.getInstance(memcached_file);

        Map<String,String> valMap = mPropertiesUtil.getMap();

        if (valMap.get("servers") != null) {
            String servers = valMap.get("servers");

            if (servers.contains(";")) {
                this_servers = servers.split(";");
            } else {
                this_servers[0] = servers;
            }

        }

        if (valMap.get("initialConnections") != null) {
            initialConnections = Integer.parseInt(valMap.get("initialConnections"));
        }
        if (valMap.get("minSpareConnections") != null) {
            minSpareConnections = Integer.parseInt(valMap.get("minSpareConnections"));
        }
        if (valMap.get("maxSpareConnections") != null) {
            maxSpareConnections = Integer.parseInt(valMap.get("maxSpareConnections"));
        }
        if (valMap.get("maxIdleTime") != null) {
            maxIdleTime = Integer.parseInt(valMap.get("maxIdleTime"));
        }
        if (valMap.get("maxBusyTime") != null) {
            maxBusyTime = Integer.parseInt(valMap.get("maxBusyTime"));
        }
        if (valMap.get("maintThreadSleep") != null) {
            maintThreadSleep = Integer.parseInt(valMap.get("maintThreadSleep"));
        }
        if (valMap.get("socketTimeOut") != null) {
            socketTimeOut = Integer.parseInt(valMap.get("socketTimeOut"));
        }
        if (valMap.get("socketConnectTO") != null) {
            socketConnectTO = Integer.parseInt(valMap.get("socketConnectTO"));
        }
        if (valMap.get("nagleAlg") != null) {
            if (valMap.get("nagleAlg").equals("true"))
                nagleAlg = true;
            else {
                nagleAlg = false;
            }
        }
    }

    /**
     * SockIOPool连接池
     */
    private static SockIOPool pool = SockIOPool.getInstance();


    private MemcachedConn() {
        super();
    }

    public static MemcachedConn getInstance() {

        conn();//获取连接

        return new MemcachedConn();
    }

    public static MemCachedClient getMemCachedClient() {

        conn();//获取连接
        /**
         * 获取客户端连接
         */
        MemCachedClient memCachedClient = new MemCachedClient();

        return memCachedClient;
    }

    private static void conn() {

//		pool.setServers(this_servers);   
//		pool.setFailover(true);   
//		pool.setInitConn(10);   
//		pool.setMinConn(5);   
//		pool.setMaxConn(250);   
//		pool.setMaintSleep(30);   
//		pool.setNagle(false);   
//		pool.setSocketTO(3000);   
//		pool.setAliveCheck(true);   
//		pool.initialize();  

        pool.setServers(this_servers);
        pool.setInitConn(initialConnections);
        pool.setMinConn(minSpareConnections);
        pool.setMaxConn(maxSpareConnections);
        pool.setMaxIdle(maxIdleTime);
        pool.setMaxBusyTime(maxBusyTime);
        pool.setMaintSleep(maintThreadSleep);
        pool.setSocketTO(socketTimeOut);
        pool.setSocketConnectTO(socketConnectTO);
        pool.setNagle(nagleAlg);
        pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
        pool.initialize();
    }
}