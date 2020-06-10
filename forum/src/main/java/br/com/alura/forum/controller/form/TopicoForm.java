package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

public class TopicoForm {

	@NotNull @NotEmpty @Size(min=5)
	private String titulo;
	
	@NotNull @NotEmpty @Size(min=5)
	private String mensagem;
	
	@NotNull @NotEmpty
	private String nomeCurso;
	
	
	public TopicoForm(String titulo, String mensagem, String nomeCurso) {
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.nomeCurso = nomeCurso;
	}

	
	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public String getNomeCurso() {
		return nomeCurso;
	}


	public Topico converter(Curso curso) {
		return new Topico(getTitulo(), getMensagem(), curso);
	}
}
