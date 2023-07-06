package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/listaLocadoras/*")
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
		switch(action){
			case "/listaPorCidade":
                    listaPorCidade(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
		}
	}

	private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Locadora> listalocadoras = dao.getAll();
		request.setAttribute("listalocadoras", listalocadoras);
		        Set<String> listaCidades = new HashSet<String>();

        for (int i = 0; i < listalocadoras.size(); i++) {
            String cidade = listalocadoras.get(i).getCidade();
            if (!listaCidades.contains(cidade)) {
                listaCidades.add(cidade);
            }
        }
        request.setAttribute("listaCidades", listaCidades);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/lista.jsp");
		dispatcher.forward(request, response);
	}
	    private void listaPorCidade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Locadora> listaLocadoras = null;
        String cidade = request.getParameter("cidade");
        try {
            listaLocadoras = dao.getAllCidade(cidade);
        } catch (Exception e) {
            Erro erros = new Erro();
            erros.add("Erro nos dados preenchidos.");

            request.setAttribute("mensagens", erros);

            RequestDispatcher rd = request.getRequestDispatcher("/logado/locadora/listaPorCidade.jsp");
            rd.forward(request, response);
        }
        request.setAttribute("listaLocadoras", listaLocadoras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/listaPorCidade.jsp");
        dispatcher.forward(request, response);
    }
}
