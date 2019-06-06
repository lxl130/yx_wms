package com.yx.utility;

import java.text.SimpleDateFormat;
import java.net.*;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommFunc {

    public static final Lock lock = new ReentrantLock();//锁对象

    //获取主键ID
    public static String GetId(String dataId)
    {
        lock.lock();
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyMMddHHmmssSSSS");//设置日期格式
        try {
            Thread.sleep(1);
            return dataId + DateFormat.format(new Date());
        }
        catch(Exception ex){
            return dataId + DateFormat.format(new Date());
        }
        finally {
            lock.unlock();
        }
    }

    /// <summary> URL编码 </summary>
    public static String UrlEncode(String value)
    {
        return UrlEncode(value, "utf-8");
    }

    /// <summary> URL编码 </summary>
    public static String UrlEncode(String value, String encoding)
    {
        value = "".equals(value) ? "" : value;
        try{
            return URLEncoder.encode(value, encoding);
        }
        catch (Exception ex){
            return value;
        }
    }

    /// <summary> URL解码 </summary>
    public static String UrlDecode(String value)
    {
        return UrlDecode(value, "utf-8");
    }

    /// <summary> URL解码 </summary>
    public static String UrlDecode(String value, String encoding)
    {
        value = "".equals(value) ? "" : value;
        try{
            return URLDecoder.decode(value, encoding);
        }
        catch (Exception ex){
            return value;
        }
    }
}
