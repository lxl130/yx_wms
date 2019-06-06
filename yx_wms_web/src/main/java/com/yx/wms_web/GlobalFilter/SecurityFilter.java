package com.yx.wms_web.GlobalFilter;

import com.yx.model.Base.Const;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SecurityFilter implements Filter {
	//绿色通道
	private static Set<String> GreenUrlSet = new HashSet<>();

	@Override
	public void init(FilterConfig arg0) {
		GreenUrlSet.add("/Login");
		GreenUrlSet.add("/Login/");
		GreenUrlSet.add("/Login/index");
	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)srequest;

        //验证请求后缀||绿色通道||包含地址
        String uri = request.getRequestURI();
        boolean isGreen = containsSuffix(uri) || GreenUrlSet.contains(uri) || containsKey(uri);
        if (isGreen){
            filterChain.doFilter(srequest, sresponse);
            return;
        }

        //验证登录
        boolean isLogin = ExitCookie(Const.USER_ID, request);
        if (isLogin) {
            filterChain.doFilter(srequest, sresponse);
            return;
        }
        String html ="<script type=\"text/javascript\">window.location.href=\"/Login\"</script>";
        sresponse.getWriter().write(html);
	}

	/**
	 * @param url
	 * @return boolean
	 * @author LiuWenLong
	 * @date 2019-4-20 15:32:29
	 */
	private boolean containsSuffix(String url) {
        return url.endsWith(".js")
                || url.endsWith(".css")
                || url.endsWith(".jpg")
                || url.endsWith(".gif")
                || url.endsWith(".png")
                || url.endsWith(".html")
                || url.endsWith(".htm")
                || url.endsWith(".eot")
                || url.endsWith(".svg")
                || url.endsWith(".ttf")
                || url.endsWith(".woff")
                || url.endsWith(".ico")
                || url.endsWith(".woff2");
	}

	/**
	 * @param url
	 * @return boolean
	 * @author LiuWenLong
	 * @date 2019-4-20 15:32:29
	 */
	private boolean containsKey(String url) {
        return url.contains("/Login") || url.contains("/login");
	}

    public boolean ExitCookie(String key, HttpServletRequest request)
    {
        Cookie cookie = ReadCookieMap(request).get(key);
        return cookie != null && !StringUtils.isBlank(cookie.getValue());

    }
    //获取Cookie的所有值
    protected Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}