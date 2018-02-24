package com.javastar920905.html2image;

import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.configurations.WrapperConfig;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationHome;

import java.io.File;
import java.util.UUID;

/**
 * 将html字符串生成图片示例--测试通过
 *
 * @author ouzhx on 2018/2/24.
 */
public class Html2image {
    /***软件安装地址  https://wkhtmltopdf.org/index.html***/
    private static final String pdfExeConfigPath =
        "E:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltoimage.exe";

    public static void main(String[] args) {
        try {
            String imagePath = "bg/48.jpg";
            String imageAbsolutePath =
                Html2image.class.getClassLoader().getResource(imagePath).getPath();
            //   返回值为 /D:/gitrepository/java-demo/html2pdf/out/production/resources/bg/48.jpg
            /**windows下一定要用"file://D:/gitrepository/" 路径才能截图成功, file:/后面一定只能跟一个/ 多一个都不行...**/

            if (imageAbsolutePath.startsWith("/")) {
                // linux 获取的路径是以/开头的
                imageAbsolutePath = String.format("file:%s", imageAbsolutePath);
            } else {
                // windows系统是以磁盘名开头的
                imageAbsolutePath = String.format("file:/%s", imageAbsolutePath);
            }
            // 解决File.separator 在windows系统时获取的值为\的问题,应该替换为/才能正常显示图片
            imageAbsolutePath = imageAbsolutePath.replaceAll("\\\\", "/");
            System.out.println("图片链接为:" + imageAbsolutePath);

            // 替换预制模板图片中的内容  (将生成的html copy到test.html用于检测结果)
            String finalHtml = greetingCardShowTemplate.replace("%title%", "这是标题")
                .replace("%bg%", imageAbsolutePath).replace("%name%", "电脑发音人")
                .replace("%greetings%", "你好").replace("%ranking%", "恭喜您打败99%的对手");
            Pdf pdf = new Pdf(new WrapperConfig(pdfExeConfigPath));
            pdf.addPageFromString(finalHtml);

            // 调用wkhtmltoimage.exe生成图片到本地
            String newImageName = generateUUID() + ".jpg";
            String localImagePath =
                new ApplicationHome(Html2image.class).getDir().getPath() + File.separator
                    + newImageName;
            //设置生成图片大小
            pdf.addParam(new Param("--crop-w", "500"));
            pdf.addParam(new Param("--crop-h", "400"));
            pdf.addParam(new Param("--crop-x", "262"));
            pdf.addParam(new Param("--crop-y", "0"));
            pdf.saveAs(localImagePath);
            System.out.println("新图片保存地址为" + localImagePath);

            //TODO 上传至cdn
            /* String cosPath = String.format("/walkman/share/%s", newImageName);
               JSONObject uploadResultJson = QcloudUtil.uploadFile("content", cosPath, localImagePath);
                LOGGER.info("上传合成图片到腾讯云返回结果为：{}", uploadResultJson);*/

            //TODO 上传成功后可以清除本地文件
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static String generateUUID() {
        return StringUtils.remove(UUID.randomUUID().toString(), "-");
    }

    /***提前定义好用于生成图片的 html模板**/
    private static final String greetingCardShowTemplate =
        "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "  <head>\n"
            + "    <meta charset=\"UTF-8\">\n"
            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n"
            + "    <title>%title%</title>\n" + "    <style>\n" + "      *{\n"
            + "        padding: 0;\n" + "        margin: 0;\n" + "        box-sizing: border-box;\n"
            + "      }\n" + "      html,\n" + "      body{\n" + "        width: 500px;\n"
            + "        height: 400px;\n" + "        margin: 0 auto;\n" + "      }\n"
            + "      .container{\n" + "        width: 100%;\n" + "        height: 100%;\n"
            + "        overflow: hidden;\n" + "        position: relative;\n" + "      }\n"
            + "      #bg{\n" + "        position: absolute;\n" + "        top: 0;\n"
            + "        left: 0;\n" + "        width: 100%;\n" + "        height: 100%;\n"
            + "        z-index: 1;\n" + "        background: url('%bg%') no-repeat 0 0;\n"
            + "      }\n" + "      .mask{\n" + "        position: absolute;\n" + "        top: 0;\n"
            + "        left: 0;\n" + "        width: 100%;\n" + "        height: 100%;\n"
            + "        z-index: 999;\n" + "        background: rgba(17, 17, 17, .5);\n"
            + "      }\n" + "      .speaker{\n" + "        position: absolute;\n"
            + "        left: 15px;\n" + "        top: 20px;\n" + "        width: 12px;\n"
            + "        height: 18px;\n" + "      }\n" + "      .center{\n"
            + "        position: absolute;\n" + "        left: 50%;\n" + "        top: 50%;\n"
            + "        margin-top: -50px;\n" + "        margin-left: -50px;\n"
            + "        width: 100px;\n" + "        height: 100px;\n" + "      }\n"
            + "      #name{\n" + "        position: absolute;\n" + "        left: 35px;\n"
            + "        top: 20px;\n" + "        width: auto;\n" + "        color: #fff;\n"
            + "        font-size: 20px;\n" + "        line-height: 20px;\n"
            + "        display: block;\n" + "      }\n" + "      #text{\n"
            + "        position: absolute;\n" + "        bottom: 10px;\n" + "        left: 0;\n"
            + "        width: 100%;\n" + "        height: auto;\n" + "        padding: 10px;\n"
            + "        padding-bottom: 0;\n" + "        display: -webkit-box;\n"
            + "        overflow : hidden;\n" + "        text-overflow: ellipsis;\n"
            + "        -webkit-line-clamp: 2;\n" + "        -webkit-box-orient: vertical;\n"
            + "        color: #fff;\n" + "        line-height: 34px;\n"
            + "        font-size: 24px;\n" + "        letter-spacing: 2px;\n" + "      }\n"
            + "      .center-box{\n" + "        position: absolute;\n" + "        top: 105px;\n"
            + "        left: 0;\n" + "        width: 100%;\n" + "        height: 130px;\n"
            + "        text-align: center;\n" + "      }\n" + "      .center-box img{\n"
            + "        width: 70px;\n" + "        height: 55px;\n" + "        margin: 0 auto;\n"
            + "        margin-bottom: 5px;\n" + "      }\n" + "      #show{\n"
            + "        width: 310px;\n" + "        line-height: 36px;\n"
            + "        text-align: center;\n" + "        letter-spacing: 2px;\n"
            + "        font-size: 24px;\n" + "        color: #f9c66e;\n"
            + "        margin: 0 auto;\n" + "      }\n" + "    </style>\n" + "  </head>\n"
            + "<body>\n" + "  <div class=\"container\">\n" + "    <div id=\"bg\"></div>\n"
            + "    <div class=\"mask\">\n"
            + "      <img class=\"speaker\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAASCAYAAAEYrg+aAAAAAXNSR0IArs4c6QAAATVJREFUKBVtkj1Ow0AUhL0WAiFInTJnoKBGyjWAjoO4giNQhBNQotRIFKkCogDBGahApACBYPnmeZ+9OPuk8Zudmf2zXVVUjHESeEQN+kKYy2vlIKMbQQ77ZOu8d4LHalcQgibvSqDfmQFpRGxpEQQ/Qwwh1N18maksXBP8ciX1Gdp2zbRNyD7iI3gDP2gfFsKYgBWYm8DD7/XrnHS7R0p0p/MZpVOZVzTYK+q4W0RmvkTq37rxbS4yHoH23ULOwSt4ALpTXwhNPxowzAUY1iKP/buXkrnp3F+GxsXrebDUde2rklHSyF7mP4D4JxiWfUlE+2E3WGnJYMo5r+E74AQcANUNuDBWVVP60jgTzsATOALjFNCOY3AMnsGp62sdsxHWDAR7rZha9T4LjBJfpf7CkffE/wAe6Oh94Tqm6wAAAABJRU5ErkJggg==\"/>\n"
            + "      <!-- <img class=\"center\" src=\"data:image/png;base64,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\"/> -->\n"
            + "      <div class=\"center-box\">\n"
            + "        <img src=\"data:image/png;base64,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\"/>\n"
            + "        <div id=\"show\">%ranking%</div>\n" + "      </div>\n"
            + "      <span id=\"name\">%name%</span>\n"
            + "      <div id=\"text\">%greetings%</div>\n" + "    </div>\n" + "  </div>\n"
            + "</body>\n" + "</html>";
}
