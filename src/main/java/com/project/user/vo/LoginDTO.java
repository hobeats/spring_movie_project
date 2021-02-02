package com.project.user.vo;

import lombok.Data;

@Data
public class LoginDTO {
	
	private String userid;
	private String userpw;
	private boolean userCookie;

}
