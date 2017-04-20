package com.ccx.credit.util;

import redis.clients.jedis.JedisCluster;
/**
 * redis 工具类
 * @author LHM
 *
 */
public class RedisCacheUtil {
	
	private static JedisCluster jedisCluster;

	public  JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public  void setJedisCluster(JedisCluster jedisCluster) {
		RedisCacheUtil.jedisCluster = jedisCluster;
	};
	
	
	/**
	   * 指定String  key  删除
	   * @param key
	   */
	  public static void delete(String key) {
		  jedisCluster.del(key);
	  }
	  /**
	   *  取出 缓存 数据
	   * @param key
	   * @return
	   */
	  public static String  get(String key) {
		  String value = jedisCluster.get(key);
	      return value;
	  }
	   /**
	    * 存入缓存数据
	    * @param key
	    * @param obj
	    */
	  public static  void set(String key,String value) {
		  jedisCluster.set(key, value);
	  }
	  /**
	   * 删除 key 存贮
	   * @param key
	   * @return
	   */
	  public static Long  del(String key) {
		  Long value = jedisCluster.del(key);
	      return value;
	  }
	  /**
	   * 设置 过期时间 单位秒
	   * @param key
	   * @param value
	   * @param seconds
	   * @return
	   */
	  public static  void setTimeSecond(String key,String value,int seconds ) {
		   jedisCluster.setex(key, seconds, value);
	  }
	  /**
	   * 设置 过期时间 单位毫秒
	   * @param key
	   * @param value
	   * @param milliseconds
	   * @return
	   */
	  public static  void setTimeMilliseconds(String key,String value,long milliseconds ) {
		   jedisCluster.psetex(key, milliseconds, value);
	  }
	  /**
	   * 设置 过期时间 以天为单位
	   * @param key
	   * @param value
	   * @param day
	   * @return
	   */
	  public static  void setTimeDay(String key,String value,int day ) {
		   jedisCluster.psetex(key, day*24*60*60, value);
	  }
	  /**
	   * 设置 过期时间 以小时为单位
	   * @param key
	   * @param value
	   * @param Hour
	   * @return
	   */
	  public static  void setTimeHour(String key,String value,int Hour ) {
		   jedisCluster.psetex(key, Hour*60*60, value);
	  }
	  /**
	   * 设置 过期时间 以分钟为单位
	   * @param key
	   * @param value
	   * @param minute
	   * @return
	   */
	  public static  void setTimeMinute(String key,String value,int minute ) {
		   jedisCluster.psetex(key, minute*60, value);
	  }
	  
}
