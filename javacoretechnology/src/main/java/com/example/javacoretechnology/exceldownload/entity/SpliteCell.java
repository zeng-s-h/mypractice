package com.example.javacoretechnology.exceldownload.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小白i
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpliteCell {

	private String key;
	
	private String parentKey;
	
	private String value;
	
	private int columnIndex;
	
	private int rowIndex;

	public SpliteCell(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
}
