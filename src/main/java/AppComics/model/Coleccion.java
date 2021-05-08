package AppComics.model;

import java.util.List;

public class Coleccion {
	protected String titulo;
	protected String creador;
	

	
	
	public Coleccion(String titulo, String creador) {
		super();
		this.titulo = titulo;
		this.creador = creador;
	}

	

	public Coleccion() {
		super();
		this.titulo = "Desconocido";
		this.creador = "Desconocido";
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}


	
}
