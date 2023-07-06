package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Locadora;

import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout.jsp" })
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		if (request.getParameter("bOK") != null) {
			String email = request.getParameter("login");
			String senha = request.getParameter("senha");
			if (email == null || email.isEmpty()) {
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				Usuario usuario = usuarioDAO.getbyLogin(email);

				LocadoraDAO locadoraDAO = new LocadoraDAO();
				Locadora locadora = locadoraDAO.getbyLogin(email);

				if (usuario != null && usuario.getSenha().equalsIgnoreCase(senha)) {
					request.getSession().setAttribute("usuarioLogado", usuario);
					if (usuario.getPapel().equals("ADMIN")) {
						response.sendRedirect("usuarios/");
					} else {
						response.sendRedirect("cadastros/");
					}
					return;
				} else if (locadora != null && locadora.getSenha().equalsIgnoreCase(senha)) {
					request.getSession().setAttribute("locadoraLogado", locadora);
					response.sendRedirect("locadoras/listacadastro");
					return;
				} else {
					erros.add("Usuário ou senha inválidos!");
				}
			}
		}
		request.getSession().invalidate();// quando sai do if, ou seja, logout, APAGA OS DADOS DA SEÇÃO

		request.setAttribute("mensagens", erros);// retorna a mensagem de erro no login

		String URL = "/login.jsp";// RETORNA PRO LOGIN
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