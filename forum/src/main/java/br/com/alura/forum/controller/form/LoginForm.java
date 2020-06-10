package br.com.alura.forum.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String email;
	private String senha;
	
	
	public LoginForm(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(getEmail(), getSenha());
	}

	
	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
}
