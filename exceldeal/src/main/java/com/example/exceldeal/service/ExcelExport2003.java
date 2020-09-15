package com.example.exceldeal.service;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 小白i
 * @date 2020/8/17
 */
public class ExcelExport2003 {

    /**
     * @throws
     * @Title: getExcelExport
     * @Description: TODO(excel方式导出)
     * @param:
     * @return: void
     */
    public static void getExcelExport() {


        Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());

        System.err.println("-----------------------------2003 19.5W导数开始时间：------------------------\n" + nowTimestamp);

        //创建HSSFWorkbook对象(excel的文档对象)   POI要操作excel 2007及以上的版本需要使用XSSF来代替上面代码的HSSF。

        HSSFWorkbook workBook = new HSSFWorkbook();

        //建立新的sheet对象（excel的表单）
        //创建Excel工作表（页签）
        HSSFSheet sheet = workBook.createSheet("Excel 2003导出测试");

        int[] width = {5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000};

        for (int i = 0; i < width.length; i++) {
            //设置列宽
            sheet.setColumnWidth(i, width[i]);

        }


        //excel列
        String[] head = {"列1", "列2", "列3", "列4", "列5", "列6", "列7", "列8"};
        //创建标题行
        HSSFRow title = sheet.createRow(0);
        //给标题行单元格赋值
        title.createCell(0).setCellValue("Excel 2003导出测试");

        //合并单元格          构造参数依次为起始行，截至行，起始列， 截至列

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        //创建并初始化标题样式
        getTitleStyle(workBook, title);
        //初始化抬头和样式
        InitExcelHead(workBook, sheet, head);
        //excel内容赋值     不多线程的
        setExcelValue(workBook, sheet, head);


        //***************这里为了测试方便直接新建页签***************

        //建立新的sheet对象（excel的表单）
        //创建Excel工作表（页签）
        HSSFSheet sheet2 = workBook.createSheet("数据导出2");

        int[] width2 = {5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000};

        for (int i = 0; i < width2.length; i++) {
            //设置列宽
            sheet2.setColumnWidth(i, width2[i]);

        }


        //excel列

        String[] head2 = {"列1", "列2", "列3", "列4", "列5", "列6", "列7", "列8"};
        //创建标题行
        HSSFRow title2 = sheet2.createRow(0);
        //给标题行单元格赋值
        title2.createCell(0).setCellValue("Excel 2003导出测试");

        //合并单元格          构造参数依次为起始行，截至行，起始列， 截至列

        sheet2.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        //创建并初始化标题样式
        getTitleStyle(workBook, title2);
        //初始化抬头和样式
        InitExcelHead(workBook, sheet2, head2);
        //excel内容赋值   不多线程的
        setExcelValue(workBook, sheet2, head2);


        //建立新的sheet对象（excel的表单）
        //创建Excel工作表（页签）
        HSSFSheet sheet3 = workBook.createSheet("数据导出3");

        int[] width3 = {5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000};

        for (int i = 0; i < width3.length; i++) {
            //设置列宽
            sheet3.setColumnWidth(i, width3[i]);

        }


        //excel列

        String[] head3 = {"列1", "列2", "列3", "列4", "列5", "列6", "列7", "列8"};
        //创建标题行
        HSSFRow title3 = sheet3.createRow(0);
        //给标题行单元格赋值
        title3.createCell(0).setCellValue("Excel 2003导出测试");

        //合并单元格          构造参数依次为起始行，截至行，起始列， 截至列

        sheet3.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        //创建并初始化标题样式
        getTitleStyle(workBook, title3);
        //初始化抬头和样式
        InitExcelHead(workBook, sheet3, head3);
        //excel内容赋值   不多线程的
        setExcelValue(workBook, sheet3, head3);

        //***************这里为了测试方便直接新建页签***************

        //导出处理
        excelExport(workBook);

        Timestamp nowTimestamp1 = new Timestamp(System.currentTimeMillis());

        System.err.println("-----------------------------2003 19.5W导数结束时间：------------------------\n" + nowTimestamp1);


    }


    /**
     * 背景：用多线程处理Excel大批量数据导出时。
     * <p>
     * <p>
     * <p>
     * 返回每个线程的数据下标始末，限制最大线程数
     *
     * @param size    数据的导出量
     * @param minSize 单个线程最小执行数量
     * @param maxTask 最大线程数
     * @return
     * @author: 谈荣涛
     * @date: 2018-12-18 下午03:51:36
     * <p>
     * <p>
     * <p>
     * 例如传入：(150000,50000,5)
     * <p>
     * 返回结果 [0,50000,10000,150000]
     */
    public static int[] getIndex(int size, int minSize, int maxTask) {


        int listIndexCount;
        double sizeDb = (double) size;
        double minSizeDb = (double) minSize;
        double maxTaskDb = (double) maxTask;


        if (sizeDb / minSizeDb < maxTaskDb) {
            listIndexCount = Double.valueOf(Math.ceil(sizeDb / minSizeDb)).intValue();
        } else {
            listIndexCount = maxTask;
        }

        int each = Double.valueOf(Math.floor(sizeDb / listIndexCount)).intValue();
        int[] indexs = new int[listIndexCount + 1];
        indexs[0] = 0;
        int totalCount = 0;

        for (int i = 1; i < listIndexCount; i++) {
            indexs[i] = indexs[i - 1] + each;
            totalCount += each;
        }

        // 最后一个线程可能多分担一点
        indexs[listIndexCount] = size - totalCount + indexs[listIndexCount - 1];
        return indexs;

    }


    /**
     * @throws
     * @Title: excelExport
     * @Description: TODO(excel导出类)
     * @param: @param sheet
     * @return: void
     */
    private static void excelExport(HSSFWorkbook workBook) {

        //获取文件保存路径
        String filePath = getSavePath();

        if (filePath == null) {
//终止程序
            return;

        }


        String srcFile = filePath + "\\Excel多线程导出.xls";

        FileOutputStream fileOut = null;

        try {

            File file = new File(srcFile);
            //当文件已存在时
            if (file.exists()) {
                //删除原Excel      打开新导出的Excel时，最好刷新下当前文件夹，以免重复操作有时出现缓存。
                file.delete();
            }
            fileOut = new FileOutputStream(file);
            workBook.write(fileOut);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //MsgBox.showError(e.getMessage());
        } catch (IOException e) {

            e.printStackTrace();
            //MsgBox.showError(e.getMessage());
        } finally {

            try {

                fileOut.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @throws
     * @Title: getSavePath
     * @Description: TODO(获取文件保存路径)
     * @param: @return
     * @return: String
     */
    private static String getSavePath() {

        // 选择保存路径
       /* String selectPath = null;

        JFileChooser chooser = new JFileChooser();
        //设置只能选择目录
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            selectPath = chooser.getSelectedFile().getPath();

        }*/

        return "D:\\360Downloads";

    }


    /**
     * @throws
     * @Title: setExcelValue
     * @Description: TODO(excel正文内容的填充)
     * @param: @param sheet  Excel页签对象名
     * @return: void
     */
    private static void setExcelValue(HSSFWorkbook workBook, HSSFSheet sheet, String[] head) {

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 65000; i++) {

            //sheet.createRow(i+2) 2003excel参数里面的类型是int，所以一次只能导出65535条数据
            HSSFRow row = sheet.createRow(i + 2);
            for (int j = 0; j < head.length; j++) {
                buffer.append("数据行" + (i + 1));
                buffer.append("列" + (j + 1));
                row.createCell(j).setCellValue(buffer.toString());
                buffer.delete(0, buffer.length());
            }
        }
    }


    /**
     * @param head
     * @param fields
     * @throws
     * @Title: CreateExcelHead
     * @Description: TODO(初始化Excel表头)
     * @param:
     * @return: void
     */
    private static HSSFRow InitExcelHead(HSSFWorkbook workBook, HSSFSheet sheet, String[] head) {


        HSSFRow row = sheet.createRow(1);
        //获取表头样式
        HSSFCellStyle style = getHeaderStyle(workBook);

        for (int i = 0; i < head.length; i++) {
            row.createCell(i).setCellValue(head[i]);
            //设置标题样式
            row.getCell(i).setCellStyle(style);
        }
        return row;


    }


    /**
     * 获取Excel表头样式(第二行)
     *
     * @param workbook 工作簿
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook) {


        HSSFCellStyle style = workbook.createCellStyle();
        //下边框
        style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        //左边框
        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        //居中
        style.setAlignment(HSSFCellStyle.BORDER_MEDIUM);
        // 垂直居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //上边框颜色
        style.setTopBorderColor(HSSFColor.BLACK.index);

        style.setBottomBorderColor(HSSFColor.BLACK.index);

        style.setLeftBorderColor(HSSFColor.BLACK.index);

        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 创建字体样式
        HSSFFont font = workbook.createFont();

        font.setFontName("宋体");
        // 字体大小
        font.setFontHeightInPoints((short) 14);
        // 加粗
        font.setBold(true);
        //给样式指定字体
        style.setFont(font);


        return style;


    }


    /**
     * 标题样式
     *
     * @param workbook 创建并初始化标题样式
     */
    public static void getTitleStyle(HSSFWorkbook workbook, HSSFRow title) {

        // 创建样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 垂直居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 创建字体样式
        HSSFFont font = workbook.createFont();
        // 字体
        font.setFontName("宋体");
        // 字体大小
        font.setFontHeightInPoints((short) 16);
        // 加粗
        font.setBold(true);
        //给样式指定字体
        style.setFont(font);
        //给标题设置样式
        title.getCell(0).setCellStyle(style);
    }


    /**
     * 正文样式
     *
     * @param workbook
     * @return
     */
    public static HSSFCellStyle getContextStyle(HSSFWorkbook workbook) {

        HSSFCellStyle style = workbook.createCellStyle();

        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 垂直居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }


    /**
     * 筛选条件样式
     *
     * @param workbook 工作
     * @return HSSFCellStyle
     */
    public HSSFCellStyle getFilterStyle(HSSFWorkbook workbook) {

        HSSFCellStyle style = workbook.createCellStyle();
        // 字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 垂直居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFFont font = workbook.createFont();

        font.setFontName("宋体");

        font.setFontHeightInPoints((short) 12);

        style.setFont(font);

        return style;

    }

}
