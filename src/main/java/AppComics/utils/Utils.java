package AppComics.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static Object dato;
	
	public static boolean incluyepalabra(String palabra1, String palabra2) {
		boolean encontrado=false;
		Pattern pat = Pattern.compile(".*"+palabra1+".*");
		Matcher mat = pat.matcher(palabra2);
		if (mat.matches()) {
		encontrado=true;
		} else {
		encontrado=false;
		}

		return encontrado;
	}
}
