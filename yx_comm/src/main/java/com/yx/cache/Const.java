package com.yx.cache;

import org.springframework.stereotype.Component;

@Component
public class Const {

    public static String BASE_PATH;

    public static String SALT = "@#$%^&*()OPG#$%^&*(HG";

    public static String DES3_KEY = "9964DYByKL967c3308imytCB";

    public static String userAgent="Mozilla";

    public static String default_Profile=BASE_PATH+"/img/logo.jpg";

    public static String LAST_REFERER = "LAST_REFERER";

    public static int COOKIE_TIMEOUT= 30*24*60*60;

    public static String USER_ID = "userId";

    public static String GROUP_ID = "groupId";
}
