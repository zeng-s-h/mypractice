package com.example.javacoretechnology.exceldownload.entity;

import lombok.Data;

@Data
public class MergeCell {

	private String key;
	
	private String parentKey;
	
	private String value;
	
	private int startC;
	
	private int endC;
	
	private int startR;
	
	private int endR;
	
	private Boolean leaf = true;

	public void incEndC(){
		this.endC ++;
	}


}
