package cn.javabus.freemarkerdemo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class FreemarkerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreemarkerDemoApplication.class, args);

        Map<String, Object> root = new HashMap<>();
        root.put("user", "Big Joe");

        Map<String,String> latest=new HashMap<>();
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "test product");
        root.put("latestProduct", latest);

        String tempaltePath=FreemarkerDemoApplication.class.getClass().getResource("/templates").getPath();

        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_27); //创建实例,指定兼容版本
            cfg.setDirectoryForTemplateLoading(new File(tempaltePath));//指定模板文件目录
            cfg.setDefaultEncoding("UTF-8");
            //设置错误的显示方式。
            //在网页*开发期间* TemplateExceptionHandler.HTML_DEBUG_HANDLER更好。
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);//不要在FreeMarker中记录它会抛出的异常
            cfg.setWrapUncheckedExceptions(true);//将模板处理期间抛出的未经检查的异常包装到TemplateException-s中


            Template temp = cfg.getTemplate("html-template.ftl");//获取模板(/where/you/store/templates/test.ftl)
            System.out.println(latest.get("name"));

            File distFile = new File(tempaltePath + "/" + latest.get("name") + ".html");
            Writer out = new OutputStreamWriter(new FileOutputStream(distFile));
            temp.process(root, out);//合并模板和数据
            out.flush();
            out.close();
            System.out.println("程序已经生成文件:"+distFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        System.out.println("程序退出");
    }

}
