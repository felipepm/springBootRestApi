package br.com.alura.forum.config.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	UsuarioRepository usuarioRepository;

	public AutenticacaoService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if (usuario.isPresent()) {
			return usuario.get();
		} else {
			throw new UsernameNotFoundException("Dados inválidos");
		}
		
	}

}
