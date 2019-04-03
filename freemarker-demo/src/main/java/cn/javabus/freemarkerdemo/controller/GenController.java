package cn.javabus.freemarkerdemo.controller;

import cn.javabus.freemarkerdemo.FreemarkerDemoApplication;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器controller (根据模板生成代码)
 *
 * @author ouzhx on 2019/4/2.
 */
@RestController
public class GenController {
    /**
     * freemarker.template.Configuration
     * spring boot auto configure 的对象
     **/
    @Autowired
    private Configuration cfg;

    @GetMapping("/")
    public String index() {

        Map<String, Object> root = new HashMap<>();
        root.put("user", "Big Joe");

        Map<String, String> latest = new HashMap<>();
        latest.put("url", "products/spring_boot_freemarker.html");
        latest.put("name", "spring_boot_freemarker");
        root.put("latestProduct", latest);

        String tempaltePath = this.getClass().getResource("/templates").getPath();

        try {
            Template temp = cfg.getTemplate("html-template.ftl");//获取模板(/where/you/store/templates/test.ftl)

            System.out.println(latest.get("name"));
            File distFile = new File(tempaltePath + "/" + latest.get("name") + ".html");
            Writer out = new OutputStreamWriter(new FileOutputStream(distFile));
            temp.process(root, out);//合并模板和数据
            out.flush();
            out.close();
            System.out.println("程序已经生成文件:" + distFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        System.out.println("程序退出");
        return "ok";
    }

    @GetMapping("/doc")
    public String doc() {

        Map<String, Object> root = new HashMap<>();
        root.put("user", "Big Joe");

        Map<String, String> latest = new HashMap<>();
        latest.put("url", "products/spring_boot_freemarker.html");
        latest.put("name", "spring_boot_freemarker");
        root.put("latestProduct", latest);

        String tempaltePath = this.getClass().getResource("/templates").getPath();

        try {
            Template temp = cfg.getTemplate("resume.xml");//获取模板(/where/you/store/templates/test.ftl)

            System.out.println(latest.get("name"));
            File distFile = new File(tempaltePath + "/resume.xml");
            Writer out = new OutputStreamWriter(new FileOutputStream(distFile));
            temp.process(root, out);//合并模板和数据
            out.flush();
            out.close();
            System.out.println("程序已经生成文件:" + distFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        System.out.println("程序退出");
        return "ok";
    }

}
