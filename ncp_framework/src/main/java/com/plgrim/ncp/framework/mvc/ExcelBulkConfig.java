package com.plgrim.ncp.framework.mvc;

//import java.io.File;
//import javax.annotation.PostConstruct;
//import org.apache.poi.util.TempFile;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelBulkConfig {
	
//	@Value("${excel.poi.temp.dir}")
//	private String excelPoiTempDir;
//
//	@PostConstruct
//	public void init() {
//		//#49504 대용량 엑셀 파일 생성시 임시파일 폴더를 /tmp가 아닌 어플리케이션 영역을 사용하게 수정 : change system property "java.io.tmpdir"(poi_version=3.14)
//		File dir = new File(excelPoiTempDir);
//		dir.mkdir();
//		TempFile.setTempFileCreationStrategy(new TempFile.DefaultTempFileCreationStrategy(dir));
//	}
}
