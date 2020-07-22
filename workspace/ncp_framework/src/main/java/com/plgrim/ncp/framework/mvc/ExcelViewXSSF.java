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

import java.awt.Color;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.ExcelResult;


/* 엑셀 전용 View */
@Slf4j
public class ExcelViewXSSF extends AbstractExcelView {

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
        super.setContentType("application/xls");
    }
    /*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

    protected void buildExcelXSSF(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

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
        response.setHeader("Content-Disposition", "attachment; filename=" + displayFileName + ".xls");


        // get data model which is passed by the Spring container
        List<String> headers = result.getHeaders();
        List<List<Object>> list = result.getContents();

        // create a new Excel sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Report");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(new XSSFColor(Color.BLUE).getIndexed());
        //style.setFillForegroundColor(XSSFColor.BLUE.index);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(new XSSFColor(Color.WHITE).getIndexed());
        //font.setColor(XSSFColor.WHITE.index);
        style.setFont(font);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        
        
        XSSFCellStyle styleNum = workbook.createCellStyle();
       


        
        // create header row
        XSSFRow header = sheet.createRow(0);

        // excel 헤더 설정
        int headerCount = 0;
        for (String element : headers) {

            header.createCell(headerCount).setCellValue(element);
            header.getCell(headerCount).setCellStyle(style);
            headerCount++;
        }

        //컨텐츠 리스트
        int rowCount = 1;
        for (List<Object> rows : list) {

            XSSFRow aRow = sheet.createRow(rowCount++);

            int colCount = 0;
            for (Object col : rows) {

                String value = "";

                if (col != null) {
                    value = StringService.trimToEmpty(col.toString());
                }
                XSSFCell k=aRow.createCell(colCount);
                if(col instanceof BigDecimal || col instanceof Double|| col instanceof Long){
                	
                	if(String.valueOf(col).indexOf(".")>-1){
                		 k.setCellValue(Double.parseDouble(value));
                	}else{
                		 XSSFDataFormat format = workbook.createDataFormat();
                		 styleNum.setDataFormat(format.getFormat("#,##0"));
                		 k.setCellStyle(styleNum);                	
                     	//k.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                     	 k.setCellValue(Double.parseDouble(value));
                	}
                	
                }else{
                	//k.setCellType(XSSFCell.CELL_TYPE_STRING);
                	 k.setCellValue(value);
                }
                
                colCount++;
            }
        }
    }
    /*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		buildExcelXSSF( model,  request,  response);
		
	}
}
