package br.com.alura.forum.controller.dto;

public class TokenDto {

	private String nome;
	private String tipo;
	
	
	public TokenDto(String nome, String tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}


	public String getNome() {
		return nome;
	}


	public String getTipo() {
		return tipo;
	}
}
