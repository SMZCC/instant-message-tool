package com.vince.controller.util;
/**
 * @author 流浪大法师
 * @time 2016-3-22 下午8:00:09
 * @email liuliangsir@gmail.com
 * @descript 模拟ajax请求获取数据
 * @warning 注意将字符集设置成UTF-8
 */
import org.apache.commons.httpclient.HttpClient; 
import org.apache.commons.httpclient.HttpMethod; 
import org.apache.commons.httpclient.HttpStatus; 
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException; 
import org.apache.commons.httpclient.methods.GetMethod; 
import org.apache.commons.httpclient.methods.PostMethod; 
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams; 
import org.apache.commons.httpclient.util.URIUtil; 
import org.apache.commons.lang.StringUtils; 
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 
import org.apache.http.entity.StringEntity;

import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.Iterator; 
import java.util.Map; 

public final class HttpTookit { 
        private static Log log = LogFactory.getLog(HttpTookit.class); 

        /** 
         * 执行一个HTTP GET请求，返回请求响应的HTML 
         * 
         * @param url                 请求的URL地址 
         * @param queryString 请求的查询参数,可以为null 
         * @return 返回请求响应的HTML 
         */ 
        public static String doGet(String url, String queryString, boolean isSetAcceptJson) { 
                String response = null; 
                HttpClient client = new HttpClient(); 
                HttpMethod method = new GetMethod(url);
                if(isSetAcceptJson){
                	method.setRequestHeader("Accept", "application/json");
                }
                try { 
                        if (StringUtils.isNotBlank(queryString)) 
                                method.setQueryString(URIUtil.encodeQuery(queryString)); 
                        client.executeMethod(method); 
                        if (method.getStatusCode() == HttpStatus.SC_OK) { 
                                response = method.getResponseBodyAsString(); 
                        } 
                } catch (URIException e) { 
                        log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e); 
                } catch (IOException e) { 
                        log.error("执行HTTP Get请求" + url + "时，发生异常！", e); 
                } finally { 
                        method.releaseConnection(); 
                } 
                return response; 
        } 

        /** 
         * 执行一个HTTP POST请求，返回请求响应的HTML 
         * 
         * @param url        请求的URL地址 
         * @param params 请求的查询参数,可以为null 
         * @return 返回请求响应的HTML 
         */ 
        public static String doPost(String url, Map<String, String> params, boolean isSetAcceptJson) { 
                BufferedReader bufferedReader = null;
                HttpClient client = new HttpClient(); 
                PostMethod method = new PostMethod(url);
                NameValuePair[] data = null;
                StringBuilder result = new StringBuilder();
                int index = 0;
                String line = null;
                //设置Http Post数据 
                if(isSetAcceptJson){
                	method.setRequestHeader("Accept", "application/json");
                }
                if (params != null) { 
                		data = new NameValuePair[params.size()];
                        for (Map.Entry<String, String> entry : params.entrySet()) { 
                        	data[index++] = new NameValuePair(entry.getKey(),entry.getValue());
                        }
                }
                method.setRequestBody(data);
                try { 
                        client.executeMethod(method);
                        if (method.getStatusCode() == HttpStatus.SC_OK) { 
                                bufferedReader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
                                while ((line = bufferedReader.readLine()) != null) {
                                   result.append(line);
                                }
                                bufferedReader.close();
                        } 
                } catch (IOException e) { 
                        log.error("执行HTTP Post请求" + url + "时，发生异常！", e); 
                } finally { 
                        method.releaseConnection(); 
                } 

                return result.toString(); 
        }
}