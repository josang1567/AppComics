package AppComics.model;


import java.util.List;

public class Autor {
	protected String nombre;
	protected int edad;
	protected String dni;
	protected String Descripcion;
	protected List<Coleccion> obras;

	public Autor(String nombre, int edad, String dni, String descripcion,
			List<Coleccion> obras) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
		this.Descripcion = descripcion;
		this.obras = obras;
	}

	public Autor() {
		super();
		this.nombre = "Desconocido";
		this.edad = 0;
		this.dni = "desconocido";
		this.Descripcion = "Desconocido";
		this.obras = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public List<Coleccion> getObras() {
		return obras;
	}

	public void setObras(List<Coleccion> obras) {
		this.obras = obras;
	}

	
	
	
	
}
