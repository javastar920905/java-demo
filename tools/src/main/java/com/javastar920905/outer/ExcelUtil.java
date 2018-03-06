package com.javastar920905.outer;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import static net.logstash.logback.marker.Markers.append;
import static net.logstash.logback.marker.Markers.appendArray;

/**
 * poi处理Excel工具类 Created by ouzhx
 */
public class ExcelUtil {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    /**
     * 列默认宽度
     */
    private static final int DEFAUL_COLUMN_WIDTH = 4000;

    /**
     * 日志记录请求参数时 的key
     */
    private static final String PARAMS = "params";
    /**
     * 日志记录异常类型 的key
     */
    private static final String ERRORTYPE = "errorType";

    private ExcelUtil() {

    }

    public static HSSFWorkbook getHSSFWorkbook() {
        return new HSSFWorkbook();
    }

    /**
     * 根据输入流构建2003工作簿
     *
     * @param in
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(InputStream in) {
        try {
            return new HSSFWorkbook(in);
        } catch (IOException e) {
            LOGGER
                .error(appendArray(PARAMS, "").and(append(ERRORTYPE, e.toString())), "创建工作簿失败", e);
        }
        return null;
    }

    public static XSSFWorkbook getXSSFWorkbook() {
        return new XSSFWorkbook();
    }

    /**
     * 根据输入流构建2007工作簿
     *
     * @param in
     * @return
     */
    public static XSSFWorkbook getXSSFWorkbook(InputStream in) {
        try {
            return new XSSFWorkbook(in);
        } catch (Exception e) {
            LOGGER
                .error(appendArray(PARAMS, "").and(append(ERRORTYPE, e.toString())), "创建工作簿失败", e);
        }
        return null;
    }

    public static HSSFSheet createHSSFSheet(HSSFWorkbook workbook, String sheetName) {
        return workbook.createSheet(sheetName);
    }

    public static XSSFSheet createXSSFSheet(XSSFWorkbook workbook, String sheetName) {
        return workbook.createSheet(sheetName);
    }

    /**
     * 写入表头信息(excel 2003)
     *
     * @param hssfWorkbook
     * @param hssfSheet
     * @param headers      所有表头列的集合
     * @param title        表格的标题
     */
    public static void writeHeader(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, String[] headers,
        String title) {
        LOGGER.info("【写入表头信息】");
        // 头信息处理
        String[] newHeaders = headersHandler(headers);

        // 初始化标题和表头单元格样式
        HSSFCellStyle titleCellStyle = createTitleCellStyle(hssfWorkbook);
        // 标题栏
        HSSFRow titleRow = hssfSheet.createRow(0);
        titleRow.setHeight((short) 500);
        HSSFCell titleCell = titleRow.createCell(0);
        // 设置标题文本
        titleCell.setCellValue(new HSSFRichTextString(title));
        // 设置单元格样式
        titleCell.setCellStyle(titleCellStyle);
        // 处理单元格合并，四个参数分别是：起始行，终止行，起始行，终止列
        hssfSheet.addMergedRegion(
            new CellRangeAddress(0, 0, (short) 0, (short) (newHeaders.length - 1)));
        // 设置合并后的单元格的样式
        titleRow.createCell(newHeaders.length - 1).setCellStyle(titleCellStyle);

        // 表头
        HSSFRow headRow = hssfSheet.createRow(1);
        headRow.setHeight((short) 500);
        HSSFCell headCell;
        String headInfo;
        // 处理excel表头
        for (int i = 0, len = newHeaders.length; i < len; i++) {
            headInfo = newHeaders[i].trim();
            headCell = headRow.createCell(i);
            headCell.setCellValue(headInfo);
            headCell.setCellStyle(titleCellStyle);
            // 设置列宽度
            hssfSheet.setColumnWidth(i, DEFAUL_COLUMN_WIDTH);
        }
    }

    /**
     * 写入表头信息(excel 2007)
     *
     * @param xssfWorkbook
     * @param xssfSheet
     * @param headers      所有表头列的集合
     * @param title        表格的标题
     */
    public static void writeHeader(XSSFWorkbook xssfWorkbook, XSSFSheet xssfSheet, String[] headers,
        String title) {
        LOGGER.info("【写入表头信息】");
        // 头信息处理
        String[] newHeaders = headersHandler(headers);

        // 初始化标题和表头单元格样式
        XSSFCellStyle titleCellStyle = createTitleCellStyle(xssfWorkbook);
        // 标题栏
        XSSFRow titleRow = xssfSheet.createRow(0);
        titleRow.setHeight((short) 500);
        XSSFCell titleCell = titleRow.createCell(0);
        // 设置标题文本
        titleCell.setCellValue(new XSSFRichTextString(title));
        // 设置单元格样式
        titleCell.setCellStyle(titleCellStyle);
        // 处理单元格合并，四个参数分别是：起始行，终止行，起始行，终止列
        xssfSheet.addMergedRegion(
            new CellRangeAddress(0, 0, (short) 0, (short) (newHeaders.length - 1)));
        // 设置合并后的单元格的样式
        titleRow.createCell(newHeaders.length - 1).setCellStyle(titleCellStyle);

        // 表头
        XSSFRow headRow = xssfSheet.createRow(1);
        headRow.setHeight((short) 500);
        XSSFCell headCell;
        String headInfo;
        // 处理excel表头
        for (int i = 0, len = newHeaders.length; i < len; i++) {
            headInfo = newHeaders[i].trim();
            headCell = headRow.createCell(i);
            headCell.setCellValue(headInfo);
            headCell.setCellStyle(titleCellStyle);
            // 设置列宽度
            xssfSheet.setColumnWidth(i, DEFAUL_COLUMN_WIDTH);
        }
    }

    /**
     * 头信息校验和处理
     *
     * @param headers
     */
    public static String[] headersHandler(String[] headers) {
        List<String> newHeaders = new ArrayList<>();
        for (String string : headers) {
            if (StringUtil.isNotBlank(string)) {
                newHeaders.add(string);
            }
        }
        int size = newHeaders.size();

        return newHeaders.toArray(new String[size]);
    }

    /**
     * 写入excel内容(excel 2003)
     *
     * @param hssfWorkbook
     * @param hssfSheet
     * @param fieldNames   对应标题的各个字段，可解析list，map和string[]三种类型，list和string[]默认按照顺序一一对应标题，map可指定顺序，key为字段顺序，value为字段值
     * @param dataList     具体的数据值，可存放任意类型
     * @throws Exception
     */
    public static <T extends Object> void writeContent(HSSFWorkbook hssfWorkbook,
        HSSFSheet hssfSheet, T fieldNames, List<?> dataList) {
        List<String> fieldList;
        if (fieldNames instanceof List) {
            fieldList = (List<String>) fieldNames;
        } else if (fieldNames instanceof Map) {
            TreeMap<String, String> treeMap = new TreeMap<>((obj1, obj2) -> {
                try {
                    int o1 = Integer.parseInt(obj1);
                    int o2 = Integer.parseInt(obj2);
                    return o1 >= o2 ? 1 : -1;
                } catch (Exception e) {
                    LOGGER.error(appendArray(PARAMS, "").and(append(ERRORTYPE, e.toString())),
                        "非数字直接调用原始字符串比较大小", e);
                    return obj2.compareTo(obj1);
                }
            });
            treeMap.putAll((Map<String, String>) fieldNames);
            fieldList = new ArrayList<>(treeMap.values());
        } else if (fieldNames instanceof String[]) {
            fieldList = new ArrayList<>(Arrays.asList((String[]) fieldNames));
        } else {
            LOGGER.warn("fieldNames 非指定的list,map或者String数组三者之一的类型");
            return;
        }

        LOGGER.info("【写入Excel内容部分】");
        // 2015-8-13 增加，当没有数据的时候，把原来抛异常的方式修改成返回一个只有头信息，没有数据的空Excel
        if (CollectionUtil.isEmpty(dataList)) {
            LOGGER.warn("【没有内容数据】");
            return;
        }
        HSSFRow row;
        HSSFCell cell;
        // 单元格的值
        Object cellValue;
        // 数据写入行索引
        int rownum = 2;
        // 单元格样式
        HSSFCellStyle cellStyle = createContentCellStyle(hssfWorkbook);
        // 遍历集合，处理数据
        for (int j = 0, size = dataList.size(); j < size; j++) {
            row = hssfSheet.createRow(rownum);
            for (int i = 0, len = fieldList.size(); i < len; i++) {
                cell = row.createCell(i);
                cellValue = getFieldValue(dataList.get(j), fieldList.get(i));
                cellValueHandler(cell, cellValue);
                cell.setCellStyle(cellStyle);
            }
            rownum++;
        }
    }

    /**
     * 写入excel内容(excel 2007)
     *
     * @param xssfWorkbook
     * @param xssfSheet
     * @param fieldNames   对应标题的各个字段，可解析list，map和string[]三种类型，list和string[]默认按照顺序一一对应标题，map可指定顺序，key为字段顺序，value为字段值
     * @param dataList     具体的数据值，可存放任意类型
     * @throws Exception
     */
    public static <T extends Object> void writeContent(XSSFWorkbook xssfWorkbook,
        XSSFSheet xssfSheet, T fieldNames, List<?> dataList) {
        List<String> fieldList;
        if (fieldNames instanceof List) {
            fieldList = (List<String>) fieldNames;
        } else if (fieldNames instanceof Map) {
            TreeMap<String, String> treeMap = new TreeMap<>((obj1, obj2) -> {
                try {
                    int o1 = Integer.parseInt(obj1);
                    int o2 = Integer.parseInt(obj2);
                    return o1 >= o2 ? 1 : -1;
                } catch (Exception e) {
                    LOGGER.error(appendArray(PARAMS, "").and(append(ERRORTYPE, e.toString())),
                        "非数字直接调用原始字符串比较大小", e);
                    return obj2.compareTo(obj1);
                }
            });
            treeMap.putAll((Map<String, String>) fieldNames);
            fieldList = new ArrayList<>(treeMap.values());
        } else if (fieldNames instanceof String[]) {
            fieldList = new ArrayList<>(Arrays.asList((String[]) fieldNames));
        } else {
            LOGGER.warn("fieldNames 非指定的list,map或者String数组三者之一的类型");
            return;
        }

        LOGGER.info("【写入Excel内容部分】");
        // 2015-8-13 增加，当没有数据的时候，把原来抛异常的方式修改成返回一个只有头信息，没有数据的空Excel
        if (CollectionUtil.isEmpty(dataList)) {
            LOGGER.warn("【没有内容数据】");
            return;
        }
        XSSFRow row;
        XSSFCell cell;
        // 单元格的值
        Object cellValue;
        // 数据写入行索引
        int rownum = 2;
        // 单元格样式
        XSSFCellStyle cellStyle = createContentCellStyle(xssfWorkbook);
        // 遍历集合，处理数据
        for (int j = 0, size = dataList.size(); j < size; j++) {
            row = xssfSheet.createRow(rownum);
            for (int i = 0, len = fieldList.size(); i < len; i++) {
                cell = row.createCell(i);
                cellValue = getFieldValue(dataList.get(j), fieldList.get(i));
                cellValueHandler(cell, cellValue);
                cell.setCellStyle(cellStyle);
            }
            rownum++;
        }
    }

    /**
     * 单元格写值处理器
     *
     * @param cell
     * @param cellValue 单元格值
     */
    public static void cellValueHandler(Cell cell, Object cellValue) {
        if (cellValue == null) {
            cell.setCellValue("");
            return;
        }
        if (cellValue instanceof String) {
            cell.setCellValue((String) cellValue);
        } else if (cellValue instanceof Boolean) {
            cell.setCellValue((Boolean) cellValue);
        } else if (cellValue instanceof Calendar) {
            cell.setCellValue((Calendar) cellValue);
        } else if (cellValue instanceof Double) {
            cell.setCellValue((Double) cellValue);
        } else if (cellValue instanceof Integer || cellValue instanceof Long
            || cellValue instanceof Short || cellValue instanceof Float) {
            cell.setCellValue(Double.parseDouble(cellValue.toString()));
        } else if (cellValue instanceof HSSFRichTextString) {
            cell.setCellValue((HSSFRichTextString) cellValue);
        }
        cell.setCellValue(cellValue.toString());
    }

    /**
     * 创建标题和表头单元格样式(excel 2003)
     *
     * @param
     * @return
     */
    public static HSSFCellStyle createTitleCellStyle(HSSFWorkbook hssfWorkbook) {
        LOGGER.info("【创建标题和表头单元格样式】");
        // 单元格的样式
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        // 设置字体样式，改为不变粗
        HSSFFont font = hssfWorkbook.createFont();
        font.setFontHeightInPoints((short) 13);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        cellStyle.setFont(font);
        // 单元格垂直居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        // 设置通用的单元格属性
        setCommonCellStyle(cellStyle);
        return cellStyle;
    }

    /**
     * 创建标题和表头单元格样式(excel 2007)
     *
     * @param
     * @return
     */
    public static XSSFCellStyle createTitleCellStyle(XSSFWorkbook xssfWorkbook) {
        LOGGER.info("【创建标题和表头单元格样式】");
        // 单元格的样式
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
        // 设置字体样式
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontHeightInPoints((short) 13);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        cellStyle.setFont(font);
        // 单元格垂直居中
        cellStyle.setVerticalAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置通用的单元格属性
        setCommonCellStyle(cellStyle);
        return cellStyle;
    }

    /**
     * 创建内容单元格样式(excel 2003)
     *
     * @param hssfWorkbook
     * @return
     */
    public static HSSFCellStyle createContentCellStyle(HSSFWorkbook hssfWorkbook) {
        LOGGER.info("【创建内容单元格样式】");
        // 单元格的样式
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        // 设置字体样式，改为不变粗
        HSSFFont font = hssfWorkbook.createFont();
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        // 设置单元格自动换行
        cellStyle.setWrapText(true);
        // 单元格垂直居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        // 设置通用的单元格属性
        setCommonCellStyle(cellStyle);
        return cellStyle;
    }

    /**
     * 创建内容单元格样式(excel 2007)
     *
     * @param xssfWorkbook
     * @return
     */
    public static XSSFCellStyle createContentCellStyle(XSSFWorkbook xssfWorkbook) {
        LOGGER.info("【创建内容单元格样式】");
        // 单元格的样式
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
        // 设置字体样式，改为不变粗
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        // 设置单元格自动换行
        cellStyle.setWrapText(true);
        // 单元格垂直居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置通用的单元格属性
        setCommonCellStyle(cellStyle);
        return cellStyle;
    }

    /**
     * 设置通用的单元格属性
     *
     * @param cellStyle 要设置属性的单元格
     */
    public static void setCommonCellStyle(CellStyle cellStyle) {
        // 居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        // 设置边框
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
    }


    /**
     * 将生成的Excel输出到指定目录
     *
     * @param workbook
     * @param filePath 文件输出目录，包括文件名
     */
    public static void writeToFilePath(Workbook workbook, String filePath) {
        LOGGER.info("【将生成的Excel输出到指定目录】filePath ：" + filePath);
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            LOGGER.error(appendArray(PARAMS, filePath).and(append(ERRORTYPE, e.toString())),
                "【将生成的Excel输出到指定目录失败】", e);
        }
    }

    /**
     * 获取excel,用于导出(excel 2003)
     *
     * @param sheetName  sheet名称
     * @param title      sheet标题
     * @param headers    sheet头部
     * @param fieldNames 对应标题的各个字段，可解析list，map和string[]三种类型，list和string[]默认按照顺序一一对应标题，map可指定顺序，key为字段顺序，value为字段值
     * @param dataList   真正的数据值
     * @return
     * @throws Exception
     */
    public static <T> HSSFWorkbook createHSSFWorkbookToExport(String sheetName, String title,
        String[] headers, T fieldNames, List<?> dataList) {
        try {
            if (ArrayUtils.isEmpty(headers)) {
                LOGGER.warn("【表头为空】");
                return null;
            }
            // 1.创建 Workbook
            HSSFWorkbook hssfWorkbook = getHSSFWorkbook();
            // 2.创建 Sheet
            HSSFSheet hssfSheet = createHSSFSheet(hssfWorkbook, sheetName);
            // 3.写入 head
            writeHeader(hssfWorkbook, hssfSheet, headers, title);
            // 4.写入内容
            writeContent(hssfWorkbook, hssfSheet, fieldNames, dataList);
            return hssfWorkbook;
        } catch (Exception e) {
            StringBuilder sbBuilder = new StringBuilder();
            sbBuilder.append(sheetName).append("-").append(title).append("-")
                .append(Arrays.toString(headers)).append("-").append(fieldNames.toString())
                .append("-").append(dataList);
            LOGGER.error(
                appendArray(PARAMS, sbBuilder.toString()).and(append(ERRORTYPE, e.toString())),
                "【Excel创建失败】", e);
            return null;
        }
    }

    /**
     * 获取excel,用于导出(excel 2007)
     *
     * @param sheetName  sheet名称
     * @param title      sheet标题
     * @param headers    sheet头部
     * @param fieldNames 对应标题的各个字段，可解析list，map和string[]三种类型，list和string[]默认按照顺序一一对应标题，map可指定顺序，key为字段顺序，value为字段值
     * @param dataList   真正的数据值
     * @return
     * @throws Exception
     */
    public static <T> XSSFWorkbook createXSSFWorkbookToExport(String sheetName, String title,
        String[] headers, T fieldNames, List<?> dataList) {

        try {
            if (ArrayUtils.isEmpty(headers)) {
                LOGGER.warn("【表头为空】");
                return null;
            }
            // 1.创建 Workbook
            XSSFWorkbook xssfWorkbook = getXSSFWorkbook();
            // 2.创建 Sheet
            XSSFSheet xssfSheet = createXSSFSheet(xssfWorkbook, sheetName);
            // 3.写入 head
            writeHeader(xssfWorkbook, xssfSheet, headers, title);
            // 4.写入内容
            writeContent(xssfWorkbook, xssfSheet, fieldNames, dataList);
            return xssfWorkbook;
        } catch (Exception e) {
            StringBuilder sbBuilder = new StringBuilder();
            sbBuilder.append(sheetName).append("-").append(title).append("-")
                .append(Arrays.toString(headers)).append("-").append(fieldNames.toString())
                .append("-").append(dataList);
            LOGGER.error(
                appendArray(PARAMS, sbBuilder.toString()).and(append(ERRORTYPE, e.toString())),
                "【Excel创建失败】", e);
            return null;
        }
    }

    /**
     * 利用反射获取对象的指定字段的值
     *
     * @param object    待处理对象
     * @param fieldName 待处理对象的字段名称
     * @return
     * @throws Exception
     */
    public static Object getFieldValue(Object object, String fieldName) {
        if (object == null) {
            return null;
        }
        if (StringUtil.isBlank(fieldName)) {
            return null;
        }
        if (object instanceof Map) {
            Map map = (Map) object;
            return map.get(fieldName);
        }
        Field field;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(object);
            } catch (Exception e) {
                LOGGER.error(appendArray(PARAMS, clazz).and(append(ERRORTYPE, e.toString())),
                    "利用反射获取对象的指定字段的值", e);
            }
        }
        return null;
    }

    /**
     * 给指定的excel列设置为下拉列表
     *
     * @param valueList 下拉列表值的集合
     * @param workbook
     * @param column    指定的列
     * @param num       当前工作簿中的下拉列表的序号(可能有多列都是下拉列表列，在此处用序号区分,从1开始取值,一个下拉列表直接传1,多个则累加)
     * @return
     */
    public static HSSFDataValidation createExcelSelectColumn(List<String> valueList,
        HSSFWorkbook workbook, int column, int num) {
        try {
            HSSFSheet hidden = workbook.createSheet("hidden" + num);
            CellRangeAddressList regions = new CellRangeAddressList(2, 65535, column, column);
            HSSFRow row = hidden.createRow(0);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue("");
            for (int i = 0; i < valueList.size(); i++) {
                row = hidden.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(valueList.get(i));
            }
            HSSFName namedCell = workbook.createName();
            namedCell.setNameName("hiddenSheet" + num);
            namedCell.setRefersToFormula("hidden" + num + "!$A$1:$A$" + valueList.size());
            DVConstraint constraint = DVConstraint.createFormulaListConstraint("hiddenSheet" + num);
            HSSFDataValidation validation = new HSSFDataValidation(regions, constraint);
            workbook.setSheetHidden(num, false);
            return validation;
        } catch (Exception e) {
            LOGGER.error(appendArray(PARAMS, valueList.toString() + "-" + column + "-" + num)
                .and(append(ERRORTYPE, e.toString())), "给指定的excel列设置为下拉列表", e);
            return null;
        }
    }

    /**
     * 添加一行
     *
     * @param hssfWorkbook
     * @param sheet
     * @param rowIndex
     * @param datas
     */
    public static void excelAddRow(HSSFWorkbook hssfWorkbook, HSSFSheet sheet, int rowIndex,
        Object[] datas) {
        HSSFRow row = sheet.createRow(rowIndex);
        for (int i = 0; i < datas.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFCellStyle cellStyle = createContentCellStyle(hssfWorkbook);
            cellValueHandler(cell, datas[i]);
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 合并单元格
     *
     * @param sheet
     * @param firstRow 开始行数
     * @param lastRow  结束行数
     * @param firstCol 开始列数
     * @param lastCol  结束列数
     */
    public static void mergeCell(Sheet sheet, int firstRow, int lastRow, int firstCol,
        int lastCol) {
        StringBuilder message = new StringBuilder();
        message.append("合并从第").append(firstRow).append("行到第").append(lastRow).append("行,从第")
            .append(firstCol).append("列到第").append(lastCol).append("列的值");
        LOGGER.info(message.toString());
        StringBuilder cellValue = new StringBuilder();
        for (int i = firstRow; i <= lastRow; i++) {
            if (sheet.getRow(i) != null) {
                for (int j = firstCol; j <= lastCol; j++) {
                    cellValue.append(getStringCellValue(sheet.getRow(i).getCell(j)));
                }
            }
        }
        if (StringUtil.isNotBlank(cellValue.toString())) {
            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
            sheet.getRow(firstRow).getCell(firstCol).setCellValue(cellValue.toString());
        }
    }



    /**
     * 按照文件路劲解析excel文件
     *
     * @param startRow  开始解析的行数
     * @param excelPath excel的文件路劲
     * @return
     * @throws Exception
     */
    public static List<String[]> readExcel(int startRow, String excelPath) {
        LOGGER.info("【读取Excel】excelPath : " + excelPath + ",从" + startRow + "行开始读取");
        FileInputStream is = null;
        List<String[]> list = new ArrayList<>();
        try {
            is = new FileInputStream(new File(excelPath));
            HSSFWorkbook wb = new HSSFWorkbook(is);
            HSSFSheet sheet = wb.getSheetAt(0);
            // 得到总共的行数
            int rowNum = sheet.getPhysicalNumberOfRows();
            if (rowNum == 0) {
                LOGGER.warn("无数据");
                return list;
            }
            int colNum = sheet.getRow(startRow).getPhysicalNumberOfCells();
            for (int i = startRow; i < rowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                if (null == row) {
                    continue;
                }
                String[] vals = new String[colNum];
                for (int j = 0; j < colNum; j++) {
                    vals[j] = getStringCellValue(row.getCell(j));
                }
                list.add(vals);
            }
        } catch (Exception e) {
            LOGGER.error(appendArray(PARAMS, startRow + "-" + excelPath)
                .and(append(ERRORTYPE, e.toString())), "【Excel解析失败】", e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                LOGGER.error(appendArray(PARAMS, startRow + "-" + excelPath)
                    .and(append(ERRORTYPE, e.toString())), "流关闭失败", e);
            }
        }
        return list;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    public static String getStringCellValue(Cell cell) {
        NumberFormat format = NumberFormat.getInstance();
        if (cell == null) {
            return "";
        }

        String strCell;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                strCell =
                    String.valueOf(format.format(cell.getNumericCellValue())).replace(",", "");
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                strCell = "";
                break;
        }
        return strCell;
    }

    /**
     * 修改指定行列的单元格的值
     *
     * @param sheet
     * @param row
     * @param col
     */
    public static void updateCellValue(HSSFSheet sheet, int row, int col, Object value) {
        try {
            HSSFRow hssfRow = sheet.getRow(row);
            Assert.notNull(hssfRow);
            HSSFCell cell = hssfRow.getCell(col);
            Assert.notNull(cell);
            cellValueHandler(cell, value);
        } catch (Exception e) {
            LOGGER.error(appendArray(PARAMS, row, col, value).and(append(ERRORTYPE, e.toString())),
                "修改指定单元格的值失败", e);
        }
    }

    /**
     * 对科学计数法出现的数字型数据进行转化
     *
     * @param number
     * @return
     */
    public static String numberFormatTranser(String number) {
        if (StringUtil.isBlank(number)) {
            return null;
        } else if (!number.toUpperCase().contains("E")) {
            // 不包含E绝不是科学计数法，直接返回
            return number;
        } else {
            try {
                DecimalFormat df = new DecimalFormat("0");
                return df.format(new BigDecimal(number));
            } catch (Exception e) {
                // 转化异常的一定是字符串，直接返回
                LOGGER.error(appendArray(PARAMS, number).and(append(ERRORTYPE, e.toString())),
                    "转化异常，直接返回当前字符串本身", e);
                return number;
            }
        }
    }

    /**
     * 以字节数组的形式返回当前sheet页中所有的图片信息
     *
     * @param hssfWorkbook
     * @param hssfSheet
     * @return
     */
    public static List<byte[]> getSheetPictures(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet) {
        List<byte[]> resultList = new ArrayList<>();
        List<HSSFPictureData> pictures = hssfWorkbook.getAllPictures();
        List<HSSFShape> shapeList = hssfSheet.getDrawingPatriarch().getChildren();
        for (HSSFShape shape : shapeList) {
            if (shape instanceof HSSFPicture) {
                HSSFPicture pic = (HSSFPicture) shape;
                int pictureIndex = pic.getPictureIndex() - 1;
                HSSFPictureData picData = pictures.get(pictureIndex);
                resultList.add(picData.getData());
            }
        }
        return resultList;
    }

    /**
     * 复制一个单元格样式到目的单元格样式
     *
     * @param fromStyle
     * @param toStyle
     */
    public static void copyCellStyle(CellStyle fromStyle, CellStyle toStyle) {
        toStyle.setAlignment(fromStyle.getAlignment());
        // 边框和边框颜色
        toStyle.setBorderBottom(fromStyle.getBorderBottom());
        toStyle.setBorderLeft(fromStyle.getBorderLeft());
        toStyle.setBorderRight(fromStyle.getBorderRight());
        toStyle.setBorderTop(fromStyle.getBorderTop());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

        // 背景和前景
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        // 数据格式
        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPattern());
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());// 首行缩进
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());// 旋转
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
        toStyle.setWrapText(fromStyle.getWrapText());
    }
}
