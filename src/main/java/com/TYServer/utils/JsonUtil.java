package com.TYServer.utils;

import com.jayway.jsonpath.JsonPath;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;


public class JsonUtil {
	static String jsonData = "";
	
	public static String MapToJson(Map<String, Object> obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonData = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonData;
	}

	public static JSONObject JsonToJsonObject(String json){
		JSONObject jObject = new JSONObject(json);
		return jObject;
	}
	
	public static String resultSetToJson(ResultSet rs){
        // 
        JSONArray array = new JSONArray();

        //
        ResultSetMetaData metaData = null;
		try {
			metaData = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        int columnCount = 0;
		try {
			columnCount = metaData.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}

        //
        try {
			while (rs.next()) {
			    JSONObject jsonObj = new JSONObject();

			    // 
			    for (int i = 1; i <= columnCount; i++) {
			        String columnName = metaData.getColumnLabel(i);
			        String value = rs.getString(columnName);
			        jsonObj.put(columnName, value);
			    }
			    array.put(jsonObj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return array.toString();
    }
	@SuppressWarnings("rawtypes")
	public static Object convertMap(Class type, Map map) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象
		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
		for (int i = 0; i< propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args[0] = value;

				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}
	public static Object getValByJsonPath(String jsonpath, String responseContent) {
		Object value = null;
		try {
			value = (Object) JsonPath.read(responseContent, jsonpath);
		} catch (Exception e) {
			value=null;
		}
		return value;
	}
	
	public static int getIntValByJsonPath(String jsonpath, String responseContent){
		return Integer.parseInt(getValByJsonPath(jsonpath, responseContent)+"");
	}
	
	public static boolean getBoolValByJsonPath(String jsonpath, String responseContent){
		return Boolean.parseBoolean(getValByJsonPath(jsonpath, responseContent)+"");
	}
	
	public static double getDoubleValByJsonPath(String jsonpath, String responseContent){
		return Double.parseDouble(getValByJsonPath(jsonpath, responseContent)+"");
	}
	
	public static String getStringValByJsonPath(String jsonpath, String responseContent){
		return getValByJsonPath(jsonpath, responseContent)+"";
	}

}
