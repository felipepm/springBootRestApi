package br.com.alura.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private Long expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date expiracao = new Date(hoje.getTime() + expiration);
		
		return Jwts.builder()
					.setIssuer("API do Fórum da Alura")
					.setSubject(logado.getId().toString())
					.setIssuedAt(hoje)
					.setExpiration(expiracao)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		return Long.parseLong(
				Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody()
					.getSubject());
	}

} 
