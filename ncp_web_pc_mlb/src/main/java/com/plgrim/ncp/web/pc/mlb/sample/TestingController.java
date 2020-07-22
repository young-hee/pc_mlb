package com.plgrim.ncp.web.pc.mlb.sample;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plgrim.ncp.commons.testing.CommonTestingController;

/**
 * Sample Controller for test
 * 
 * @author Chulhui Park <charles@plgrim.com>
 *
 */
@Controller
@RequestMapping(value = "/sample")
public class TestingController extends CommonTestingController {

    @RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return "tiles:sample/main";
    }
	
    @RequestMapping(value = "/main/getInfo", method = {RequestMethod.GET}, produces="application/json")
    @ResponseBody
    public SampleDTO getInfo(HttpServletRequest request, HttpServletResponse response) {
    	SampleDTO sampleDTO = new SampleDTO();
    	
    	sampleDTO.setTitle("Hello World");
    	
    	LinkedList<SampleProductDTO> list = new LinkedList<>();
    	for (int i=1; i<11; i++) {
    		SampleProductDTO productDTO = new SampleProductDTO();
    		productDTO.setSeq(i);
    		productDTO.setName("name "+ i);
    		productDTO.setContent("content "+ i);
    		list.add(productDTO);
    	}
    	sampleDTO.setSampleList(list);
    	
    	return sampleDTO;
    }
}
