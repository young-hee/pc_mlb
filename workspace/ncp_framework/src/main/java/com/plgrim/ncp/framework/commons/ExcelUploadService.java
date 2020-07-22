package com.plgrim.ncp.framework.commons;

import com.google.common.collect.Maps;
import com.plgrim.ncp.framework.cloud.CloudFileSystem;
import com.plgrim.ncp.framework.cloud.aws.S3FileSystem;
import com.plgrim.ncp.framework.data.ExcelFileResult;
import com.plgrim.ncp.framework.data.FileUploadResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class ExcelUploadService {

    @SuppressWarnings("rawtypes")
    public static ExcelFileResult excelUpload(List<MultipartFile> files, String[] properties, Class cls, HttpServletRequest request,
                                              String upfilePath) throws Exception {

        return excelUpload(files, properties, cls, false, null, request, upfilePath);

    }

    @SuppressWarnings("rawtypes")
    public static ExcelFileResult excelUpload(List<MultipartFile> files, String[] properties, Class cls, boolean group,
                                              Integer[] groupKeys, HttpServletRequest request, String upfilePath) throws Exception {
        return excelUpload(files, properties, cls, group, groupKeys, request, upfilePath, false);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ExcelFileResult excelUpload(List<MultipartFile> files, String[] properties, Class cls, boolean group,
                                              Integer[] groupKeys, HttpServletRequest request, String upfilePath, boolean chkPhoneNumber)
            throws Exception {

        String[] excludeExtensions = {"jsp", "php", "exe", "bat"};

        DateService dateService = new DateService();
        upfilePath += dateService.getStringCurrentToday() + "/";

        FileUploadResult fileUploadResult = IOService.fileUploadAutoFileName(files, upfilePath, excludeExtensions);
        String filePath = fileUploadResult.getRows().get(0).getFilePath();
        String fileName = fileUploadResult.getRows().get(0).getFileName();

        S3FileSystem s3FileSystem = new S3FileSystem();

        Workbook workbook = null;
        Workbook workbookErr = null;
        InputStream fileInput = null;
        String bucketName = IOService.getBucketName();
        String cloudFilePath = bucketName + ":" + filePath;
        try {

            fileInput = s3FileSystem.getInputStream(cloudFilePath + fileName);

            if (fileName.toUpperCase().indexOf(".XLSX") != -1) {
                //workbook = new XSSFWorkbook(fileInput);
                workbook = WorkbookFactory.create(fileInput);
                workbookErr = new XSSFWorkbook();
            } else if (fileName.toUpperCase().indexOf(".XLS") != -1) {

                workbook = new HSSFWorkbook(new POIFSFileSystem(fileInput));
                workbookErr = new HSSFWorkbook();
            }

        } catch (Exception e) {
            log.error("", e);
            throw new Exception("파일로드중 에러");
        } finally {

            fileInput.close();

        }


        //첫번째 sheet의 데이터만 적재
        Sheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();

        Map<Integer, Object> excelMap = Maps.newHashMap();
        Map<Integer, Object> excelErrMap = Maps.newHashMap();

        Map<Integer, String> errMap = Maps.newHashMap();
        Map<String, ArrayList<Integer>> groupkeyMap = Maps.newHashMap();
        Map<String, Integer> errGroupkeyMap = Maps.newHashMap();
        int requestCount = 0;
        if (rows >= 2) {

            errMap.put(0, "");

            for (int i = 1; i < rows; i++) {
                Class c = null;
                Object obj = null;
                Map<String, Object> map = null;
                if (cls.equals(HashMap.class)) {
                    map = Maps.newHashMap();
                } else {
                    c = Class.forName(cls.getName());
                    obj = c.newInstance();
                }

                Row row = sheet.getRow(i);

                // 빈로우가 오면 Loop 종료
                if (row == null || row.getCell(0) == null || "".equals(row.getCell(0).toString().trim())) {
                    break;
                }

                int index = 0;

                boolean flag = true;
                int falseCount = 0;

                String tempKey = "";

                /*
                 * '수령인 핸드폰번호' 전화번호 및 오류여부
                 *	- '수령인 핸드폰번호', '수령인 전화번호' 둘중에 하나는 정상이어야 주문 등록 가능
                 */
                boolean isInvalidMobileNumber = false;
                String mobileNumber = null;
                String phoneNumber = null;

                for (String prop : properties) {
                    String val = "";
                    try {
                        if (row.getCell(index) != null) {

                            // 사이트, 우편번호 항목에 숫자가 올 경우 셀 타입을 문자로 변경한다.
                            if ("mallId".equals(prop) || "cstmrPostNo".equals(prop)) {
                                row.getCell(index).setCellType(Cell.CELL_TYPE_STRING);
                            }

                            val = row.getCell(index).toString().trim();

                            // 우편번호 값이 숫자로 입력되어 4자리로 들어올 경우 맨 앞에 0을 추가한다.
                            if ("cstmrPostNo".equals(prop)) {
                                if (val.length() < 5 && val.indexOf("-") < 0) {
                                    String chkVal = new Double(val).intValue() + "";
                                    if (chkVal.length() < 5) {
                                        val = "0" + chkVal;
                                    } else {
                                        val = chkVal;
                                    }
                                }
                            }

                            if (chkPhoneNumber) {
                                /*
                                 * 1. 수정일자 	: 2016-03-24
                                 * 2. 수정자 	: 김재성 (jskim27)
                                 * 3. 요청 SR NO: #18207
                                 * 4. 수정 내용 : [제휴] EC모니터 연계를 통한 제휴 등록 시 '전화번호' 체크 기능 추가
                                 * 					- 안심번호 추가로 전화번호 패턴 변경, (\d{2,4})-(\d{3,4})-(\d{4}) 형식으로 입력되어야 함
                                 * 					- '-'를 제외한 9~12 숫자 체크 및 패턴((\d{2,4})-(\d{3,4})-(\d{4})) 에 맞지 않는 경우 Exception 발생 후 등록오류 처리
                                 * 					- "mobilNo", "telNo"는 [제휴주문등록] 엑셀업로드에서만 사용함, 향후 타 기능에서 사용 시 오류 메시지 등 조정 필요
                                 */
                                if (StringService.equalsIgnoreCase("mobilNo", prop)) {
                                    if (StringService.isEmpty(makePhoneNumber(val)) ||
                                            !isPhoneNumber(val)) {
                                        if (log.isInfoEnabled()) {
                                            log.info("Invalid mobile phone number [{}]", val);
                                        }
                                        // 엑셀 다운로드 시 표기용
                                        mobileNumber = val;
                                        isInvalidMobileNumber = true;

                                        // '수령인 핸드폰번호'가 오류인 경우 '--' 처리
                                        val = "--";
                                    }
                                }

                                if (StringService.equalsIgnoreCase("telNo", prop)) {
                                    if (StringService.isEmpty(makePhoneNumber(val)) ||
                                            !isPhoneNumber(val)) {
                                        if (log.isInfoEnabled()) {
                                            log.info("Invalid phone number [{}]", val);
                                        }
                                        // 엑셀 다운로드 시 표기용
                                        phoneNumber = val;
                                        // '수령인 전호번호'가 오류인 경우 '--' 처리
                                        val = "--";

                                        // '수령인 핸드폰번호', '수령인 전화번호' 모두 비정상인 경우
                                        if (isInvalidMobileNumber) {
                                            throw new Exception("[" + mobileNumber + "/" + phoneNumber + "]");
                                        }
                                    }
                                }
                            }
                        }

                        if (group) {

                            for (int j = 0; j < groupKeys.length; j++) {

                                int key = groupKeys[j];

                                if (index == key) {

                                    if (val.trim().length() < 1) {
                                        throw new Exception("REQUIRED");
                                    }
                                    tempKey += val;
                                }

                            }
                        }

                        if (cls.equals(HashMap.class)) {

                            map.put(prop, val);
                        } else {

                            if (prop.indexOf(".") != -1) {

                                Method subEntity = null;
                                Method subEntityProp = null;

                                String[] entity = prop.split("\\.");

                                subEntity = c.getMethod("get" + entity[0].toUpperCase().charAt(0) + entity[0].substring(1));

                                Class subCls = Class.forName(subEntity.getReturnType().getName());

                                Object subObj = subEntity.invoke(obj);

                                if (subObj == null) {
                                    subObj = subCls.newInstance();
                                }


                                subEntityProp = c.getMethod("set" + entity[0].toUpperCase().charAt(0) + entity[0].substring(1),
                                        subEntity.getReturnType());

                                Method get = subCls.getMethod("get" + entity[1].toUpperCase().charAt(0) + entity[1].substring(1));

                                Method set = subCls.getMethod("set" + entity[1].toUpperCase().charAt(0) + entity[1].substring(1),
                                        get.getReturnType());

                                invoke(subObj, row, index, val, get, set);
                                subEntityProp.invoke(obj, subObj);
                            } else {

                                Method get = c.getMethod("get" + prop.toUpperCase().charAt(0) + prop.substring(1));

                                Method set = c.getMethod("set" + prop.toUpperCase().charAt(0) + prop.substring(1), get.getReturnType());


                                invoke(obj, row, index, val, get, set);
                            }

                        }

                    } catch (Exception e) {

                        flag = false;

                        if (falseCount == 0) {
                            errMap.put(i, index + "==&==" + e.getMessage());
                        }

                        falseCount++;
                    }

                    index++;
                }

                if (flag) {

                    if (cls.equals(HashMap.class)) {

                        excelMap.put(i, map);
                    } else {
                        excelMap.put(i, obj);

                    }

                    if (groupkeyMap.containsKey(tempKey)) {

                        ArrayList<Integer> arr = groupkeyMap.get(tempKey);

                        arr.add(i);
                        groupkeyMap.put(tempKey, arr);
                    } else {

                        ArrayList<Integer> arr = new ArrayList<Integer>();
                        arr.add(i);
                        groupkeyMap.put(tempKey, arr);
                    }


                } else {
                    if (cls.equals(HashMap.class)) {

                        excelErrMap.put(i, map);
                    } else {
                        excelErrMap.put(i, obj);

                    }

                    errGroupkeyMap.put(tempKey, i);

                }

                requestCount++;
            }

        }

        List<Map<String, List<? extends Object>>> groupList = new ArrayList<Map<String, List<? extends Object>>>();
        List<Object> allList = new ArrayList<Object>();


        Iterator<String> keySet = errGroupkeyMap.keySet().iterator();

        while (keySet.hasNext()) {

            String key = keySet.next();

            Integer rowKey = errGroupkeyMap.get(key);

            String msg = errMap.get(rowKey);

            if (groupkeyMap.containsKey(key)) {

                ArrayList<Integer> arr = groupkeyMap.get(key);

                for (int j = 0; j < arr.size(); j++) {
                    Integer integer = arr.get(j);

                    errMap.put(integer, msg);
                    excelMap.remove(integer);

                }

            }

        }

        Iterator<String> excelKeySet = groupkeyMap.keySet().iterator();

        while (excelKeySet.hasNext()) {

            String key = excelKeySet.next();

            ArrayList<Integer> arr = groupkeyMap.get(key);
            Map<String, List<? extends Object>> v = Maps.newHashMap();
            List<Object> subs = new ArrayList<Object>();

            for (int j = 0; j < arr.size(); j++) {

                Integer integer = arr.get(j);
                if (excelMap.get(integer) != null) {

                    subs.add(excelMap.get(integer));
                    allList.add(excelMap.get(integer));
                }

            }

            if (subs.size() > 0) {

                v.put(key, subs);
                groupList.add(v);

            }

        }


        Sheet sheetErr = workbookErr.createSheet();

        workbookErr.setSheetName(0, "에러데이터");

        erroFile(sheet, errMap, sheetErr);


        String errFileName = "err" + String.valueOf(System.nanoTime()) + "." + FilenameUtils.getExtension(fileName);

        File pFile = new File(IOService.getFileSystemTempPath());
        if (!pFile.isDirectory()) {
            pFile.mkdirs();
        }
        File errFile = new File(pFile.getPath() + "/" + errFileName);

        FileOutputStream fileOut = new FileOutputStream(errFile);
        workbookErr.write(fileOut);

        s3FileSystem.store(errFile, cloudFilePath, CloudFileSystem.Permission.PUBLIC);
        fileOut.close();

        // S3 upload 후 File Delete
        if (errFile.isFile()) {
            errFile.delete();
        }

        ExcelFileResult list = new ExcelFileResult();

        list.setExcelMap(excelMap);
        list.setExcelErrMap(excelErrMap);

        list.setRequestCount(requestCount);
        list.setFailCount(errMap.values().size() - 1);
//		list.setErrFile(errFile);
        list.setErrFilePath(filePath);
        list.setErrFileName(errFileName);
        list.setGroupList(groupList);
        list.setAllList(allList);
        list.setGroupkeyMap(groupkeyMap);
        list.setErrCloudFilePath(cloudFilePath + errFileName);

        try {
            s3FileSystem.delete(bucketName + ":" + filePath);
        } catch (Exception e) {
            log.warn("Excel File Delete Fail");
        }

        return list;
    }

    private static void invoke(Object obj, Row row, int index, String val, Method get, Method set) throws IllegalAccessException,
            InvocationTargetException {
        if (get.getReturnType().equals(String.class)) {

            if (row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_NUMERIC) {

                BigDecimal zero = new BigDecimal("0");
                BigDecimal num = new BigDecimal(val);
                BigDecimal num2 = new BigDecimal(val).setScale(0, BigDecimal.ROUND_DOWN);

                if (num.subtract(num2).compareTo(zero) == 1) {
                    set.invoke(obj, num.toString());
                } else {
                    set.invoke(obj, String.valueOf((long) Double.parseDouble(val)));
                }

            } else {

                set.invoke(obj, val);
            }

        } else if (get.getReturnType().equals(BigDecimal.class) && !val.trim().isEmpty()) {

            set.invoke(obj, new BigDecimal(Double.parseDouble(val)));
        } else if ((get.getReturnType().equals(Long.class) || get.getReturnType().equals(long.class)) && !val.trim().isEmpty()) {

            set.invoke(obj, (long) Double.parseDouble(val));
        } else if ((get.getReturnType().equals(Integer.class) || get.getReturnType().equals(int.class)) && !val.trim().isEmpty()) {

            set.invoke(obj, (int) Double.parseDouble(val));
        } else if (get.getReturnType().equals(Date.class)) {

            set.invoke(obj, row.getCell(index).getDateCellValue());
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void erroFileDB(ExcelFileResult excelResult, String[] properties, List<? extends Object> objs) throws Exception {

        Workbook workbookErr = null;
        S3FileSystem s3FileSystem = new S3FileSystem();

        String errFilePath = excelResult.getErrFilePath();
        String errFileName = excelResult.getErrFileName();

        String bucketName = IOService.getBucketName();
        InputStream errFileInputStream = s3FileSystem.getInputStream(bucketName + ":" + errFilePath + errFileName);
        if (errFileName.toUpperCase().indexOf(".XLSX") != -1) {
            workbookErr = new XSSFWorkbook(errFileInputStream);
        } else if (errFileName.toUpperCase().indexOf(".XLS") != -1) {
            workbookErr = new HSSFWorkbook(new POIFSFileSystem(errFileInputStream));
        }

        Sheet sheetErr = workbookErr.getSheetAt(0);

        for (Object row : objs) {

            Row rowErr = sheetErr.createRow(sheetErr.getPhysicalNumberOfRows());

            Class c = Class.forName(row.getClass().getName());

            int paramInt = 0;
            for (String prop : properties) {
                Cell cellErr = rowErr.createCell(paramInt);

                Method get = c.getMethod("get" + prop.toUpperCase().charAt(0) + prop.substring(1));

                if (get.getReturnType().equals(String.class)) {

                    cellErr.setCellValue((String) get.invoke(row));

                } else if (get.getReturnType().equals(Date.class)) {
                    if (get.invoke(row) != null) {
                        cellErr.setCellValue((Date) get.invoke(row));
                    } else {
                        cellErr.setCellValue("");
                    }

                } else {


                    cellErr.setCellValue(String.valueOf(get.invoke(row)));
                }

                paramInt++;
            }

        }

        File pFile = new File(IOService.getFileSystemTempPath());
        if (!pFile.isDirectory()) {
            pFile.mkdirs();
        }
        File errFile = new File(pFile.getPath() + "/" + errFileName);

        FileOutputStream fileOut = new FileOutputStream(errFile);
        workbookErr.write(fileOut);

        s3FileSystem.store(errFile, bucketName + ":" + errFilePath, CloudFileSystem.Permission.PUBLIC);
        fileOut.close();

        // S3 Upload 후 File System File 삭제
        if (errFile.isFile()) {
            errFile.delete();
        }
    }
/*
	public static void erroFile(ExcelFileResult excelResult, Map<Integer, String> errMap) throws Exception {

		Workbook workbook = null;
		Workbook workbookErr = null;
		FileInputStream uploadFile = new FileInputStream(excelResult.getUploadFile());
		FileInputStream errFile = new FileInputStream(excelResult.getErrFile());

		if (excelResult.getErrFile().getName().toUpperCase().indexOf(".XLSX") != -1) {

			workbook = new XSSFWorkbook(uploadFile);
			workbookErr = new XSSFWorkbook(errFile);
		}
		else if (excelResult.getErrFile().getName().toUpperCase().indexOf(".XLS") != -1) {

			workbook = new HSSFWorkbook(new POIFSFileSystem(uploadFile));
			workbookErr = new HSSFWorkbook(new POIFSFileSystem(errFile));
		}
		Sheet sheet = workbook.getSheetAt(0);
		Sheet sheetErr = workbookErr.getSheetAt(0);

		erroFile(sheet, errMap, sheetErr);

		FileOutputStream fileOut = new FileOutputStream(excelResult.getErrFile());

		workbookErr.write(fileOut);
		fileOut.close();
		
		if(excelResult.getUploadFile().isFile()){
			excelResult.getUploadFile().delete();
		}
		
	}*/

    private static void erroFile(Sheet sheet, Map<Integer, String> errMap, Sheet sheetErr) {

        Iterator<Integer> keySet = errMap.keySet().iterator();
        int hInt = 0;
        while (keySet.hasNext()) {

            Integer key = keySet.next();
            Row rowErr = sheetErr.createRow(sheetErr.getPhysicalNumberOfRows());
            Iterator<Cell> cells = sheet.getRow(key).cellIterator();

            int paramInt = 0;

            while (cells.hasNext()) {
                Cell cell = cells.next();

                Cell cellErr = rowErr.createCell(paramInt);

                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

                    cellErr.setCellValue(cell.getNumericCellValue());
                    cellErr.setCellType(Cell.CELL_TYPE_NUMERIC);

                } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

                    cellErr.setCellValue(cell.getStringCellValue().trim());
                    cellErr.setCellType(Cell.CELL_TYPE_STRING);
                } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    cellErr.setCellValue(cell.getCellFormula());
                    cellErr.setCellType(Cell.CELL_TYPE_FORMULA);
                } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    cellErr.setCellValue("");
                    cellErr.setCellType(Cell.CELL_TYPE_STRING);
                } else {
                    cellErr.setCellValue("");
                    cellErr.setCellType(Cell.CELL_TYPE_STRING);

                }

                if (key == 0) {
                    sheetErr.setColumnWidth(paramInt, sheet.getColumnWidth(paramInt));
                }

                paramInt++;
            }
            if (key == 0) {
                hInt = paramInt;
            }
            log.debug("에러메세지" + hInt);
            Cell cellErr = rowErr.createCell(hInt);
            if (key == 0) {

                sheetErr.setColumnWidth(hInt, 27000);
                cellErr.setCellValue("에러메세지");
            } else {

                String[] msg = errMap.get(key).split("==&==");
                String col = "";
                String ms = "";
                if (msg.length < 2) {
                    ms = errMap.get(key);
                } else {

                    if (sheetErr.getRow(0).getCell(Integer.parseInt(msg[0])) != null) {
                        col = sheetErr.getRow(0).getCell(Integer.parseInt(msg[0])).getStringCellValue();
                    }

                    ms = msg[1];

                }

                cellErr.setCellValue(col + " 입렵값 오류 : " + ms);
            }


            cellErr.setCellType(Cell.CELL_TYPE_STRING);
        }
    }

    private static String makePhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[-]", "");
        /*
         * 안심번호 체크 추가 및 일반전화 체크 추가로 전화번호 패턴 변경, 국번
         * 	- (\d{3})(\d{3,4})(\d{4}) -> (\d{2,4})(\d{3,4})(\d{4})
         */
        String regEx = "(\\d{2,4})(\\d{3,4})(\\d{4})";

        if (!Pattern.matches(regEx, phoneNumber)) {
            return null;
        }
        return phoneNumber.replaceAll(regEx, "$1-$2-$3");
    }

    private static boolean isPhoneNumber(String phoneNumber) {
        /*
         * 안심번호 체크 추가 및 일반전화 체크 추가로 전화번호 패턴
         * 	- (\d{2,4})-(\d{3,4})-(\d{4})
         */
        String regEx = "(\\d{2,4})-(\\d{3,4})-(\\d{4})";

        if (!Pattern.matches(regEx, phoneNumber)) {
            return false;
        }
        return true;
    }
}
