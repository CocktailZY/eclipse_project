package com.ldh.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * <p>Title:JsonUtil.java</p>
 * Description:Json操作工具类
 * @author zy
 * @date  2016-8-24
 */
public class JsonUtil {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * <p>Title:resultMap</p>
	 * Description:组装JSON字符串返回
	 * @param key 键名称
	 * @param message 值
	 * @return String
	 * @author zy
	 * @date  2016-8-24
	 */
	public static String resultMap(String key, String message){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, message);
		return toJson(map);
	}
	
	/**
	 * <p>Title:toJson</p>
	 * Description:将对象转换成json格式的字符串
	 * @param obj 将要转换的对象
	 * @return String 返回该对象转换的JSON格式的字符串
	 * @author zy
	 * @date  2016-8-24
	 */
	public static String toJson(Object obj) {
		JSONObject jsonObj = JSONObject.fromObject(obj);
		return jsonObj.toString();
	}

	/**
	 * <p>Title:toJsonByListObj</p>
	 * Description:将List集合转换成json字符串
	 * @param listObj List集合
	 * @return String
	 * @author zy
	 * @date  2016-8-24
	 */
	public static String toJsonByListObj(List<Object> listObj) {
		JSONArray listArray = JSONArray.fromObject(listObj);
		return listArray.toString();
	}

    /**
     * <p>Title:fromJson</p>
     * Description: json字符串转成对象 
     * @param jsonStr json字符串
     * @param clazz	需要转换成的类对象
     * @return Object
     * @author zy
     * @date  2016-8-24
     */
    @SuppressWarnings({ "static-access", "rawtypes" })
	public static Object fromJson(String jsonStr, Class clazz) {  
    	JSONObject jsonObject = JSONObject.fromObject(jsonStr);
    	Object obj = jsonObject.toBean(jsonObject, clazz);
        return obj;        
    }  
    
    /**
     * <p>Title:fromListJson</p>
     * Description:JSON数组字符串转换为List对象集合
     * @param jsonStr	json字符串
     * @param clazz		需要转换成的对象
     * @return List<T>  List集合
     * @author zy
     * @date  2016-8-24
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> fromListJson(String jsonStr, Class clazz) {
    	JSONArray jsonArray = JSONArray.fromObject(jsonStr);
    	List<T> list = (List<T>) JSONArray.toList(jsonArray, clazz);
        return list;        
    } 
    
    /**
     * <p>方法功能描述：对象转为json</p>
     * @方法作者：zy
     * @创建时间：2016-9-23 下午1:27:00
     * @参数：@param data
     * @参数：@return    
     * @返回类型：String   
     * @throws
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }

    
}
