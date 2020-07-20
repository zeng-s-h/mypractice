package com.example.javacoretechnology.exceldownload.service.impl;

import com.example.javacoretechnology.exceldownload.entity.*;
import com.example.javacoretechnology.exceldownload.service.IExportTreeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小白i
 * @date 2020/7/20
 */
@Service
public class ExportTreeServiceImpl implements IExportTreeService {

    private static final String SPLIT_TARGET = "##";

    @Override
    public ExportFileBo exportFile(List<ColumnBo> columns, List<Map<String, String>> dataList, String fileName, String tableName) {
        List<ColumnBo> allShowColumns = new ArrayList<>();
        List<String> targetNames = new ArrayList<>();

        columns.forEach(column -> {
            String columnValue = column.getColumnValue();
            List<ColumnBo> children = column.getChildren();
            getTargetNames(children, targetNames, columnValue, allShowColumns);
            if (children.size() == 0) {
                targetNames.add(columnValue);
                allShowColumns.add(column);
            }
        });

        final int firstTreeRowIndex = 0;
        int rowSize = getRowSize(targetNames);

        List<SpliteCell> contents = new ArrayList<>();
        for (int i = 0; i < targetNames.size(); i++) {
            String[] values = targetNames.get(i).split(SPLIT_TARGET);
            for (int j = 0; j < values.length; j++) {
                String value = values[j];
                String key = getKey(values, j);
                String parentKey = getParentKey(values, j);
                contents.add(new SpliteCell(key, parentKey, value, i, j + firstTreeRowIndex));
            }
        }

        final SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet(tableName);

        final CellStyle titleCellStyle = getCellStyle(workbook, true, "宋体", 10, Font.BOLDWEIGHT_BOLD, "",
                CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
        final CellStyle dataTextCellStyle = getCellStyle(workbook, false, "宋体", 10, Font.BOLDWEIGHT_BOLD, "",
                CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
        final CellStyle dataNumCellStyle = getCellStyle(workbook, false, "宋体", 10, Font.BOLDWEIGHT_BOLD, "#,##0",
                CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
        //百分比样式
        final CellStyle dataNumPercentCellStyle = getCellStyle(workbook, false, "宋体", 10, Font.BOLDWEIGHT_BOLD, "0%",
                CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);

        Map<Integer, CellStyle> numCellStyleMap = new HashMap<>();
        numCellStyleMap.put(0, dataNumCellStyle);
        numCellStyleMap.put(100, dataNumPercentCellStyle);

        CellTransformer cellInfoManager = new CellTransformer(contents, firstTreeRowIndex, rowSize);
        Map<String, MergeCell> map = cellInfoManager.transform();
        return null;
    }

    /**
     * 创建excel样式
     *
     * @param workbook   workbook
     * @param isWrap     是否换行
     * @param fontName   字体名称
     * @param fontSize   字体大小
     * @param boldWeight 加粗
     * @param format     格式
     * @param hAlign     垂直
     * @param vAlign     水平
     * @return CellStyle
     */
    private static CellStyle getCellStyle(final SXSSFWorkbook workbook, final boolean isWrap, final String fontName,
                                          final int fontSize, final short boldWeight, final String format, final short hAlign, final short vAlign) {
        CellStyle cellStyle = workbook.createCellStyle();
        // 是否自动换行
        cellStyle.setWrapText(isWrap);
        Font font = workbook.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short) fontSize);
        font.setBoldweight(boldWeight);
        cellStyle.setFont(font);

        if (StringUtils.isNotBlank(format)) {
            DataFormat dataFormat = workbook.createDataFormat();
            // 两位小数#,##0.00
            cellStyle.setDataFormat(dataFormat.getFormat(format));
        }
        // 垂直居中
        cellStyle.setVerticalAlignment(vAlign);
        // 水平居右
        cellStyle.setAlignment(hAlign);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        return cellStyle;

    }

    private String getParentKey(String[] values, int index) {
        if (index == 0) {
            return null;
        }
        return getKey(values, index);
    }

    private String getKey(String[] values, int j) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < (j + 1); i++) {
            sb.append(values[j] + SPLIT_TARGET);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private int getRowSize(List<String> targetNames) {
        int rowSize = 0;
        for (String name : targetNames) {
            rowSize = Math.max(rowSize, name.split(SPLIT_TARGET).length);
        }
        return rowSize;
    }

    private void getTargetNames(List<ColumnBo> children, List<String> targetNames, String parentValue,
                                List<ColumnBo> allShowColumns) {

        children.forEach(child -> {
            String columnValue = child.getColumnValue();
            List<ColumnBo> children2 = child.getChildren();
            String nowTarget = parentValue + SPLIT_TARGET + columnValue;
            if (children2.size() == 0) {
                targetNames.add(nowTarget);
                allShowColumns.add(child);
            }
            getTargetNames(children2, targetNames, nowTarget, allShowColumns);
        });
    }
}
