package cn.javabus.codeidentification.util;

import cn.hutool.core.codec.Base64;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.log4j.Log4j2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ouzhx on 2018/8/8.
 */
@Log4j2
public class QRCodeUtil {
    private static final int width = 300;// 默认二维码宽度
    private static final int height = 300;// 默认二维码高度
    private static final String format = "png";// 默认二维码文件格式
    private static final Map<EncodeHintType, Object> hints = new HashMap();// 二维码参数

    static {
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 字符编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);// 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        hints.put(EncodeHintType.MARGIN, 2);// 二维码与图片边距
    }

    /**
     * 返回一个 BufferedImage 对象
     *
     * @param content 二维码内容
     * @param width   宽
     * @param height  高
     */
    public static BufferedImage toBufferedImage(String content, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * 将二维码图片输出到一个流中
     *
     * @param content 二维码内容
     * @param stream  输出流
     * @param width   宽
     * @param height  高
     */
    public static void writeToStream(String content, OutputStream stream, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
    }

    /**
     * 生成二维码图片文件
     *
     * @param content 二维码内容
     * @param path    文件保存路径
     * @param width   宽
     * @param height  高
     */
    public static void createQRCode(String content, String path, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToFile(bitMatrix, format, new File(path));
    }

    /**
     * 生成二维码文件
     *
     * @param content
     * @param path
     * @throws WriterException
     * @throws IOException
     */
    public static void createQRCode(String content, String path) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToFile(bitMatrix, format, new File(path));
    }

    /**
     * 生成待logo 的二维码
     *
     * @param content             要生成的二维码内容
     * @param dist_localtion_path 生成二维码,存放路径 ("e:" + File.separator + "new-1.gif")
     * @throws WriterException
     * @throws IOException
     */
    public static void createQRCodeWithLogo(String content, String dist_localtion_path) throws WriterException, IOException {
        // 二维码图片宽度 300
        int width = 430;
        int height = 430;

        // 二维码的图片格式 gif
        String format = "jpg";


        LogoConfig logoConfig = new LogoConfig();
        BufferedImage logoImage = logoConfig.LogoMatrix(toBufferedImage(content, width, height));

        // 生成二维码 //指定输出路径
        File outputFile = new File(dist_localtion_path);
        if (outputFile.exists()) {
            outputFile.delete();
        }

        if (!ImageIO.write(logoImage, format, outputFile)) {
            throw new IOException("Could not write an image of format " + format + " to " + outputFile);
        }
    }

    /**
     * 生成待logo 的二维码 (返回base64的图片信息)
     *
     * @param content 要生成的二维码内容
     * @throws WriterException
     * @throws IOException
     */
    public static String createQRCodeWithLogo(String content) throws WriterException, IOException {
        // 二维码图片宽度 300
        int width = 430;
        int height = 430;

        LogoConfig logoConfig = new LogoConfig();
        BufferedImage logoImage = logoConfig.LogoMatrix(toBufferedImage(content, width, height));


        try (ByteArrayOutputStream bs = new ByteArrayOutputStream()) {
            //将绘制得图片输出到流
            ImageIO.write(logoImage, "png", bs);
            String imgsrc = Base64.encode(bs.toByteArray());
            return imgsrc;
        } catch (Exception e) {
           log.error("生成base64图片失败");
        }
        return null;
    }
}
