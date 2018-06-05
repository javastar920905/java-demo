package com.javastar920905.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author ouzhx on 2018/6/5.
 */
public class GzipUtils {
    public static final int BUFFER = 1024;
    public static final String EXT = ".gz";

    /**
     * 数据压缩
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 压缩
        compress(bais, baos);

        byte[] output = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return output;
    }

    /**
     * 文件压缩
     *
     * @param file
     * @throws Exception
     */
    public static void compress(File file) throws Exception {
        compress(file, true);
    }

    /**
     * 文件压缩
     *
     * @param file
     * @param delete
     *            是否删除原始文件
     * @throws Exception
     */
    public static void compress(File file, boolean delete) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);

        compress(fis, fos);

        fis.close();
        fos.flush();
        fos.close();

        if (delete) {
            file.delete();
        }
    }

    /**
     * 数据压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    public static void compress(InputStream is, OutputStream os)
        throws Exception {

        GZIPOutputStream gos = new GZIPOutputStream(os);

        int count;
        byte data[] = new byte[BUFFER];
        while ((count = is.read(data, 0, BUFFER)) != -1) {
            gos.write(data, 0, count);
        }

        gos.finish();

        gos.flush();
        gos.close();
    }

    /**
     * 文件压缩
     *
     * @param path
     * @throws Exception
     */
    public static void compress(String path) throws Exception {
        compress(path, true);
    }

    /**
     * 文件压缩
     *
     * @param path
     * @param delete
     *            是否删除原始文件
     * @throws Exception
     */
    public static void compress(String path, boolean delete) throws Exception {
        File file = new File(path);
        compress(file, delete);
    }

    /**
     * 文件压缩
     *
     * @param inputFileName
     * @param outputFileName
     *            是否删除原始文件
     * @throws Exception
     */
    public static void compress(String inputFileName, String outputFileName)
        throws Exception {
        FileInputStream inputFile = new FileInputStream(inputFileName);
        FileOutputStream outputFile = new FileOutputStream(outputFileName);
        compress(inputFile, outputFile);
        inputFile.close();
        outputFile.flush();
        outputFile.close();
    }

    /**
     * 数据解压缩
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] decompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 解压缩

        decompress(bais, baos);

        data = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return data;
    }

    /**
     * 文件解压缩
     *
     * @param file
     * @throws Exception
     */
    public static void decompress(File file) throws Exception {
        decompress(file, true, null);
    }

    /**
     * 文件解压缩
     *
     * @param file
     *                需要解压的文件
     * @param delete
     *            是否删除原始文件
     * @param outPath
     *            解压文件的输出路径
     * @throws Exception
     */
    public static void decompress(File file, boolean delete, String outPath)
        throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = null;
        if (outPath == null || outPath=="") {
            fos = new FileOutputStream(file.getPath().replace(EXT, ""));
        } else {
            File files = new File(outPath);
            //判断文件是否存在，不存在，则创建
            //FileUtil.mkDir(files);//此处调用了递归创建文件夹，没有写出，网上很多
            //文件输出流参数中，需要指定文件解压后的文件名，这里，用文件的原名称
            fos = new FileOutputStream(outPath + File.separator
                + file.getName().replace(EXT, ""));
        }

        decompress(fis, fos);
        fis.close();
        fos.flush();
        fos.close();

        if (delete) {
            file.delete();
        }
    }

    /**
     * 文件解压缩
     *
     * @param inputFileName
     * @param outputFileName
     *            是否删除原始文件
     * @throws Exception
     */
    public static void decompress(String inputFileName, String outputFileName)
        throws Exception {
        FileInputStream inputFile = new FileInputStream(inputFileName);
        FileOutputStream outputFile = new FileOutputStream(outputFileName);
        decompress(inputFile, outputFile);
        inputFile.close();
        outputFile.flush();
        outputFile.close();
    }

    /**
     * 数据解压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    public static void decompress(InputStream is, OutputStream os)
        throws Exception {
        GZIPInputStream gis = new GZIPInputStream(is);
        //GZIPInputStream gis = new GZIPInputStream(new BufferedInputStream(is));
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }

        gis.close();
    }

    /**
     * 文件解压缩
     *
     * @param path
     * @throws Exception
     */
    public static void decompress(String path) throws Exception {
        decompress(path, true, null);
    }

    /**
     * 文件解压缩(解压单个文件)
     *
     * @param path
     *            需要解压的文件路径（包含文件名称）
     * @param delete
     *            是否删除原始文件（true：删除；false：保留）
     * @param outPath
     *            解压后文件的输出路径，如果该参数的值为 null，则输出解压文件到当前文件夹
     * @throws Exception
     */
    public static void decompress(String path, boolean delete, String outPath)
        throws Exception {
        File file = new File(path);
        decompress(file, delete, outPath);
    }

    //测试，通过遍历，解压一个文件夹中的所有文件
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();// 记录开始时间
        String path = "E:\\compreFile\\20160831\\pT\\20160831";
        File file = new File(path);
        String files[] = file.list();
        System.out.println("****共 " + files.length + " 个文件****");
        Thread.sleep(2000);
        int num = 0;
        List list=new ArrayList();
        for (int x = 0; x < files.length; x++) {
            try {
                //调用解压方法
                decompress(path + "\\" + files[x], false,"F:\\compreFile\\test2");
            } catch (Exception e) {
                list.add(files[x]);
                continue;
            }
            ++num;
            System.out.println("第   " + num + "  个文件解压成功！！");
        }

        /*for(int x=0;x<list.size();x++){
            System.out.println(list.get(x).toString());
        }*/
        System.out.println("问题文件 " + list.size() + " 个 ");


        //decompress("E:\\compreFile\\20160730\\page_zip\\188999100.zip","E:\\compreFile\\a");

        long endTime = System.currentTimeMillis();// 记录结束时间
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("执行时间：" + excTime + "s");
        System.out.println("*****完成！！*****");
    }
}
