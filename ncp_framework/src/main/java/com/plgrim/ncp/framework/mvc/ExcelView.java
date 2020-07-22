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
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.ExcelResult;


/* 엑셀 전용 View */
@Slf4j
public class ExcelView extends AbstractExcelView {

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

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

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
        HSSFSheet sheet = workbook.createSheet("Report");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        
        
        CellStyle styleNum = workbook.createCellStyle();
       


        
        // create header row
        HSSFRow header = sheet.createRow(0);

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

            HSSFRow aRow = sheet.createRow(rowCount++);

            int colCount = 0;
            for (Object col : rows) {

                String value = "";

                if (col != null) {
                    value = StringService.trimToEmpty(col.toString());
                }
                HSSFCell k=aRow.createCell(colCount);
                if(col instanceof BigDecimal || col instanceof Double|| col instanceof Long){
                	
                	if(String.valueOf(col).indexOf(".")>-1){
                		 k.setCellValue(Double.parseDouble(value));
                	}else{
                		 styleNum.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
                		 k.setCellStyle(styleNum);                	
                     	//k.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                     	 k.setCellValue(Double.parseDouble(value));
                	}
                	
                }else{
                	//k.setCellType(HSSFCell.CELL_TYPE_STRING);
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
}
