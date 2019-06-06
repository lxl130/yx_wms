package com.yx.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionFactory {
    @Autowired
    private ApplicationEntity appEntity;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private CookieHelper cookieHelper;

    public boolean Exist(String key)
    {
        if ("session".equals(appEntity.sessionType))
            return sessionHelper.Exist(key);
        else
            return cookieHelper.Exist(key);
    }

    public void Set(String key, String value)
    {
        if ("session".equals(appEntity.sessionType))
            sessionHelper.Set(key, value);
        else
            cookieHelper.Set(key, value);
    }

    public String Get(String key)
    {
        if ("session".equals(appEntity.sessionType))
            return sessionHelper.Get(key);
        else
            return cookieHelper.Get(key);
    }

    public void Delete(String key)
    {
        if ("session".equals(appEntity.sessionType))
            sessionHelper.Delete(key);
        else
            cookieHelper.Delete(key);
    }

    public String GetId()
    {
        if ("session".equals(appEntity.sessionType))
            return sessionHelper.GetId();
        else
            return cookieHelper.GetId();
    }
}
