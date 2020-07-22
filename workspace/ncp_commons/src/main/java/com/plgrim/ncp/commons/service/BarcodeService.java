package com.plgrim.ncp.commons.service;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.krysalis.barcode4j.BarcodeClassResolver;
import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.MimeTypes;

import com.plgrim.ncp.framework.commons.StringService;

/**
 * [클래스 설명]
 * 
 * <p>
 * 바코드를 이미지 파일로 생성
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sewoon1.choi
 * @since 2015. 7. 13.
 */
@Slf4j
@Data
public class BarcodeService{
	
	private int dpi;
	
	private String format;
	
	private boolean antiAliasing;
	
	private AbstractBarcodeBean barcodeBean;
	
	private BarcodeClassResolver resolver;
	
	
	public BarcodeService(String type) throws Exception{
		
		resolver = new DefaultBarcodeClassResolver();
		Class<?> clazz = null;
		clazz = resolver.resolveBean(type);
		barcodeBean = (AbstractBarcodeBean)clazz.newInstance();
		
		
	}
	
	public void createBarcode(HttpServletResponse response, String data) throws Exception{
		
		response.setContentType("image/gif");
		OutputStream out = response.getOutputStream();

		try {
			
			if(StringService.isNotEmpty(data)){
				 
				String mimeType = MimeTypes.expandFormat(format);
				int imageType   = BufferedImage.TYPE_BYTE_BINARY;
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(
												out, 
												mimeType, 
												dpi, 
												imageType, 
												antiAliasing, 
												0);
				barcodeBean.doQuietZone(true);
				barcodeBean.setBarHeight(15); 
				barcodeBean.setModuleWidth(1);
				barcodeBean.setFontSize(7);
				barcodeBean.generateBarcode(canvas, data);
	
				canvas.finish();
				
				log.info("create image success.");
			}

		} catch (Exception e) {
			log.error("create image fail!");
			throw new RuntimeException(e.getMessage());
		}finally{
			try{
				out.close();
			}catch(IOException io){
				throw new RuntimeException(io.getMessage());
			}
		}
	}	
}