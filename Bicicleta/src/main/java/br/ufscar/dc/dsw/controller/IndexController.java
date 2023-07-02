package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout.jsp" })
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		if (request.getParameter("bOK") != null) {//VERIFICA SE O FORMS FOI PREENCHIDO
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			if (login == null || login.isEmpty()) {//VERIFICA SE TEM LOGIN
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {//VERIFICA SE TEM SENHA
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {//CASO NÃO HAJA ERROS
				UsuarioDAO dao = new UsuarioDAO();//CRIA UM NOVO OBJ USUARIO Q POSSUI OS CAMPOS (nome, login, senha, papel)
				Usuario usuario = dao.getbyLogin(login);//RETORNA O USUARIO COM BASE NO LOGIN
				ClienteDAO dao2 = new ClienteDAO();
				Cliente cliente = dao2.getbyEmail(login);

				if (usuario != null) {//CONFERE SE O USUARIO EXISTE NO BD
					if (usuario.getSenha().equalsIgnoreCase(senha)) {//CONFERE SE A SENHA PASSADA BATE COM A SENHA NO BD
						request.getSession().setAttribute("usuarioLogado", usuario);//PASSA O USUARIO COMO PARAMETRO NO REQUEST
						if (usuario.getPapel().equals("ADMIN")) {//CONFERE PRA QUAL PAGINA SERA ENVIADO, A DO ADM OU DO USER
							response.sendRedirect("usuarios/");
						} else {
							response.sendRedirect("cadastros/");
						}
						return;
					} else {//MENSAGEM CASO O USUARIO EXISTA,MAS A SENHA ESTEJA ERRADA
						erros.add("Senha inválida!");
					}
				} else {//CASO O USUARIO NÃO EXISTA, VE SE EXISTE CLIENTE
					if (cliente != null){
						if(cliente.getSenha().equalsIgnoreCase(senha)){
							request.getSession().setAttribute("clienteLogado", cliente);
							response.sendRedirect("cadastros/");
							return;

						}else{
							erros.add("Senha inválida!");
						}
					}else{
					erros.add("Usuário não encontrado!");
					
				} 
				}
			}
		}
		request.getSession().invalidate();//quando sai do if, ou seja, logout, APAGA OS DADOS DA SEÇÃO

		request.setAttribute("mensagens", erros);//retorna a mensagem de erro no login

		String URL = "/login.jsp";//RETORNA PRO LOGIN
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}