package com.suntak.autotask.bean;

import java.util.List;
import java.util.Map;

public class OaFormXmlBean {
	
	private String formExportVersion = "2.0";
	
	private String tableName;
	
	private Map<String,String> tableHeaderDataMap;
	
	private List<List<Map<String,String>>> tableLinesDataList;

	public String getFormExportVersion() {
		return formExportVersion;
	}

	public void setFormExportVersion(String formExportVersion) {
		this.formExportVersion = formExportVersion;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, String> getTableHeaderDataMap() {
		return tableHeaderDataMap;
	}

	public void setTableHeaderDataMap(Map<String, String> tableHeaderDataMap) {
		this.tableHeaderDataMap = tableHeaderDataMap;
	}

	public List<List<Map<String,String>>> getTableLinesDataList() {
		return tableLinesDataList;
	}

	public void setTableLinesDataList(List<List<Map<String,String>>> tableLinesDataList) {
		this.tableLinesDataList = tableLinesDataList;
	}

}
