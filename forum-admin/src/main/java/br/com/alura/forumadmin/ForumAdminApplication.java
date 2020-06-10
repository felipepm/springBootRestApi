package br.com.alura.forumadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class ForumAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumAdminApplication.class, args);
	}

}
