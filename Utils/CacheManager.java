package com.ccx.credit.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccx.credit.model.NegativeBean;

public class CacheManager {
	
	private static final Logger logger = LoggerFactory.getLogger(CacheManager.class);
	
	private BaseCache cache;
	
	private static CacheManager instance;
	private static Object lock = new Object();
	
	public CacheManager(){
		cache = new BaseCache("",1800*24);
	}

	/**
	 * 单例化一个缓存管理对象
	 * @return
	 */
	public static CacheManager getInstance() {
		if (instance == null) {
			synchronized( lock ){
				if (instance == null) {
					 instance = new CacheManager();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 将对象放入缓存
	 * @param key
	 * @param list
	 */
	public void putNagatives(String key, List<NegativeBean> list) {
		cache.put(key, list);
	}
	
	/**
	 * 将对象移除缓存
	 * @param key
	 */
	public void removeNegatives(String key) {
		cache.remove(key);
	}
	
	public List<NegativeBean> getNegatives(String key){
		try {
			return (List<NegativeBean>) cache.get(key);
		} catch (Exception e) {
			logger.debug("getNegativeBeanList>>key【" + key+"】异常");
			return null;
		}
	}
	
	/**
	 * 根据Key值获取缓存数据
	 * @param key
	 * @return
	 */
	public Object getCache(String key){
		Object cacheObj = null;
		try {
//			logger.debug("根据Key值获取缓存数据-->Key值：【 " + key+"】");
			cacheObj = cache.get(key);
		} catch (Exception e) {
			logger.error("根据Key值获取缓存为空-->key值：【 " + key+"】");
			return null;
		}
		return cacheObj;
	}
	
	/**
	 * 缓存结果
	 * @param key
	 * @param obj
	 */
	public void putCache(String key,Object obj){
		cache.put(key, obj);
	}
	
	/**
	 * 清除所有缓存
	 */
	public void removeAll(){
		cache.removeAll();
	}
	
	
}
