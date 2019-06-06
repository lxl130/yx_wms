package com.yx.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEntity {
    @Value("${yx.com.cookie.type}")
    public String sessionType;

    @Value("${yx.com.cookie.domain}")
    public String cookieDomain;
}
