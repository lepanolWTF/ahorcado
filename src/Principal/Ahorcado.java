package Principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Chekeo.Comprobar;


@WebServlet("/Ahorcado")
public class Ahorcado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String vista = "/ImpAhorcado.jsp";
	private Map<Integer, String> animales = new TreeMap<Integer,String>() {{
		put(1, "gorrión");
		put(2, "jurel");
		put(3, "gato");
//		put(4, "perro");	
//		put(5, "elefante");
//		put(6, "hormiga");	
//		put(7, "rinoceronte");
//		put(8, "mamut");

	}}; 
	private String[] limpia,estado,original;
	private int aleatorio,vidas=8;
	private ArrayList<String> erroneas = new ArrayList<String>();
	private boolean nuevo=true;
    public Ahorcado() {
        super();
    }
    private void nuevaPartida() {
		aleatorio = (int) ((Math.random() * ( animales.size() - 1 )) + 1);
		original=animales.get(aleatorio).split("");
		limpia=Comprobar.clean(animales.get(aleatorio)).split("");
		estado=new String[limpia.length];
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		HttpSession laSesion= request.getSession(false);  //carga la sessi�n si existe, devuelve null sino
		if(laSesion != null) {
			if (laSesion.getAttribute("vidas") != null) {
				vidas = (int) laSesion.getAttribute("vidas");
			}
			if (laSesion.getAttribute("original") != null) {
				original = (String[]) laSesion.getAttribute("original");
			}
			if (laSesion.getAttribute("estado") != null) {
				estado = (String[]) laSesion.getAttribute("estado");
			}
			if (laSesion.getAttribute("erroneas") != null) {
				erroneas = (ArrayList<String>) laSesion.getAttribute("erroneas");
			}
			limpia=Comprobar.clean(Comprobar.aTexto(original)).split("");
		}else{
			nuevaPartida();
			laSesion= request.getSession(true);
			laSesion.setAttribute("estado", estado);
			laSesion.setAttribute("vidas", vidas);
			laSesion.setAttribute("original", original);
		}
		request.setAttribute("estado",estado);
		request.setAttribute("vidas",vidas);
		request.setAttribute("erroneas", erroneas);
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
        dispatcher.forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String letra=request.getParameter("letra");
		String exp="[a-z]{1}";
//		Pattern.matches(exp,"g")
		HttpSession laSesion= request.getSession(false);  //carga la sessi�n si existe, devuelve null sino
		if(laSesion != null) {
			if (laSesion.getAttribute("vidas") != null) {
				vidas = (int) laSesion.getAttribute("vidas");
			}
			if (laSesion.getAttribute("original") != null) {
				original = (String[]) laSesion.getAttribute("original");
			}
			if (laSesion.getAttribute("estado") != null) {
				estado = (String[]) laSesion.getAttribute("estado");
			}
			if (laSesion.getAttribute("erroneas") != null) {
				erroneas = (ArrayList<String>) laSesion.getAttribute("erroneas");
			}
			limpia=Comprobar.clean(Comprobar.aTexto(original)).split("");
		}
		
		if(letra!=null) {
			//si letra no esta en la lista de erroneas y cumple que es una letra sola
			if(Pattern.matches(exp,letra) && !erroneas.contains(Comprobar.clean(letra).toLowerCase())) {
				if(Comprobar.comLetra(limpia, letra)) {
			    	for(int i=0;i<limpia.length;i++) {
			    		if(limpia[i].equals(letra)) {
			    			estado[i]=original[i];
			    		}
			    	}
				}else {
					erroneas.add(letra);
				}
			}
		}else{
			
		}
		
		request.setAttribute("estado",estado);
		request.setAttribute("vidas",vidas);
		request.setAttribute("erroneas", erroneas);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
        dispatcher.forward(request, response); 
		//		doGet(request, response);
	}

}
