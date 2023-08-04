package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.dao.ILocacaoDAO;
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
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, ILocadoraDAO locadoraDAO, IClienteDAO clienteDAO, ILocacaoDAO locacaoDAO) {
		return (args) -> {
			
			Usuario u1 = new Usuario();
			u1.setUsername("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);
		
			
			Cliente c1 = new Cliente();
			c1.setUsername("123@gmail.com");
			c1.setPassword(encoder.encode("1234"));
			c1.setRole("ROLE_CLIENTE");
			c1.setEnabled(true);
			c1.setCPF("123.123.123-11");
			c1.setName("Delano");
			c1.setPhone("16 9 0000-0000");
			c1.setGender("M");
			c1.setDataNascimento("01/01/2023");
			usuarioDAO.save(c1);

			Locadora locadora1 = new Locadora();
			locadora1.setUsername("locadorasc@gmail.com");
			locadora1.setPassword(encoder.encode("1234"));
			locadora1.setRole("ROLE_LOCADORA");
			locadora1.setEnabled(true);
			locadora1.setCNPJ("11.222.333/4444-55");
			locadora1.setName("Locadora São Carlos");
			locadora1.setCidade("São Carlos");
			usuarioDAO.save(locadora1);

			Cliente c2 = new Cliente();
			c2.setUsername("giovani@guidini.com.br");
			c2.setPassword(encoder.encode("1234"));
			c2.setRole("ROLE_CLIENTE");
			c2.setEnabled(true);
			c2.setCPF("313.735.718-55");
			c2.setName("Giovani");
			c2.setPhone("19 9 8133-0700");
			c2.setGender("M");
			c2.setDataNascimento("12/03/2001");
			usuarioDAO.save(c2);
	
			Locadora locadora2 = new Locadora();
			locadora2.setUsername("locadorapira@gmail.com");
			locadora2.setPassword(encoder.encode("1234"));
			locadora2.setRole("ROLE_LOCADORA");
			locadora2.setEnabled(true);
			locadora2.setCNPJ("31.312.312/3131-31");
			locadora2.setName("Locadora Piracicaba");
			locadora2.setCidade("Piracicaba");
			usuarioDAO.save(locadora2);
			
			Locadora locadora3 = new Locadora();
			locadora3.setUsername("locadorapira1@gmail.com");
			locadora3.setPassword(encoder.encode("1234"));
			locadora3.setRole("ROLE_LOCADORA");
			locadora3.setEnabled(true);
			locadora3.setCNPJ("31.332.312/3131-31");
			locadora3.setName("Locadora Piracicaba reserva");
			locadora3.setCidade("Piracicaba");
			usuarioDAO.save(locadora3);

			Locacao locacao1 = new Locacao();
			locacao1.setDataRes("2023-12-03");
			locacao1.setHourRes(13);
			locacao1.setCliente(c1);
			locacao1.setLocadora(locadora1);
			locacaoDAO.save(locacao1);

			Locacao locacao2 = new Locacao();
			locacao2.setDataRes("2022-12-03");
			locacao2.setHourRes(14);
			locacao2.setCliente(c1);
			locacao2.setLocadora(locadora1);
			locacaoDAO.save(locacao2);

			Locacao locacao3 = new Locacao();
			locacao3.setDataRes("2022-12-03");
			locacao3.setHourRes(19);
			locacao3.setCliente(c2);
			locacao3.setLocadora(locadora1);
			locacaoDAO.save(locacao3);

			Locacao locacao4 = new Locacao();
			locacao4.setDataRes("2022-03-12");
			locacao4.setHourRes(12);
			locacao4.setCliente(c1);
			locacao4.setLocadora(locadora2);
			locacaoDAO.save(locacao4);
		};
	}
}
