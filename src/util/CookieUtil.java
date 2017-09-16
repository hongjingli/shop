package util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static String get(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie.getValue();
	    }else{
	        return null;
	    }   
	}
	
	public static void add(HttpServletResponse response, String name, String val) {
		Cookie cookie = new Cookie(name,  val);
		cookie.setMaxAge(7200);
		cookie.setPath("/");		
		response.addCookie(cookie);
	}	 
	
	public static void clear(HttpServletRequest request, HttpServletResponse reponse) {
		String cookieNames[] = {
				"username",
				"userkey"
		};
		for (String name : cookieNames) {
			Cookie c = new Cookie(name, null);
			c.setMaxAge(0);
			c.setPath("/");
			reponse.addCookie(c);
		}
	}	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){	    	
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
}
