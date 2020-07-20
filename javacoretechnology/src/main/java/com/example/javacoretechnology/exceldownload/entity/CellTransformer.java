package com.example.javacoretechnology.exceldownload.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author 小白i
 */
@Data
public class CellTransformer {

    private final List<SpliteCell> cellContents;

    private final int firstRowIndex;

    private final int rowSize;

    private Map<String, MergeCell> cellInfoMap;

    public CellTransformer(List<SpliteCell> contents, int firstTreeRowIndex, int rowSize) {
        this.cellContents = contents;
        this.firstRowIndex = firstTreeRowIndex;
        this.rowSize = rowSize;
    }

    public Map<String, MergeCell> transform() {
        cellInfoMap.clear();
        for (SpliteCell spliteCell : cellContents) {
            MergeCell cellInfo = cellInfoMap.get(spliteCell.getKey());
            if (cellInfo == null) {
                cellInfo = convertToCellInfo(spliteCell);
                cellInfoMap.put(cellInfo.getKey(), cellInfo);
                MergeCell mergeCell = cellInfoMap.get(cellInfo.getParentKey());
                if (mergeCell != null && StringUtils.isNotBlank(mergeCell.getKey())) {
                    mergeCell.setLeaf(false);
                }
            } else {
                cellInfo.incEndC();
                cellInfo.setLeaf(false);
            }
        }

        for (MergeCell cellInfo : cellInfoMap.values()) {
            if (cellInfo.getLeaf()) {
                cellInfo.setEndR(firstRowIndex + rowSize - 1);
            }
        }
        return cellInfoMap;
    }

    private MergeCell convertToCellInfo(SpliteCell cellContent) {
        MergeCell cellInfo = new MergeCell();
        cellInfo.setKey(cellContent.getKey());
        cellInfo.setParentKey(cellContent.getParentKey());
        cellInfo.setValue(cellContent.getValue());
        cellInfo.setStartC(cellContent.getColumnIndex());
        cellInfo.setEndC(cellContent.getColumnIndex());
        cellInfo.setStartR(cellContent.getRowIndex());
        cellInfo.setEndR(cellContent.getRowIndex());
        return cellInfo;
    }

}
