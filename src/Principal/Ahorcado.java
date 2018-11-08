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
import Chekeo.Comprobar;


@WebServlet("/Ahorcado")
public class Ahorcado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String vista = "/ImpAhorcado.jsp";
	private Map<Integer, String> animales = new TreeMap<Integer,String>() {{
		put(1, "gorrión");
		put(2, "jurel");
		put(3, "gato");
		put(4, "perro");	
		put(5, "elefante");
		put(6, "hormiga");	
		put(7, "rinoceronte");
		put(8, "mamut");

	}}; 
	String[] limpia,solu;
    public Ahorcado() {
        super();
    }
    private void nuevaPartida() {
		int aleatorio = (int) ((Math.random() * ( 8 - 1 )) + 1);
		
		limpia=animales.get(aleatorio).split("");
		solu=new String[limpia.length];
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
