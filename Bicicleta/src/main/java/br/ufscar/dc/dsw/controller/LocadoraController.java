package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.CadastroDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Cadastros;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/locadoras/*")
public class LocadoraController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private LocadoraDAO dao;

	@Override
	public void init() {
		dao = new LocadoraDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Locadora locadora = (Locadora) request.getSession().getAttribute("locadoraLogado");
		Erro erros = new Erro();

		if (usuario == null && locadora == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (locadora == null && !usuario.getPapel().equals("ADMIN")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
			return;
		}

		String action = request.getPathInfo();
		if (action == null) {
			action = "";
		}

		try {
			switch (action) {
				case "/cadastro":
					apresentaFormCadastro(request, response);
					break;
				case "/insercao":
					insere(request, response);
					break;
				case "/remocao":
					remove(request, response);
					break;
				case "/edicao":
					apresentaFormEdicao(request, response);
					break;
				case "/atualizacao":
					atualize(request, response);
					break;
				case "/listacadastro":
					listacadastro(request, response);
					break;
				default:
					lista(request, response);
					break;
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}
	}

	private void listacadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CadastroDAO daoc= new CadastroDAO();
		Locadora locadora=(Locadora) request.getSession().getAttribute("locadoraLogado");
		List<Cadastros> listacadastros = daoc.getPorLocadora(locadora.getCnpj());
		request.setAttribute("listacadastros", listacadastros);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cadastro/lista.jsp");
		dispatcher.forward(request, response);
	}

	private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Locadora> listalocadoras = dao.getAll();
		request.setAttribute("listalocadoras", listalocadoras);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/lista.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cnpj = request.getParameter("cnpj");
		Locadora locadora = dao.getbyLogin(cnpj);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/formulario.jsp");
		request.setAttribute("locadora", locadora);
		dispatcher.forward(request, response);
	}

	private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String cnpj = request.getParameter("cnpj");
		String cidade = request.getParameter("cidade");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");

		Locadora locadora = new Locadora(email, cnpj, cidade, senha, nome);

		dao.insert(locadora);
		response.sendRedirect("lista");
	}

	private void atualize(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    String email = request.getParameter("email");
    String cnpj = request.getParameter("cnpj");
    String cidade = request.getParameter("cidade");
    String senha = request.getParameter("senha");
    String nome = request.getParameter("nome");

    Locadora locadoraExistente = dao.getbyLogin(cnpj);
    
    if (locadoraExistente != null) {
        locadoraExistente.setEmail(email);
        locadoraExistente.setCnpj(cnpj);
        locadoraExistente.setCidade(cidade);
        locadoraExistente.setSenha(senha);
        locadoraExistente.setNome(nome);

        dao.update(locadoraExistente);
        response.sendRedirect("lista");
    } 
}
	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cnpj = request.getParameter("cnpj");

		Locadora locadora = new Locadora(cnpj);
		dao.delete(locadora);
		response.sendRedirect("lista");
	}
}