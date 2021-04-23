package AppComics.model;

public class Comic {

	protected String Titulo;
	protected int Num_paginas;
	protected boolean Leido;

	protected String codigo;

	public Comic(String titulo, int num_paginas, boolean leido, String codigo) {
		super();
		Titulo = titulo;
		Num_paginas = num_paginas;
		Leido = leido;
		this.codigo = codigo;
	}

	public Comic() {
		super();
		Titulo = "Desconocido";
		Num_paginas = 0;
		Leido = false;

		this.codigo = "Desconocido";
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
