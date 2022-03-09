package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { 
		"/Controller", 
		"/main",
		"/insert",
		"/select",
		"/update",
		"/delete"
	})

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch(action) {
			case "/main": contatos(request, response); break;
			case "/insert": novoContato(request, response); break;
			case "/select": consultaContato(request, response); break;
			case "/update": editarContato(request, response); break;
			case "/delete": excluirContato(request, response); break;
			default: response.sendRedirect("index.html");
		}
	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Criando um objeto que ir� receber a lista de contato
		ArrayList<JavaBeans> lista = dao.listarContatos();
		
		// encaminhar lista para agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	// Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		
		dao.inserirContato(contato);
		response.sendRedirect("main");
	}
	
	// Consultar contato 
	protected void consultaContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		contato.setId(id);
		dao.buscarContatoPorId(contato);

		request.setAttribute("id", contato.getId());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("telefone", contato.getTelefone());
		request.setAttribute("email", contato.getEmail());

		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	// Editar contato
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setId(request.getParameter("id"));
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));

		dao.atualizarContato(contato);
		response.sendRedirect("main");
	}
	
	// Excluir contato
	protected void excluirContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setId(request.getParameter("id"));

		dao.excluirContato(contato);
		response.sendRedirect("main");
	}
}