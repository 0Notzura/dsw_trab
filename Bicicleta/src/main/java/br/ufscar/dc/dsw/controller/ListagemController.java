package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/listagem/*")
public class ListagemController extends HttpServlet {

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
		String action = request.getPathInfo();
		if (action == null) {
			action = "";
		}
		lista(request, response);
	}

	private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Locadora> listalocadoras = dao.getAll();
		request.setAttribute("listalocadoras", listalocadoras);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/lista.jsp");
		dispatcher.forward(request, response);
	}
}
