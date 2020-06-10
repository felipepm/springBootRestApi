package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AtualizacaoTopicoForm {

	@NotNull @NotEmpty @Size(min=5)
	private String titulo;
	
	@NotNull @NotEmpty @Size(min=5)
	private String mensagem;

	
	public AtualizacaoTopicoForm(String titulo, String mensagem) {
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
	}
	

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}
}
