package com.example.javacoretechnology.exceldownload.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小白i
 */
@Data
public class ColumnBo {

	private String columnId;
	
	private String columnCode;
	
	private String columnValue;
	
	private long order;
	/**
	 * C:字符，N:数字
	 */
	private String type;
	
	private boolean readOnly;
	
	private long columnWidth;
	
	private int precision;
	
	private List<ColumnBo> children = new ArrayList<>();

}
