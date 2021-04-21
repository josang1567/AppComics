package AppComics.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Autor {
	protected String nombre;
	protected int edad;
	protected String dni;
	protected LocalDateTime fecha_nacimiento;
	protected String Descripcion;
	protected List<Coleccion> obras;

	public Autor(String nombre, int edad, String dni, LocalDateTime fecha_nacimiento, String descripcion,
			List<Coleccion> obras) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
		this.fecha_nacimiento = fecha_nacimiento;
		Descripcion = descripcion;
		this.obras = obras;
	}

	public Autor() {
		super();
		this.nombre = "Desconocido";
		this.edad = 0;
		this.dni = "desconocido";
		this.fecha_nacimiento = LocalDateTime.now();
		Descripcion = "Desconocido";
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

	public LocalDateTime getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDateTime fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
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

	@Override
	public String toString() {
		return "Autor [nombre=" + nombre + ", edad=" + edad + ", dni=" + dni + ", fecha_nacimiento=" + fecha_nacimiento
				+ ", Descripcion=" + Descripcion + ", obras=" + obras + "]";
	}
	
	
	
}
