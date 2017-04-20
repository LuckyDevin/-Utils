package com.ccx.credit.util;

import java.util.Date;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class BaseCache extends GeneralCacheAdministrator{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7047463729269332918L;
	
	private int refreshPeriod;
	private String keyPrefix;
	
	/**
	 * 构造器
	 * @param keyPrefix 关键字前缀
	 * @param refreshPeriod 过期时间
	 */
	public BaseCache(String keyPrefix, int refreshPeriod){
		super();
		this.keyPrefix = keyPrefix;
		this.refreshPeriod = refreshPeriod;
	}

	/**
	 * 添加被缓存的对象；
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		this.putInCache(this.keyPrefix + "_" + key, value);
	}
	
	public void remove(String key) {
		this.flushEntry(this.keyPrefix + "_" + key);
	}
	
	/**
	 * 删除所有的缓存对象
	 * @param date
	 */
	public void removeAll(Date date) {
		this.flushAll(date);
	}
	
	/**
	 * 删除所有缓存对象
	 */
	public void removeAll() {
		this.flushAll();
	}
	
	/**
	 * 获取缓存对象
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Object get(String key) throws Exception {
		try{
			return
			this.getFromCache(this.keyPrefix + "_" + key, this.refreshPeriod);
		} catch (NeedsRefreshException e){
			this.cancelUpdate(this.keyPrefix + "_" + key);
			throw e;
		}
	}

}
