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
		put(4, "perro");	
		put(5, "elefante");
		put(6, "hormiga");	
		put(7, "rinoceronte");
		put(8, "mamut");

	}}; 
	private int aleatorio;
    public Ahorcado() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String[] limpia,estado,original=null;
		String originalStr;
		aleatorio = (int) ((Math.random() * ( animales.size() - 1 )) + 1);
		int vidas=10;
		ArrayList<String> erroneas = new ArrayList<String>();
		originalStr=animales.get(aleatorio);
		original=animales.get(aleatorio).split("");

		limpia=Comprobar.clean(animales.get(aleatorio)).split("");
		estado=new String[original.length];
		
		HttpSession laSesion= request.getSession(false);  //carga la sessi�n si existe, devuelve null sino
		if(laSesion != null) {
			if (request.getParameter("empezar") != null) {  // se ha recibido el par�metro empezar
				laSesion.invalidate();
			}
		}
		laSesion= request.getSession(false); 
		if(laSesion != null) {
			if (laSesion.getAttribute("vidas") != null) {
				vidas = (int) laSesion.getAttribute("vidas");
			}
			if (laSesion.getAttribute("aleatorio") != null) {
				aleatorio = (int) laSesion.getAttribute("aleatorio");
			}
			if (laSesion.getAttribute("estado") != null) {
				estado = (String[]) laSesion.getAttribute("estado");
			}
			if (laSesion.getAttribute("erroneas") != null) {
				erroneas = (ArrayList<String>) laSesion.getAttribute("erroneas");
			}
			limpia=Comprobar.clean(originalStr).split("");
		}else{
			laSesion= request.getSession(true);
			laSesion.setAttribute("estado", estado);
			laSesion.setAttribute("vidas", vidas);
			laSesion.setAttribute("aleatorio", aleatorio);
			laSesion.setAttribute("erroneas", erroneas);
		
		}
		request.setAttribute("estado",estado);
		request.setAttribute("vidas",vidas);
		request.setAttribute("erroneas", erroneas);
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
        dispatcher.forward(request, response); 
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String[] limpia=null,estado=null,original=null;
		int vidas=0;
		ArrayList<String> erroneas = new ArrayList<String>();
		
		
		
		String letra=Comprobar.clean(request.getParameter("letra").toLowerCase());
		System.out.println(request.getParameter("letra"));
		System.out.println(letra);
		String exp="[a-z]{1}";
//		Pattern.matches(exp,"g")
		HttpSession laSesion= request.getSession(false);  //carga la sessi�n si existe, devuelve null sino
		if(laSesion != null) {
			if (laSesion.getAttribute("vidas") != null) {
				vidas = (int) laSesion.getAttribute("vidas");
			}
			if (laSesion.getAttribute("aleatorio") != null) {
				aleatorio = (int) laSesion.getAttribute("aleatorio");
			}
			if (laSesion.getAttribute("estado") != null) {
				estado = (String[]) laSesion.getAttribute("estado");
			}
			if (laSesion.getAttribute("erroneas") != null) {
				erroneas = (ArrayList<String>) laSesion.getAttribute("erroneas");
			}
			original=animales.get(aleatorio).split("");
			limpia=Comprobar.clean(animales.get(aleatorio)).split("");
			
			
			if(letra!=null) {
				//si letra no esta en la lista de erroneas y cumple que es una letra sola
				if(Pattern.matches(exp,letra) && !erroneas.contains(letra)) {
					//comprobamos que la letra
					if(Comprobar.comLetra(limpia, letra)) {
				    	for(int i=0;i<limpia.length;i++) {
				    		if(limpia[i].equals(letra)) {
				    			estado[i]=original[i];
				    		}
				    	}
					}else {
						erroneas.add(letra);
						vidas--;
						System.out.println(letra);
					}
				}
			}else{
				
			}
			//guardamos parametros en la sesion
			laSesion.setAttribute("estado", estado);
			laSesion.setAttribute("vidas", vidas);
			laSesion.setAttribute("erroneas", erroneas);
			
			//pasamos parametros a jsp
			request.setAttribute("estado",estado);
			request.setAttribute("vidas",vidas);
			request.setAttribute("erroneas", erroneas);
		}
		

		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
        dispatcher.forward(request, response); 
		//		doGet(request, response);
	}

}
