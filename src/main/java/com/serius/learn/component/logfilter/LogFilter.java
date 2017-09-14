package com.serius.learn.component.logfilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class LogFilter {

    /**
     * 转换成json格式，并且脱敏、截取过大参数值
     * @param object
     * @param fields
     * @return
     */
    public String toJSON(Object object, String[] largeParams, String[] sensitiveParams) {
    	if(object == null) {
            return "";
        }
    	int largeParamsLen = largeParams.length;
    	int sensitiveParamsLen = sensitiveParams.length;
    	if(largeParamsLen == 0 && sensitiveParamsLen == 0){
    		return JSON.toJSONString(object, SerializerFeature.PrettyFormat);
    	} else{
    		SerializeFilter[] filters = {};
        	List<SerializeFilter> filterList = new ArrayList<SerializeFilter>();
        	if(largeParamsLen > 0){
        		LargeValueFilter filter = new LargeValueFilter(largeParams);
        		filterList.add(filter);
        	}
        	if(sensitiveParamsLen > 0){
        		SensitiveFilter filter = new SensitiveFilter(sensitiveParams);
        		filterList.add(filter);
        	}
        	return JSON.toJSONString(object, filterList.toArray(filters), SerializerFeature.PrettyFormat);
    	}
    }
    
    /**
     * 转换成json格式
     * @param object
     * @return
     */
    public String toJSON(Object object) {
    	if(object == null) {
            return "";
        }
    	return JSON.toJSONString(object, SerializerFeature.PrettyFormat);
    }

    /**
     * create by tanweia on Jul 3, 2017
     * 参数过长过滤
     */
    protected static class LargeValueFilter implements ValueFilter {

        private final HashSet<String> fieldSet;

        public LargeValueFilter(String[] fields) {
            fieldSet = new HashSet<String>();
            fillSet(fields);
        }

        private void fillSet(String[] fields) {
            if(fields.length == 0) {
                return;
            }
            for(String f : fields) {
                if(!StringUtils.isEmpty(f)) {   //只添加非空白字符
                    fieldSet.add(f);
                }
            }

        }

        /**
         * FastJSON将会自动调用该方法完成过滤
         * <p>WARN: 此处严禁对object 和 value进行任何的设值操作，否则会改变堆中实际对象值，将会影响业务代码的实参</p>
         * @param object 参数对应对象
         * @param name 参数名
         * @param value 参数值
         * @return
         */
        @Override
        public Object process(Object object, String name, Object value) {
            if(StringUtils.isEmpty(name)) {
                return value;
            }
            
            if(fieldSet.contains(name)) {
                if(value instanceof String && value != null) {
                	String param = value.toString();
                	if(!StringUtils.isEmpty(param) && param.length() > 600){
                		param = param.subSequence(0, 600) + "...";
                	}
                	return param;
                }
            }

            return value;
        }
    }
    
    /**
     * create by tanweia on Jul 4, 2017
     * 敏感过滤
     */
    protected static class SensitiveFilter implements ValueFilter {

    	  private final HashSet<String> fieldSet;

          public SensitiveFilter(String[] fields) {
              fieldSet = new HashSet<String>();
              fillSet(fields);
          }

          private void fillSet(String[] fields) {
              if(fields.length == 0) {
                  return;
              }
              for(String f : fields) {
                  if(!StringUtils.isEmpty(f)) {   //只添加非空白字符
                      fieldSet.add(f);
                  }
              }

          }

        /**
         * FastJSON将会自动调用该方法完成过滤
         * <p>WARN: 此处严禁对object 和 value进行任何的设值操作，否则会改变堆中实际对象值，将会影响业务代码的实参</p>
         * @param object 参数对应对象
         * @param name 参数名
         * @param value 参数值
         * @return
         */
        @Override
        public Object process(Object object, String name, Object value) {
            if(StringUtils.isEmpty(name)) {
                return value;
            }

            if(fieldSet.contains(name)) {
            	 if(value instanceof String && value != null) {
                 	String param = value.toString();
                 	char[] charArray = param.toCharArray();
                 	int len = charArray.length;
                 	// 当数组长度为奇数时，掩码比显示位数多
                 	for (int i = len - 1; i > len / 2 - 1; i--) {
                 		charArray[i] = '*';
                 	}
                 	return String.valueOf(charArray);
                 }
            }

            return value;
        }
    }

}

