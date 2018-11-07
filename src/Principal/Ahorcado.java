package Principal;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Ahorcado")
public class Ahorcado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String vista = "/ImpAhorcado.jsp";
	private Map<Integer, String> usuariosArr = new TreeMap<Integer,String>() {{
		put(1, "barcos");
		put(2, "transatlantico");
		put(3, "barcos");
		put(4, "transformer");	
		put(5, "elefante");
		put(6, "");	
		put(7, "barcos");
		put(8, "transatlantico");	

	}}; 
    public Ahorcado() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
        dispatcher.forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
