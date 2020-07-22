package com.plgrim.ncp.biz.settlement.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.ui.Model;

import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.ExcelResult;


public class ExcelDataListResultHandler implements ResultHandler {

	private static Log logger = LogFactory.getLog(ExcelDataListResultHandler.class);

	/**
	 * @uml.property  name="sw"
	 * @uml.associationEnd  
	 */
	private Sheet sheet;
	private Map<String, CellStyle> styles;
	private Model model;
	private int total = 0;
	private int rowCount = 1;
	
	public ExcelDataListResultHandler(Sheet sheet, Map<String, CellStyle> styles, Model model,ExcelResult result){
		this.sheet = sheet;
		this.styles = styles;
		this.model = model;
		
		List<String> headers = result.getHeaders();
		//엑셀 헤더 추가
		addHeader(headers);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleResult(ResultContext context) {

		try {
			total = context.getResultCount();
            if (logger.isDebugEnabled() && (total == 1 || (total % 10000 == 0) ))
            {
                logger.debug("   Listed " + total + " map entries");
            }
			Map<String, Object> map = (Map<String, Object>) context.getResultObject();
			ExcelResult resultMap = new ExcelResult();
			resultMap.parse(model,map);
			List<Object> rows = resultMap.getContent();
			
			if(total < 1048559 ){
				//엑셀 ROW 추가
				addRow(rows);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addRow(List<Object> rows) {
        
        try {
        	
        	Row row = sheet.createRow(rowCount++);
        	
        	int colCount = 0;
            for (Object col : rows) {
            	
            	String value = "";
    			
    			if (col != null) {
    				value = StringService.trimToEmpty(col.toString());
    			}
    			Cell k = row.createCell(colCount);
    			if(col instanceof BigDecimal || col instanceof Double|| col instanceof Long){
    				
    				if(String.valueOf(col).indexOf(".")>-1){
    					k.setCellStyle(styles.get("styleCell"));
    					k.setCellValue(Double.parseDouble(value));
    				}else{
    					k.setCellStyle(styles.get("styleNum"));                	
    					k.setCellValue(Double.parseDouble(value));
    				}
    				
    			}else{
    				k.setCellValue(value); 
    			}
    			colCount++;
            }
        }
        catch (Exception ex) {
            throw new RuntimeException (ex);
        }
	}
	
	private void addHeader(List<String> headers){
        
        try {
            
        	// create header row
            Row header = sheet.createRow(0);

            // excel 헤더 설정
            int headerCount = 0;
            for (String element : headers) {
            	Cell cell = header.createCell(headerCount);
            	cell.setCellValue(element);
            	cell.setCellStyle(styles.get("header"));
                headerCount++;
            }
            
        } catch (Exception ex) {
            throw new RuntimeException (ex);
        }
    }
}

