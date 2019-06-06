package com.yx.cache;

import com.yx.utility.CommFunc;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class SessionHelper {

    public HttpServletRequest ContextRequest()
    {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public boolean Exist(String key)
    {
        if(ContextRequest() == null) return false;
        Object session = ContextRequest().getSession().getAttribute(key);
        if (session != null)
        {
            return !"".equals(session.toString());
        }

        return true;
    }

    public void Set(String key, String value)
    {
        if(ContextRequest() == null) return;
        value = CommFunc.UrlEncode(value);
        ContextRequest().getSession().setAttribute(key, value);
    }

    public String Get(String key)
    {
        if(ContextRequest() == null) return "";
        Object session = ContextRequest().getSession().getAttribute(key);
        if (session != null)
        {
            return CommFunc.UrlDecode(session.toString());
        }
        return "";
    }

    public void Delete(String key)
    {
        if(ContextRequest() == null) return;
        ContextRequest().getSession().removeAttribute(key);
    }

    public String GetId()
    {
        if(ContextRequest() == null) return "";
        return ContextRequest().getSession().getId();
    }
}
