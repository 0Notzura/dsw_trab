package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.CadastroDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Cadastros;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/cadastros/*")
public class CadastrosController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CadastroDAO dao;

    @Override
    public void init() {
        dao = new CadastroDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!usuario.getPapel().equals("USER")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [USER] tem acesso a essa página");
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
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        List<Cadastros> listacadastros = dao.getAll(usuario);
        request.setAttribute("listacadastros", listacadastros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cadastro/lista.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, Cliente> getCliente() {
        Map<Long, Cliente> clientes = new HashMap<>();
        for (Cliente cliente: new ClienteDAO().getAll()) {
            clientes.put(cliente.getId(), cliente);
        }
        return clientes;
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("clientes", getCliente());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cadastro/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Long id = Long.parseLong(request.getParameter("cliente"));
        Long locadora_id = Long.parseLong(request.getParameter("locadora"));
        String dia = request.getParameter("dia");
        String hora = request.getParameter("hora");

        Cliente cliente = new ClienteDAO().get(id);
        Locadora locadora = new LocadoraDAO().get(locadora_id);

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        
        Cadastros cadastro = new Cadastros(dia, hora, cliente, locadora, usuario);
        dao.insert(cadastro);
        
        response.sendRedirect("lista");
    }
}