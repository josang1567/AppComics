package AppComics.model;

import java.util.List;

public class Coleccion {
	protected String titulo;
	protected String nombre_autor;
	protected String urlImagen;
	protected Autor autor;
	protected List<Comic> comics;
	
	

	public Coleccion(String titulo, String nombre_autor, String urlImagen) {
		super();
		this.titulo = titulo;
		this.nombre_autor = nombre_autor;
		this.urlImagen = urlImagen;
	}



	public Coleccion() {
		super();
		this.titulo = "";
		this.nombre_autor = "";
		this.urlImagen = "";
	}



	public String getUrlImagen() {
		return urlImagen;
	}



	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getnombre_autor() {
		return nombre_autor;
	}

	public void setnombre_autor(String nombres_autor) {
		this.nombre_autor = nombres_autor;
	}



	public String getNombre_autor() {
		return nombre_autor;
	}



	public void setNombre_autor(String nombre_autor) {
		this.nombre_autor = nombre_autor;
	}



	public Autor getAutor() {
		return autor;
	}



	public void setAutor(Autor autor) {
		this.autor = autor;
	}



	public List<Comic> getComics() {
		return comics;
	}



	public void setComics(List<Comic> comics) {
		this.comics = comics;
	}



	@Override
	public String toString() {
		return "Coleccion [titulo=" + titulo + ", nombre_autor=" + nombre_autor + ", urlImagen=" + urlImagen
				+ ", autor=" + autor + "]";
	}


	
}
