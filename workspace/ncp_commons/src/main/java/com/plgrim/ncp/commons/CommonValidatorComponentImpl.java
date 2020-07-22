
package com.plgrim.ncp.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.plgrim.ncp.framework.exception.ValidationFailException;
import com.plgrim.ncp.framework.validator.BaseValidator;
import com.plgrim.ncp.framework.validator.ValidatorFactory;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> validation factory 에서 해당하는 validator를 찾아서 실행
 *   <li> [기능2]
 * </ul>.
 *
 * @author sewoon1.choi
 * @since 2015. 6. 25.
 */
@Data
public class CommonValidatorComponentImpl implements CommonValidatorComponent{
	
	ValidatorFactory validatorFactory; 
	
	private final String FIX_CODE = "valid.err.";
	
	public void valid(Object bean){
		valid(bean,null);
	}
	
	public void valid(Object bean,Object param){
		BaseValidator validator = validatorFactory.getValidator(bean);
		if(validator != null){
		
			
			BeanPropertyBindingResult bindResult = null;
				
			bindResult = new BeanPropertyBindingResult(bean,bean.getClass().getName());
			
			if(param != null){
				validator.validate(bean,param, bindResult);
			}else{
				validator.validate(bean, bindResult);
			}
			
			
			if (bindResult.hasErrors())
			{
				ObjectError code = null;
				FieldError field = null;
				//String[] errorMsg = new String[bindResult.getAllErrors().size()];
				
				code = bindResult.getAllErrors().get(0);
				field = bindResult.getFieldErrors().get(0);
				
				/*for(int i = 0 ; i < errorMsg.length ; i++){
					code = bindResult.getAllErrors().get(i);
					field = bindResult.getFieldErrors().get(i);
					
					break;
					
				}*/
				
				throw new ValidationFailException(FIX_CODE+code.getCode(),new String[]{field.getField()});
			}
		}
		
	}
	
	public List<Map<String,String>> validResult(BaseValidator validator){
		return validResult(validator,null);
	}
	
	public List<Map<String,String>> validResult(Object bean,Object param){
		
		BaseValidator validator = validatorFactory.getValidator(bean);
		List<Map<String,String>> errorList = null;
		Map<String,String> errorMap = null;
		
		if(validator != null){
			
			BeanPropertyBindingResult bindResult = null;
				
			bindResult = new BeanPropertyBindingResult(bean,bean.getClass().getName());
			
			if(param != null){
				validator.validate(bean,param, bindResult);
			}else{
				validator.validate(bean, bindResult);
			}
			
			
			if (bindResult.hasErrors())
			{
				errorList = new ArrayList<Map<String,String>>();
				
				ObjectError code = null;
				FieldError field = null;
				
				code = null;
				field = null;
				
				for(int i = 0 ; i < bindResult.getAllErrors().size() ; i++){
					code = bindResult.getAllErrors().get(i);
					field = bindResult.getFieldErrors().get(i);
					
					errorMap = new HashMap<String,String>();
					
					errorMap.put("field", field.getField());
					errorMap.put("msgCode", FIX_CODE+code.getCode());
					
					errorList.add(errorMap);
					
				}
			}
		}
		
		return errorList;
		
	}
}
