  
package AppComics.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import AppComics.model.Autor;
import AppComics.model.Coleccion;
import AppComics.model.Comic;



public class SaveAndLoad {
	private static SaveAndLoad _instance;
	

	private SaveAndLoad() {
		super();
	}
	public static SaveAndLoad getSingletoonInstance() {
		if(_instance==null) {
			_instance=new SaveAndLoad();
		}
		return _instance;
	}

	public void saveComics(List<Comic> l) {
		if (l != null) {
			FileOutputStream f;

			try {
				f = new FileOutputStream("Comics.txt");
				ObjectOutputStream of = new ObjectOutputStream(f);
				of.writeObject(l);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public List<Comic> loadComics() throws ClassNotFoundException {
		List<Comic> result=null;
		
			FileInputStream f;
			try {
				f = new FileInputStream("Comics.txt");
				ObjectInputStream of = new ObjectInputStream(f);
				//repositoryClient.setClient((List<Client>) of.readObject());
				result=((List<Comic>) of.readObject());
				return result;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return result;

	}

	public void saveColecciones(List<Coleccion> l) {
		if (l != null) {
			FileOutputStream f;

			try {
				f = new FileOutputStream("Colecciones.txt");
				ObjectOutputStream of;
				of = new ObjectOutputStream(f);
				of.writeObject(l);

			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	public List<Coleccion> loadColeccions() {
		List<Coleccion> result=null;
			FileInputStream f;

			try {
				f = new FileInputStream("Colecciones.txt");
				ObjectInputStream of = new ObjectInputStream(f);
				result=(List<Coleccion>) of.readObject();

			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
		
	}

	public void saveAutores(List<Autor> l) {
		if (l != null) {
			FileOutputStream f;

			try {
				f = new FileOutputStream("Autores.txt");
				ObjectOutputStream of;
				of = new ObjectOutputStream(f);
				of.writeObject(l);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public List<Autor> loadAutor() {
		
			List<Autor> result=null;
			
			try {
				FileInputStream f=new FileInputStream("Autores.txt");
				ObjectInputStream of;
					of = new ObjectInputStream(f);
				result=(ArrayList<Autor>) of.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	

}
