package AppComics.model;


public class Coleccion {
	protected String titulo;
	protected String nombre_autor;
	

	
	
	public Coleccion(String titulo, String nombres_autor) {
		super();
		this.titulo = titulo;
		this.nombre_autor = nombres_autor;
	}

	

	public Coleccion() {
		super();
		this.titulo = "Desconocido";
		this.nombre_autor = "Desconocido";
		
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
