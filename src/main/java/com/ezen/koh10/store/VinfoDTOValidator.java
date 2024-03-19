package com.ezen.koh10.store;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VinfoDTOValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
	
		return VinfoDTO.class.isAssignableFrom(clazz) ;
	}

	@Override
	public void validate(Object target, Errors errors) {
		
			VinfoDTO dto =(VinfoDTO) target;
			String name=dto.getName();//�̸�
			int age=dto.getAge();//���� 
			
			//�̸��˻�
			if(name==null || name.trim().isEmpty())
			{
				errors.rejectValue("name", "�̸��� ����ȵ�");
				
			}
			
			if(age<1 || age>150) {
				errors.rejectValue("age","���̹��� 1-150 ");
			}
		
	}
	

}
