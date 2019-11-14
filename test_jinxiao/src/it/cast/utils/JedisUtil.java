package it.cast.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * jedis连接池工具类
 */
public class JedisUtil {

    private static JedisPool jedisPool = null;//jedis连接池对象

    //静态代码块初始化JedisPool
    static{
        //读取配置文件jedis.properties里面的配置信息
        //ResourceBundle,专门用于解析properties文件的，不需要后缀名
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jedis");
        String host = resourceBundle.getString("host");
        int port = Integer.parseInt(resourceBundle.getString("port"));
        int maxTotal = Integer.parseInt(resourceBundle.getString("maxTotal"));
        int maxIdle = Integer.parseInt(resourceBundle.getString("maxIdle"));
        //根据配置信息创建jedis配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        //初始化JedisPool
        jedisPool = new JedisPool(jedisPoolConfig,host,port);
    }


    /**
     * 返回一个jedis连接
     * @return
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    /**
     * 关闭资源
     * @param jedis
     */
    public static void close(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
        //jedisPool关闭确实可以防止虚拟机关闭后占用，但是这里不可以关闭，因为我们会重复使用连接池，所以不能关
//        if(jedisPool!=null){
//            jedisPool.close();
//        }
    }
}
