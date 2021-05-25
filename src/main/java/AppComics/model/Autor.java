package AppComics.model;


import java.util.ArrayList;
import java.util.List;

public class Autor {
	protected String nombre;
	protected String Descripcion;
	protected List<Coleccion> colecciones;

	

	public Autor(String nombre, String descripcion, List<Coleccion> colecciones) {
		super();
		this.nombre = nombre;
		this.Descripcion = descripcion;
		this.colecciones = new ArrayList<Coleccion>();
	}

	public Autor() {
		super();
		this.nombre = "desconocido";
		this.Descripcion = "desconocido";
		this.colecciones = new ArrayList<Coleccion>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public List<Coleccion> getColecciones() {
		return colecciones;
	}

	public void setColecciones(List<Coleccion> colecciones) {
		this.colecciones = colecciones;
	}


	

	
	
	
	
}
