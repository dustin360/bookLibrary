package com.booklibrary.demo.dto.incoming;

import com.booklibrary.demo.constant.MessageConstant;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {

	@NotEmpty(message = MessageConstant.USERNAME_EMPTY)
	private String email;

	@NotEmpty(message = MessageConstant.PASSWORD_EMPTY)
	private String password;


}
