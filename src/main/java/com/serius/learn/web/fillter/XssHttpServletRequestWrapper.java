package com.serius.learn.web.fillter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.StringUtils;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	 	public XssHttpServletRequestWrapper(HttpServletRequest request) {  
	        super(request);  
	    }  
	  
	    @Override  
	    public String getHeader(String name) {  
	        return StringEscapeUtils.escapeHtml(super.getHeader(name));  
	    }  
	  
	    @Override  
	    public String getQueryString() {
	    	String value = super.getQueryString();
			if (!StringUtils.isEmpty(value)) {
				value = value.replaceAll("\"", "&quot;");
				value = value.replace("<", "&lt;");
				value = value.replaceAll(">", "&gt;");
			}
			return value;
	    }  
	  
	    @Override  
	    public String getParameter(String name) {  
	        return cleanXSS(super.getParameter(name));  
	    }  
	  
	    @Override  
	    public String[] getParameterValues(String name) { 
	        String[] values = super.getParameterValues(name);  
	        if(values != null) {  
	            int length = values.length;  
	            String[] escapseValues = new String[length];  
	            for(int i = 0; i < length; i++){
	            	escapseValues[i] = cleanXSS(values[i]);  
	            }  
	            return escapseValues;  
	        }  
	        return super.getParameterValues(name);  
	    }  
	    
	    private String cleanXSS(String value) {
	    	if(StringUtils.isEmpty(value)){
	    		return value;
	    	}
	    	
	    	if(isExcluded()){
	    		return cleanExcludedXSS(value);
	    	}
	    	
	    	value = StringEscapeUtils.escapeHtml(value);
	    	value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
	    	value = value.replace("*", "&#42;");
			value = value.replaceAll("'", "&#39;");
			value = value.replaceAll("\\\\", "&#92;");
	    	return value;
	    }
	    
	    private String cleanExcludedXSS(String value) {
	    	value = value.replaceAll("<script>", "&lt;script&gt;");
			value = value.replaceAll("</script>", "&lt;/script&gt;");
			return value;
	    }
	    
	    private boolean isExcluded() {
	    	Object object = super.getAttribute("isExcluded");
	    	if(object != null){
	    		return true;
	    	}
	    	return false;
	    }
}
