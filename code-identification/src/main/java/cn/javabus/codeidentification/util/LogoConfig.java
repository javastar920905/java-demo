package cn.javabus.codeidentification.util;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ouzhx on 2018/12/10.
 */
@Log4j2
public class LogoConfig {
    /**
     * 设置 logo
     *
     * @param matrixImage 源二维码图片
     * @return 返回带有logo的二维码图片
     * @throws IOException
     * @author Administrator sangwenhao
     */
    public BufferedImage LogoMatrix(BufferedImage matrixImage) throws IOException {
        /**
         * 读取二维码图片，并构建绘图对象
         */
        Graphics2D g2 = matrixImage.createGraphics();

        int matrixWidth = matrixImage.getWidth();
        int matrixHeigh = matrixImage.getHeight();

        /**
         * 读取Logo图片
         */
        BufferedImage logo = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("logo.png");
            //使用input stream 解决spring boot找不到jar包中的文件问题(spring boot jar中的文件不能使用classPathResource.getFile()方式获取,)
            @Cleanup InputStream inputStream = classPathResource.getInputStream();
            logo = ImageIO.read(inputStream);
        } catch (IOException e) {
            log.error("找不到logo 图片!");
        }

        //开始绘制图片x,y,witdth,height
        int x = matrixWidth / 5 * 2;
        int y = matrixHeigh / 5 * 2;
        int logo_width = matrixWidth / 5;
        int logo_height = matrixWidth / 5;
        //绘制
        g2.drawImage(logo, x, y, logo_width, logo_height, null);
        BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        // 设置笔画对象
        g2.setStroke(stroke);

        //指定弧度的圆角矩形( 米白色边框)
       /* RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 3, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, 20, 20);
        g2.setColor(Color.white);
        // 绘制圆弧矩形
        g2.draw(round);*/


        //设置logo 有一道灰色边框(可自定义)
       /* BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke2);
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(x + 2, y+ 2, logo_width - 4, logo_height- 4, 20, 20);
        g2.setColor(new Color(128,128,128));
        g2.draw(round2);*/

        g2.dispose();
        matrixImage.flush();
        return matrixImage;
    }
}
