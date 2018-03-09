package com.serius.learn.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


/**
 * Created by cdtanwei1 on Mar 9, 2018
 */
public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	/**
	 * POST请求
	 * @param serverUrl
	 * @param jsonParam
	 * @return
	 */
	public static String post(String serverUrl, String jsonParam) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(jsonParam, headers);
		String result = restTemplate.postForObject(serverUrl, request, String.class);
		return result;
	}
	
	
	/** 
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址; 
     *  
     * @param request 
     * @return 
     */  
    public final static String getIpAddress(HttpServletRequest request) {  
    	// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址  
        String ip = request.getHeader("X-Forwarded-For");  
        logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip={}",ip);  
  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
                logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip={}",ip);  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
                logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip={}",ip);  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
                logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip={}",ip);  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
                logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip={}",ip);  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getRemoteAddr();  
                logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip={}",ip);  
            }  
        } else if (ip.length() > 15) {  
            String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }  
        }  
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip; 
    }
	
}
