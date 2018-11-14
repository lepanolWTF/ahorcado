package Chekeo;

import java.text.Normalizer;

public class Comprobar {
    public static String clean(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }
    
    public static String aTexto (String[] arr) {
    	String cad="";
    	for(int i=0; i<arr.length;i++) {
    		cad+=arr[i];
    	}
    	
    	return cad;
    }
    
    
    public static boolean comLetra(String[] limpia,String letra) {
    	for(int i=0;i<limpia.length;i++) {
    		if(limpia[i].equals(letra)) {
    			return true;
    		}
    	}
    	return false;
    }
}
