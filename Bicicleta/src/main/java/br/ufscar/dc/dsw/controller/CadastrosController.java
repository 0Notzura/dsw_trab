package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
/* import java.util.HashMap;*/
import java.util.List;
/* import java.util.Map; */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Calendar;
import java.util.Date;

import br.ufscar.dc.dsw.dao.CadastroDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Cadastros;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/cadastros/*")
public class CadastrosController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CadastroDAO dao;//CRIA UM OBJ CADASTRODAO   
    private LocadoraDAO daoLocadora;//CRIA UM OBJ LOCADORADAO

    @Override
    public void init() {
        dao = new CadastroDAO();
        daoLocadora= new LocadoraDAO();
    }

    @Override//CHAMA O DOGET, O QUE IMPOSSIBILITA A PASSAGEM DE PARAMETROS NA URL
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");//PEGA O USUAIO PASSADO NA LINHA 38 DO INDEX
		Erro erros = new Erro();

		if (usuario == null) {//CASO SEJA NULO
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!usuario.getPapel().equals("USER")) {//CASO SEJA ADM
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [USER] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
			return;
		}//COEÇA O TRATMENTO
		
        String action = request.getPathInfo();//PEGA A URL PRA SABER O QUE FAZER NO SWITCH
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

    /* private Map<Long, Cliente> getCliente() {
        Map<Long, Cliente> clientes = new HashMap<>();
        for (Cliente cliente: new ClienteDAO().getAll()) {
            clientes.put(cliente.getId(), cliente);
        }
        return clientes;
    } */

    /* private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("clientes", getCliente());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cadastro/formulario.jsp");
        dispatcher.forward(request, response);
    } */
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Locadora> listaLocadoras = daoLocadora.getAll();
        request.setAttribute("listaLocadoras", listaLocadoras);
        
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cadastro/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	Erro erros = new Erro();
    	Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");
    	
    	if (clienteLogado == null) {
    		erros.add("Precisa estar logado para acessar essa página.");

            request.setAttribute("mensagens", erros);
            String URL = "/login.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
    	}
    	
    	try {
            String login = request.getParameter("login");
	        Long cnpj =Long.parseLong(request.getParameter("locadoras"));
	        String dma = request.getParameter("data");
	        String horario = request.getParameter("horario");
	
	        Locadora locadora = daoLocadora.get(cnpj);

            UsuarioDAO usuarioDAO = new UsuarioDAO();//CRIA UM NOVO OBJ USUARIO Q POSSUI OS CAMPOS (nome, login, senha, papel)
			Usuario usuario = usuarioDAO.getbyLogin(login);//RETORNA O USUARIO COM BASE NO LOGIN
	        	                
	        Cadastros cadastro = new Cadastros(dma,horario,clienteLogado, locadora,usuario );//CRIA O OBJ LOGADO

	        Cadastros existente = dao.get(clienteLogado.getCpf(), locadora.getCnpj(), dma);//

	        if (existente == null) {
	        	dao.insert(cadastro);
	        } else {
	        	erros.add("Esse horário está ocupado.");
	    		
	    		request.setAttribute("mensagens", erros);
	            String URL = "/locacoes/cadastro";
	            RequestDispatcher rd = request.getRequestDispatcher(URL);
			    rd.forward(request, response);
	            return;
	        }
    	} catch (Exception e) {
    		System.out.print(e.toString());
    		
    		erros.add("Erro nos dados preenchidos.");
    		
    		request.setAttribute("mensagens", erros);
            String URL = "/locacoes";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
    	}

        RequestDispatcher dispatcher = request.getRequestDispatcher("/locacoes/locacoes");
        dispatcher.forward(request, response);
    }
}