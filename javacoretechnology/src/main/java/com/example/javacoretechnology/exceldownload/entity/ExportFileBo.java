package com.example.javacoretechnology.exceldownload.entity;

import lombok.Data;

/**
 * @author 小白i
 */
@Data
public class ExportFileBo {

	private String fileName;

	private String fullFilePath;

	private byte[] fileData;

	private boolean clearFlag = true;

	private long size;

	private boolean endWritePoint = false;

}
