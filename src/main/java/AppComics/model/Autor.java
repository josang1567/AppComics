package AppComics.model;


import java.util.List;

public class Autor {
	protected String nombre;
	protected String Descripcion;
	

	public Autor(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.Descripcion = descripcion;
	
	}

	public Autor() {
		super();
		this.nombre = "Desconocido";
		this.Descripcion = "Desconocido";
		
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

	

	
	
	
	
}
