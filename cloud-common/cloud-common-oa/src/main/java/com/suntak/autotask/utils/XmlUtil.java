package com.suntak.autotask.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.suntak.autotask.bean.OaFormXmlBean;

/**
 * XML文档辅助类 - 2019-01-08
 * @author zzhong
 *
 */
public class XmlUtil {
	
	/**
	 * 获取一个xml文档对象 - ZZHONG20190108
	 * @return
	 * @throws ParserConfigurationException
	 */
	public static Document getDocumentObj() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//构建xml建造工厂类
		DocumentBuilder builder = factory.newDocumentBuilder();//构建xml文档建造类
		Document document = builder.newDocument();//构建文档
		document.setXmlStandalone(true);//设置是否引用外部文档，true表示不引用
		return document;
	}
	
	/**
	 * 把xml文档转换为xml字符串 - ZZHONG20190108
	 * @param document
	 * @return
	 * @throws TransformerException
	 * @throws UnsupportedEncodingException 
	 */
	public static String transferDocument2XmlString(Document document) throws TransformerException, UnsupportedEncodingException{
		if(document == null){
			return null;
		}
		TransformerFactory transFactory = TransformerFactory.newInstance();//构建xml文档转换工厂类
		Transformer transformer = transFactory.newTransformer();//构建xml文档转换类
		//transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");//设置转换的字符集，默认UTF-8
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");//设置输出结果时转换器是否可以添加额外空白，默认yes
		DOMSource domSource = new DOMSource(document);//把文档构建为DOM树的形式
	 
		// xml transform String
		ByteArrayOutputStream bos = new ByteArrayOutputStream();//创建buty数组输出流
		transformer.transform(domSource, new StreamResult(bos));//通过流转换执行xmlDocument to xmlString的转换
		String xmlString = new String(bos.toByteArray(),"UTF-8");//获取字符串
		//String xmlString = bos.toString();
		return xmlString;
	}
	

	
	/**
	 * 构造OA表单数据的xml字符串（目前只支持单个重复表） - ZZHONG20190108.n
	 * 后续修改：支持单个重复表改为多个,在subForms标签里修改逻辑，猜测subForm需要添加name属性，标识重复表的表名。
	 * @param oaForm
	 * @return
	 * @throws Exception
	 */
	public static String getOaFormXmlString(OaFormXmlBean oaForm) throws Exception{
		String formXmlString = "";
		Document document = XmlUtil.getDocumentObj();//获取document对象
		Element columentEle = null;
		Element valueEle = null;
		Element rowEle = null;
		
		Element formExportEle = document.createElement("formExport");//构建formExport元素
		formExportEle.setAttribute("version", oaForm.getFormExportVersion());
		document.appendChild(formExportEle);
		
		Element summaryEle = document.createElement("summary");//构建summary元素，包含id和name两个属性
		summaryEle.setAttribute("id", "");
		summaryEle.setAttribute("name", oaForm.getTableName());
		formExportEle.appendChild(summaryEle);
		
		Element headerValuesEle = document.createElement("values");//构建表数据values元素
		formExportEle.appendChild(headerValuesEle);
		
		//构建表数据
		if(oaForm.getTableHeaderDataMap() != null && oaForm.getTableHeaderDataMap().size() != 0){
			for(Entry<String,String> entry:oaForm.getTableHeaderDataMap().entrySet()){
				columentEle = document.createElement("column");
				columentEle.setAttribute("name",entry.getKey());
				headerValuesEle.appendChild(columentEle);
				
				valueEle = document.createElement("value");
				valueEle.setTextContent(entry.getValue());
				columentEle.appendChild(valueEle);
			}
		}
		
		Element subFormsEle = document.createElement("subForms");//构建标识子表单（重复表）元素
		formExportEle.appendChild(subFormsEle);
		
		Element subFormEle = document.createElement("subForm");//构建标识单个重复表元素
		subFormsEle.appendChild(subFormEle);
		
		Element linesValuesEle = document.createElement("values");//构建重复表values元素
		subFormEle.appendChild(linesValuesEle);
		
		//构建重复表数据
		if(oaForm.getTableLinesDataList() != null && oaForm.getTableLinesDataList().size() != 0){
			for(Map<String,String> subMap : oaForm.getTableLinesDataList()){
				rowEle = document.createElement("row");
				linesValuesEle.appendChild(rowEle);
				for(Entry<String,String> entry:subMap.entrySet()){
					columentEle = document.createElement("column");
					columentEle.setAttribute("name",entry.getKey());
					rowEle.appendChild(columentEle);
					
					valueEle = document.createElement("value");
					valueEle.setTextContent(entry.getValue());
					columentEle.appendChild(valueEle);
				}
			}
		}
		
		formXmlString = XmlUtil.transferDocument2XmlString(document);
		
		return formXmlString;
	}
	
}
