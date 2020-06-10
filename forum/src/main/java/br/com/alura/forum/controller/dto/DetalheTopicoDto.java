package br.com.alura.forum.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;

public class DetalheTopicoDto extends TopicoDto {

	
	private StatusTopico status;
	private List<RespostaDto> respostas;

	public DetalheTopicoDto(Topico topico) {
		super(topico);
		this.status = topico.getStatus();
		this.respostas = new ArrayList<RespostaDto>();
		this.respostas = topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList());
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDto> getRespostas() {
		return respostas;
	}

}
