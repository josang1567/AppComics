package AppComics.model;


public class Coleccion {
	protected String titulo;
	protected String nombre_autor;
	protected String urlImagen;
	

	
	
	public Coleccion(String titulo, String nombres_autor, String urlImagen) {
		super();
		this.titulo = titulo;
		this.nombre_autor = nombres_autor;
		this.urlImagen=urlImagen;
	}

	

	public Coleccion() {
		super();
		this.titulo = "Desconocido";
		this.nombre_autor = "Desconocido";
		this.urlImagen="";
		
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


	
}
