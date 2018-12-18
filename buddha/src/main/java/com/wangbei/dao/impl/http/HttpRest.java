package com.wangbei.dao.impl.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;


import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 
* @ClassName: msd.clb.foundation.dao.impl.rest 
* @Description: HttpClient请求抽象类 
* @author yuyidi0630@163.com 
* @date 2016年4月17日 下午6:39:44
 */
public abstract class HttpRest<T> {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected RestTemplate restTemplate;
	protected Class<T> entityClass;
	protected String entityName;
	
	@SuppressWarnings("unchecked")
	public HttpRest() {
		Type c = (((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]);
		if (c instanceof ParameterizedType) {
			entityClass = (Class<T>) ((ParameterizedType) c).getRawType();
		} else {
			entityClass = (Class<T>) c;
		}
		entityName = entityClass.getSimpleName();
	}

	public String doPostString(URI uri,T t) {
		try {
			return restTemplate.postForObject(uri, t, String.class);
		} catch (RestClientException e) {
			logger.error("post 请求出错：{}", uri, e);
		}
		return null;
	}


	public String doGetString(URI uri) {
		try {
			return restTemplate.getForObject(uri, String.class);
		} catch (RestClientException e) {
			logger.error("get 请求出错：{}", uri, e);
		}
		return null;
	}
	
	public String doGetString(String url) {
		URI uri =null;
		try {
			uri = new URIBuilder(url).build();
			return restTemplate.getForObject(uri, String.class);
		} catch (RestClientException | URISyntaxException e) {
			logger.error("get 请求出错：{}", uri, e);
		}
		return null;
	}

	/**
	 * 
	* @Title: doGetObject 
	* @Description: 不带参数的HttpGet请求-->查询
	* @author yuyidi0630@163.com
	* @param  url 请求链接
	* @return T
	* @throws
	 */
	public T doGetObject(String url) {
		HttpStatus status=null;
		try {
			ResponseEntity<T> response = restTemplate.getForEntity(url, entityClass);
			status = response.getStatusCode();
			if(status.equals(HttpStatus.OK)){
				return response.getBody();
			}
		} catch (RestClientException e) {
			logger.error("get 请求出错:{},Http 状态码 :{}", url,status, e);
		}
		return null;
	}

	/**
	 * 
	* @Title: doGetObject 
	* @Description: 带参数的HttpGet请求-->带参查询
	* @author yuyidi0630@163.com
	* @param  url 请求查询链接URL
	* @param  params 查询参数集合
	* @return T
	* @throws
	 */
	public T doGetObject(String url, Map<String, String> params) {
		HttpStatus status=null;
		try {
			ResponseEntity<T> response = restTemplate.getForEntity(url, entityClass,params);
			status = response.getStatusCode();
			if(status.equals(HttpStatus.OK)){
				return response.getBody();
			}
		} catch (RestClientException e) {
			logger.error("get 请求出错:{},Http 状态码 :{}", url,status, e);
		}
		return null;
	}
	
	/**
	 * 带参数的查询
	 * <p>Description: </p>
	 * @author w420372197@qq.com
	 * @date 2016年7月6日 下午3:39:47
	 * @param url
	 * @param params
	 * @return
	 */
	public String doGetString(String url,Map<String, String> params) {
		try {
			return restTemplate.getForObject(url, String.class);
		} catch (RestClientException e) {
			logger.error("get 请求出错：{}", url, e);
		}
		return null;
	}


	/**
	 * 
	* @Title: doPostObject 
	* @Description: 执行HttpPost请求-->新增
	* @author yuyidi0630@163.com
	* @param  uri 新增对象链接URL
	* @param  requestBody 新增实体对象
	* @return T
	* @throws
	 */
	public T doPostObject(URI uri, T requestBody) {
		HttpStatus status=null;
		try {
			ResponseEntity<T> response = restTemplate.postForEntity(uri, requestBody, entityClass);
			status = response.getStatusCode();
			if(status.equals(HttpStatus.CREATED)){
				return response.getBody();
			}
		} catch (RestClientException e) {
			logger.error("post 请求出错：{},Http 状态码 {} ：", uri,status, e);
		}
		return null;
	}
	
	/**
	 * <p>Description: </p>
	 * @author w420372197@qq.com
	 * @date 2016年7月6日 下午3:06:14
	 * @param url
	 * @param requestBody
	 * @return
	 */
	public <E> E doPostObject(String url, Object requestBody, Class<E> responseType) {
		HttpStatus status=null;
		try {
			ResponseEntity<E> response = restTemplate.postForEntity(new URI(url), requestBody, responseType);
			status = response.getStatusCode();
			if(status.equals(HttpStatus.CREATED)){
				return response.getBody();
			}
		} catch (RestClientException |URISyntaxException e) {
			logger.error("post 请求出错：{},Http 状态码 {} ：", url,status, e);
		}
		return null;
	}
	
	
	/**
	 * 
	* @Title: doPutObjectForUpdate 
	* @Description: 通过http post 的方式更新对象  有更新返回对象
	* @author yuyidi0630@163.com
	* @param  url 请求实体URL
	* @param  requestBody 待更新对象实体
	* @return T 返回已更新的实体
	* @throws
	 */
	public T doPutObjectForUpdate(String url, T requestBody) {
		HttpStatus status=null;
		try {
			ResponseEntity<T> response = restTemplate.postForEntity(url, requestBody, entityClass);
			status = response.getStatusCode();
			if(status.equals(HttpStatus.NO_CONTENT)){
				return response.getBody();
			}
		} catch (RestClientException e) {
			logger.error("put 请求出错：{},Http 状态码 {} ：", url,status, e);
		}
		return null;
	}
	
	/**
	 * 
	* @Title: doPutObject 
	* @Description: 执行HttpPut请求-->更新
	* @author yuyidi0630@163.com
	* @param  url 请求实体URL
	* @param  requestBody 待更新对象实体
	* @throws
	 */
	public void doPutObject(String url, T requestBody) {
		HttpStatus status=null;
		try {
			URI uri = new URIBuilder(url).build();
			restTemplate.put(uri, requestBody);
		} catch (RestClientException e) {
			logger.error("put 请求出错：{},Http 状态码 {} ：", url,status, e);
		} catch (URISyntaxException e) {
			logger.error("put 请求,url 转URI 异常",e);
		}
	}
	
	
	public Integer doDeleteObject(String url) {
		Integer result = 0;
		HttpStatus status=null;
		try {
			URI uri = new URIBuilder(url).build();
			restTemplate.delete(uri);
			result = 1;
		} catch (RestClientException e) {
			logger.error("put 请求出错：{},Http 状态码 {} ：", url,status, e);
		} catch (URISyntaxException e) {
			logger.error("put 请求,url 转URI 异常",e);
		}
		return result;
	}
}
