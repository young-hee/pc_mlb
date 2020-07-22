/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 24       
 */
package com.plgrim.ncp.framework.mvc;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.ExcelResult;

import lombok.extern.slf4j.Slf4j;


/* 엑셀 전용 View */
@Slf4j
public class ExcelViewSXSSF extends AbstractExcelView {

	/*
     * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/*
     * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

    public void DownloadView() {
        // Excel의 경우
        super.setContentType("application/xlsx");
    }
    /*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

    protected void buildExcelSXSSF(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ExcelResult result = (ExcelResult) model.get(ExcelResult.MODEL_NAME);
        String displayFileName = result.getFileName();
        String userAgent = request.getHeader("User-Agent");

        if (userAgent.contains("Trident") || (userAgent.indexOf("MSIE") > -1)) {
            log.info("user-agent:IE under 10 : {}", userAgent);
            displayFileName = URLEncoder.encode(displayFileName, "UTF-8").replaceAll("\\+", "%20");
        } else if (userAgent.contains("Chrome")) {
            log.info("user-agent:Chrome : {}", userAgent);
            displayFileName = new String(displayFileName.getBytes("UTF-8"), "ISO-8859-1");
        } else if (userAgent.contains("Opera")) {
            log.info("user-agent:Opera : {}", userAgent);
            displayFileName = new String(displayFileName.getBytes("UTF-8"), "ISO-8859-1");
        } else if (userAgent.contains("Firefox")) {
            log.info("user-agent:Firefox : {}", userAgent);
            displayFileName = new String(displayFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        // 다운로드 파일명 설정
        response.setHeader("Content-Disposition", "attachment; filename=" + displayFileName + ".xlsx");


        // get data model which is passed by the Spring container
        List<String> headers = result.getHeaders();
        List<List<Object>> list = result.getContents();

        // create a new Excel sheet
        SXSSFWorkbook workbook = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sheet = workbook.createSheet("Report"); //iterating r number of rows
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 11);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        
        CellStyle styleNum = workbook.createCellStyle();
        Font cellFont = workbook.createFont();
        cellFont.setFontName("Arial");
        cellFont.setFontHeightInPoints((short) 11);
        styleNum.setFont(cellFont);
        
        CellStyle styleCell = workbook.createCellStyle();
        styleCell.setFont(cellFont);
       
        // create header row
        Row header = sheet.createRow(0);

        // excel 헤더 설정
        int headerCount = 0;
        for (String element : headers) {

            header.createCell(headerCount).setCellValue(element);
            header.getCell(headerCount).setCellStyle(style);
            headerCount++;
        }
        
        
        //컨텐츠 리스트
        try {
        	int rowCount = 1;
        	for (List<Object> rows : list) {
        		
        		Row row = sheet.createRow(rowCount++); //iterating c number of columns 
        		
        		int colCount = 0;
        		for (Object col : rows) {
        			
        			String value = "";
        			
        			if (col != null) {
        				value = StringService.trimToEmpty(col.toString());
        			}
        			Cell k = row.createCell(colCount);
        			if(col instanceof BigDecimal || col instanceof Double|| col instanceof Long){
        				
        				if(String.valueOf(col).indexOf(".")>-1){
        					k.setCellStyle(styleCell);
        					k.setCellValue(Double.parseDouble(value));
        				}else{
        					DataFormat format = workbook.createDataFormat();
        					styleNum.setDataFormat(format.getFormat("#,##0"));
        					k.setCellStyle(styleNum);                	
        					k.setCellValue(Double.parseDouble(value));
        				}
        				
        			}else{
        				k.setCellValue(value); 
        			}
        			
        			colCount++;
        		}
        		if ( colCount % 10000 == 0) { log.debug("{}", colCount); } 
        	}
        	
        	// Set the content type.
        	response.setContentType(getContentType());
        	
        	// Flush byte array to servlet output stream.
        	ServletOutputStream out = response.getOutputStream();
        	workbook.write(out);
        	out.flush();
        	out.close();
		}
		catch (Exception e) {
			log.debug("", e); 
		}
    }

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		buildExcelSXSSF( model,  request,  response);
		
	}

}
