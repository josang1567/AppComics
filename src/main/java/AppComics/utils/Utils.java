package AppComics.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static Object dato;
	public static String tipopestaña="";
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
	public static void saveImage(String filePath, String destinationPath) {
        InputStream input = null;
        OutputStream output = null;
        try {
            try {
                input = new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                output = new FileOutputStream(destinationPath);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                output.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
