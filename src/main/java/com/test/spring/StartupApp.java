package com.test.spring;

import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.time.Instant;

public class StartupApp {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.addWebapp("", new File("./src/main/").getAbsolutePath());
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }
}
