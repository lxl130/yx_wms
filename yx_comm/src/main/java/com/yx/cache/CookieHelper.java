package com.yx.cache;

import com.yx.utility.Des3EncryptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class CookieHelper {
    @Autowired
    private ApplicationEntity appEntity;

    public HttpServletRequest ContextRequest()
    {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpServletResponse ContextResponse()
    {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public boolean Exist(String key)
    {
        Cookie cookie = readCookieMap(ContextRequest()).get(key);
        if (cookie == null) cookie = new Cookie(key, "");
        cookie.setDomain(appEntity.cookieDomain);
        return !("".equals(cookie.getValue()));
    }

    public void Set(String key, String value)
    {
        Cookie cookie = readCookieMap(ContextRequest()).get(key);
        if (cookie == null) cookie = new Cookie(key, "");
        value = Des3EncryptionUtil.encode(Const.DES3_KEY, value + Const.SALT);
        cookie.setValue(value);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        cookie.setDomain(appEntity.cookieDomain);
        ContextResponse().addCookie(cookie);
    }

    public String Get(String key)
    {
        Cookie cookie = readCookieMap(ContextRequest()).get(key);
        if(cookie == null) return "";
        String cookieValue = cookie.getValue();
        String newValue = Des3EncryptionUtil.decode(Const.DES3_KEY, cookieValue);
        if(StringUtils.isBlank(newValue)) return "";
        return newValue.substring(0, newValue.indexOf(Const.SALT));
    }

    public void Delete(String key)
    {
        Cookie cookie = readCookieMap(ContextRequest()).get(key);
        if (cookie == null) return;
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain(appEntity.cookieDomain);
        ContextResponse().addCookie(cookie);
    }

    public String GetId()
    {
        return "";
    }

    //获取Cookie的所有值
    public Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
