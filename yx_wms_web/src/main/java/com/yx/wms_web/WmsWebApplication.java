package com.yx.wms_web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring Boot 应用的标识
@SpringBootApplication(scanBasePackages = {"com.yx.wms_service","com.yx.wms_web","com.yx.cache"})
// mapper 接口类扫描包配置
@MapperScan("com.yx.wms_dao")
//@EnableCaching//缓存支持
public class WmsWebApplication {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(WmsWebApplication.class, args);
    }

    /*
     * Created: LiuWenLong
     * Date: 2019-3-19 14:27:04
     * Desc: 异常页面指向  目录: \resources\static
     */
}
