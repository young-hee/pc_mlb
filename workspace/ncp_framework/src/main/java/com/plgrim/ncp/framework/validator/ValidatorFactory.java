package com.plgrim.ncp.framework.validator;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * [클래스 설명]
 * 
 * 서버 시작시 지정된 package를 스캔하여
 * type BaseValidator 로 지정된 모든 객체를 
 * 등록한다
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sewoon1.choi
 * @since 2015. 8. 12.
 */
@Slf4j
public class ValidatorFactory {
	
	private List<BaseValidator> validators;
	
	public ValidatorFactory(List<String> scanPkg){
		validators = getValidators(scanPkg);
	}
	
	public List<BaseValidator> getValidators(List<String> scanPkgs){
		
		URL pkgUrl = null;
		List<String> pkgList = scanPkgs;
		List<String> pkgSubList = null;
		List<BaseValidator> validatorList = new ArrayList<BaseValidator>();
		String pkgPath = "";
		Object object = null;
		
		log.debug("######################################################");
		log.debug("# Validators Register Information"); 
		log.debug("######################################################");
		log.debug("");
		while(!pkgList.isEmpty()){
			
			pkgSubList = new ArrayList<String>();
			
			for(String pkgName : pkgList){
				try {
					pkgPath = pkgName.replace('.', '/');
					pkgUrl = Thread.currentThread().getContextClassLoader().getResource(pkgPath);
					if(pkgUrl == null){
						throw new Exception("Fail PKG Path : "+pkgName.replace('/', '.'));
					}
					
					File scanPath = new File(pkgUrl.getFile());
					for(File scanFile : scanPath.listFiles()){
						if(scanFile.isFile()){
							String className = scanFile.getName().substring(0, scanFile.getName().lastIndexOf('.'));
							
							try{
								object = Class.forName(pkgName.replace('/', '.')+"."+className).newInstance();
								if(object instanceof BaseValidator){
		                        	log.info("Register : "+pkgName.replace('/', '.')+"."+className);
		                        	validatorList.add((BaseValidator)object);
		                        }
							}catch(Exception c){
								throw new Exception("Invalid object : "+pkgName.replace('/', '.')+"."+className);
							} 
	                       
						}else if(scanFile.isDirectory()){
							pkgSubList.add(pkgName+"/"+scanFile.getName());
						}
						
					}
				}catch(Exception e){
					log.warn(e.getMessage());
				}
			}
			pkgList = pkgSubList;
		}
		log.debug("");
		return validatorList;
		
	}
	
	public BaseValidator getValidator(Object bean){
		BaseValidator validator = null;
		for(int i = 0 ; i < validators.size() ; i++){
			validator = validators.get(i);
			if(validator.supports(bean.getClass())){
				//validator.setTarget(bean);
				break;
			}
		}
		
		return validator;
	}
	/*
	public static void main(String args[]){
		List test = new ArrayList();
		
		test.add("1111");
		test.add("2222");
		
		log.debug(test.contains("1112"));
	}*/
}
