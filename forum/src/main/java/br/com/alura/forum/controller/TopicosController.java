package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalheTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	TopicoRepository topicoRepository;
	CursoRepository cursoRepository;
	
	
	public TopicosController(TopicoRepository topicoRepository, CursoRepository cursoRepository) {
		super();
		this.topicoRepository = topicoRepository;
		this.cursoRepository = cursoRepository;
	}

	@GetMapping
	@Cacheable(value="listaTopicos")
	public Page<TopicoDto> listar(@RequestParam(required=false) String nomeCurso, 
								  @PageableDefault(sort="id", direction=Direction.DESC, page=0, size=10) Pageable paginacao) {
		if (nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDto.converter(topicos);
		} else {
			Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
			return TopicoDto.converter(topicos );
		}
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="listaTopicos", allEntries=true)
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm formularioTopico, UriComponentsBuilder uriBuilder) {
		Curso curso = cursoRepository.findByNome(formularioTopico.getNomeCurso());
		Topico topico = topicoRepository.save(formularioTopico.converter(curso));
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalheTopicoDto> detalhar(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			return ResponseEntity.ok(new DetalheTopicoDto(topico.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="listaTopicos", allEntries=true)
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm formularioTopico) {
		Optional<Topico> topicoOptional = topicoRepository.findById(id);
		if(topicoOptional.isPresent()) {
			Topico topico = topicoOptional.get();
			topico.atualizar(formularioTopico);
			topico = topicoRepository.save(topico);
			return ResponseEntity.ok(new TopicoDto(topico));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="listaTopicos", allEntries=true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Topico> topicoOptional = topicoRepository.findById(id);
		if(topicoOptional.isPresent()) {
			topicoRepository.delete(topicoOptional.get());
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
