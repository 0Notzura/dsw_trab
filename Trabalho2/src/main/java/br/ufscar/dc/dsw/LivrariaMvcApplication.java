package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Locadora;


@SpringBootApplication
public class LivrariaMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, ILocadoraDAO editoraDAO, IClienteDAO livroDAO) {
		return (args) -> {
			
			Usuario u1 = new Usuario();
			u1.setUsername("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);
		
			
			Cliente c1 = new Cliente();
			c1.setUsername("alice@gmail.com");
			c1.setPassword(encoder.encode("alice"));
			c1.setRole("ROLE_CLIENTE");
			c1.setEnabled(true);
			c1.setCPF("111.222.333-44");
			c1.setName("Alice");
			c1.setPhone("00 9 0000-0000");
			c1.setGender("F");
			c1.setDataNascimento("01/01/1970");
			usuarioDAO.save(c1);

			Locadora locadora1 = new Locadora();
			locadora1.setUsername("locadora_a@gmail.com");
			locadora1.setPassword(encoder.encode("locadora_a"));
			locadora1.setRole("ROLE_LOCADORA");
			locadora1.setEnabled(true);
			locadora1.setCNPJ("11.222.333/4444-55");
			locadora1.setName("Locadora A");
			locadora1.setCidade("São Carlos");
			usuarioDAO.save(locadora1);

			Cliente c2 = new Cliente();
			c2.setUsername("bob@gmail.com");
			c2.setPassword(encoder.encode("bob"));
			c2.setRole("ROLE_CLIENTE");
			c2.setEnabled(true);
			c2.setCPF("111.222.333-55");
			c2.setName("Bob");
			c2.setPhone("00 9 0000-0000");
			c2.setGender("M");
			c2.setDataNascimento("01/01/1970");
			usuarioDAO.save(c2);
	
			
		};
	}
}
