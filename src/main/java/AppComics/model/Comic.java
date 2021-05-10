package AppComics.model;

public class Comic {

	protected String Titulo;
	protected boolean Leido;
	protected String titulo_coleccion;
	protected boolean localizacion;
	protected boolean propiedad;
	protected String tapa;
	protected String tipo;

	
	
	public Comic(String titulo, boolean leido, String titulo_coleccion, boolean localizacion,
			boolean propiedad, String tapa, String tipo) {
		super();
		Titulo = titulo;
		Leido = leido;
		this.titulo_coleccion = titulo_coleccion;
		this.localizacion = localizacion;
		this.propiedad = propiedad;
		this.tapa = tapa;
		this.tipo = tipo;
	}

	public Comic() {
		this.Titulo = "Desconocido";
		this.Leido = false;
		this.titulo_coleccion = "00000";
		this.localizacion=true;
		this.propiedad=false;
		this.tapa="blanda";
		this.tipo="comic";
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	

	public boolean isLeido() {
		return Leido;
	}

	public void setLeido(boolean leido) {
		Leido = leido;
	}

	public String getTitulo_coleccion() {
		return titulo_coleccion;
	}

	public void setTitulo_coleccion(String titulo_coleccion) {
		this.titulo_coleccion = titulo_coleccion;
	}

	public boolean isLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(boolean localizacion) {
		this.localizacion = localizacion;
	}

	public boolean isPropiedad() {
		return propiedad;
	}

	public void setPropiedad(boolean propiedad) {
		this.propiedad = propiedad;
	}

	public String getTapa() {
		return tapa;
	}

	public void setTapa(String tapa) {
		this.tapa = tapa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/***
	 * revisa el boolean de leido y si es false lo cambia por un no y si es true por
	 * un si
	 */

	public String leido(boolean leido) {
		String estadolectura = "";
		if (leido == false) {
			estadolectura = "No leido";
		} else if(leido==true){
			estadolectura = "Leido";
		}
		return estadolectura;
	}
	public String localizacion(boolean localizacion) {
		String estado="";
		if(localizacion==true) {
			estado="En casa";
		}else {
			estado="Prestado";
		}
		return estado;
	}
	
	public String propiedad(boolean propiedad) {
		String estado="";
		if(propiedad==true) {
			estado="En posecion";
		}else if(propiedad==false) {
			estado="En Lista de deseos";
		}
		return estado;
	}

}
