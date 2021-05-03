package AppComics.model;

public class Comic {

	protected String Titulo;
	protected int Num_paginas;
	protected boolean Leido;
	protected String codigo;
	protected String codigo_coleccion;
 
	
	
	public Comic(String titulo, int num_paginas, boolean leido, String codigo, String codigo_coleccion) {
		super();
		Titulo = titulo;
		Num_paginas = num_paginas;
		Leido = leido;
		this.codigo = codigo;
		this.codigo_coleccion = codigo_coleccion;
	}

	
	
	public Comic() {
		Titulo = "Desconocido";
		Num_paginas = 0;
		Leido = false;
		this.codigo = "00000";
		this.codigo_coleccion = "00000";
	}



	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public int getNum_paginas() {
		return Num_paginas;
	}

	public void setNum_paginas(int num_paginas) {
		Num_paginas = num_paginas;
	}

	public boolean isLeido() {
		return Leido;
	}

	public void setLeido(boolean leido) {
		Leido = leido;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo_coleccion() {
		return codigo_coleccion;
	}

	public void setCodigo_coleccion(String codigo_coleccion) {
		this.codigo_coleccion = codigo_coleccion;
	}



	/***
	 * revisa el boolean de leido y si es false lo cambia por un no y si es true por
	 * un si
	 */

	public String leido(boolean leido) {
		String estadolectura = "";
		if (leido == false) {
			estadolectura = "No leido";
		} else {
			estadolectura = "Leido";
		}
		return estadolectura;
	}

}
