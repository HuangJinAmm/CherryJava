package com.cherryjava.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.cherryjava.*")
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
//        System.setProperty("java.version","1.8.0_221");
//        String s = System.getProperty("java.version");
//        System.out.println(s);
        SpringApplication.run(App.class, args);
        String successMsg =
                "｀　　　　启　　　　　　　　　　　　　　动　　　　　　　　　　　成　成　　　　　　　　　　　功\n" +
                "　启启启启启启启启启启　　　动动动动　　动　　　　　　　　　　　成　　成　　　功功功功功　　功\n" +
                "　启　　　　　　　　启　　　　　　　　动动动动动　　　成成成成成成成成成成　　　　功　　功功功功功功\n" +
                "　启　　　　　　　　启　　　　　　　　　动　　动　　　成　　　　成　　　　　　　　功　　　　功　　功\n" +
                "　启启启启启启启启启启　　动动动动动动　动　　动　　　成　　　　成　　　　　　　　功　　　　功　　功\n" +
                "　启　　　　　　　　　　　　　动　　　　动　　动　　　成　　　　　成　成　　　　　功　　　　功　　功\n" +
                "　启　启启启启启启启启　　　　动　　　　动　　动　　　成成成成　　成　成　　　　　功　　　　功　　功\n" +
                "　启　启　　　　　　启　　　　动　动　　动　　动　　　成　　成　　成　成　　　　　功　　　　功　　功\n" +
                "　启　启　　　　　　启　　　动　　动　　动　　动　　　成　　成　　　成　成　　　　功功功　功　　　功\n" +
                "启　　启　　　　　　启　　　动　　动　动　　　动　　成　　　成　　　成　成　　功功功　　　功　　　功\n" +
                "启　　启启启启启启启启　　动动动动　动动　　动动　　成　　成成　　成　成　　　　　　　　功　　　功功";

        System.out.println(successMsg);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
}