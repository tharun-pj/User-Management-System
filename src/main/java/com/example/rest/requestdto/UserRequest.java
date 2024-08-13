package com.example.rest.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequest {
	
	@NotNull(message = "username cannot be null")
	@Pattern(regexp = "^[a-zA-Z]+$\r\n",message = "Invalid username, should contain only alphabetical characters")
	private String username;
	@NotNull(message = "email cannot be null")
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$\r\n",message = "Invalid email, should end with @gmail.com")
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\r\n",message = "Invalid password, should follow regular password pattern")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
