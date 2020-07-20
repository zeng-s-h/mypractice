package com.example.javacoretechnology.exceldownload.service;

import com.example.javacoretechnology.exceldownload.entity.ColumnBo;
import com.example.javacoretechnology.exceldownload.entity.ExportFileBo;

import java.util.List;
import java.util.Map;

/**
 * @author 小白i
 * @date 2020/7/20
 */
public interface IExportTreeService {

    /**
     * excel下载
     *
     * @param columns   所有的列信息
     * @param dataList  数据信息
     * @param fileName  文件名称
     * @param tableName 表名称
     * @return ExportFileBo
     */
    ExportFileBo exportFile(List<ColumnBo> columns, List<Map<String, String>> dataList, String fileName,
                            String tableName);
}
