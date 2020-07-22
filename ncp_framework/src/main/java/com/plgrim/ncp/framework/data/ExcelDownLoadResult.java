package com.plgrim.ncp.framework.data;

import java.util.List;
import java.util.Map;

import lombok.Data;

import org.springframework.ui.Model;

import com.google.common.collect.Lists;

/**
 * 엑셀다운로드 전용 Result.
 */
@Data
public class ExcelDownLoadResult {

    public static final String MODEL_NAME = "result";

    /* 엑셀 다운로드 파일명 (확장자는 생략) */
    String fileName = "report";

    /* 엑셀 헤더 */
    List<List<String>> headers = Lists.newArrayList();

    /*  result 리스트*/
    List<List<Object>> contents = Lists.newArrayList();

    /* Model에 모든 데이터를 담는다. */
    public <T extends Map<String, ? extends Object>> void  parse(Model model, List<T> list) throws Exception {

        //maps를 List<List<String>>으로 변환 한다.
        //Map<String, String>형태를 List<String>로 변환
        for (T map : list) {

            List<Object> elements = Lists.newArrayList();
            for (String key : map.keySet()) {
                elements.add(map.get(key));
            }
            contents.add(elements);
        }

        model.addAttribute(MODEL_NAME, this);

    }
    
    public void  parse2(Model model, List<List<Object>> list) throws Exception {

    	this.contents = list;

        model.addAttribute(MODEL_NAME, this);

    }

    /* 헤더 추가 */
    public void addHeader(List<String> element) throws Exception {
        this.headers.add(element);
    }

    static private String hexEncode(byte[] aInput){
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','A','B','C','D','E','F'};
        for (int idx = 0; idx < aInput.length; ++idx) {
            byte b = aInput[idx];
            result.append(digits[ (b&0xf0) >> 4 ]);
            result.append(digits[ b&0x0f]);
        }
        return result.toString();
    }


}

