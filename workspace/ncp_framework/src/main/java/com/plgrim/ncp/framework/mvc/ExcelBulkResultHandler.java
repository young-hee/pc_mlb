package com.plgrim.ncp.framework.mvc;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.ui.Model;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.ExcelResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 대량 data 엑셀다운로드 핸들러
 */
@Slf4j
public class ExcelBulkResultHandler implements ResultHandler {
	private SXSSFWorkbook workbook;
	private Sheet sheet;
	private Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
	private Model model;
	private int total = 0;
	private int rowCount = 1;
	
	/**
	 * 생성자
	 */
	public ExcelBulkResultHandler(SXSSFWorkbook wb, Model model, ExcelResult result){
		this.workbook = wb;
		this.sheet = workbook.createSheet("result");
		sheet.setDefaultColumnWidth(30);
		this.model = model;
		
		initStyle();
        
		//엑셀 헤더 추가
		List<String> headers = result.getHeaders();
		if(headers != null && headers.size() > 0) {
			addHeader(headers);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleResult(ResultContext context) {
		try {
			total = context.getResultCount();
            if (total == 1 || (total % 10000 == 0)) {
                log.debug("   Listed " + total + " map entries");
            }
			Map<String, Object> map = (Map<String, Object>) context.getResultObject();
			ExcelResult resultMap = new ExcelResult();
			resultMap.parse(model, map);
			List<Object> rows = resultMap.getContent();
			
			if(total < 1048575 ){
				addRow(rows);	//엑셀 ROW 추가
			}
		}
		catch (Exception e) {
			log.info("", e);
		}
	}
	
	/**
	 * row 작성
	 */
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
        	log.info("", ex);
            throw new RuntimeException(ex);
        }
	}
	
	/**
	 * header 작성
	 */
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
        	log.info("", ex);
            throw new RuntimeException (ex);
        }
    }
	
	/**
	 * 엑셀 스타일 초기화
	 */
	private void initStyle() {
		// create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        styles.put("header", style);
        
        CellStyle styleNum = workbook.createCellStyle();
        Font cellFont = workbook.createFont();
        cellFont.setFontName("Arial");
        cellFont.setFontHeightInPoints((short) 10);
        styleNum.setFont(cellFont);
        DataFormat format = workbook.createDataFormat();
        styleNum.setDataFormat(format.getFormat("#,##0"));
        styles.put("styleNum", styleNum);
        
        CellStyle styleCell = workbook.createCellStyle();
        styleCell.setFont(cellFont);
        styles.put("styleCell", styleCell);
	}
	
	/**
	 * user-agent 에 따라 엑셀 파일명 수정 
	 */
	public static String getExcelFileName(String userAgent, String fileName) {
		String displayFileName = "";
		try {
			displayFileName = fileName + ".xlsx";
	        if (userAgent.contains("Trident") || (userAgent.indexOf("MSIE") > -1)) {
	            displayFileName = URLEncoder.encode(displayFileName, "UTF-8").replaceAll("\\+", "%20");
	        } else if (userAgent.contains("Chrome")) {
	            displayFileName = new String(displayFileName.getBytes("UTF-8"), "ISO-8859-1");
	        } else if (userAgent.contains("Opera")) {
	            displayFileName = new String(displayFileName.getBytes("UTF-8"), "ISO-8859-1");
	        } else if (userAgent.contains("Firefox")) {
	            displayFileName = new String(displayFileName.getBytes("UTF-8"), "ISO-8859-1");
	        }
		} catch (Exception ex) {
        	log.info("", ex);
        }
		
        return displayFileName;
    }
}

