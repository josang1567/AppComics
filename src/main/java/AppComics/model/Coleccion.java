package AppComics.model;

import java.util.List;

public class Coleccion {
	protected String titulo;
	protected String creador;
	protected String codigo;
	protected int total_paginas;
	protected boolean leido;

	
	
	public Coleccion(String titulo, String creador, String codigo, int total_paginas, boolean leido) {
		super();
		this.titulo = titulo;
		this.creador = creador;
		this.codigo = codigo;
		this.total_paginas = total_paginas;
		this.leido = leido;
	}

	

	public Coleccion() {
		super();
		this.titulo = "Desconocido";
		this.creador = "Desconocido";
		this.codigo="00000";
		this.total_paginas = 0;
		this.leido = false;
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

	public int getTotal_paginas() {
		return total_paginas;
	}

	public void setTotal_paginas(int total_paginas) {
		this.total_paginas = total_paginas;
	}


	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	/***
	 * recorre la lista de comics y calcula el total de paginas
	 * 
	 * @param comics
	 * @return
	 */
	public int totalpaginas(List<Comic> comics) {
		int total = 0;
		for (Comic comic : comics) {
			total += comic.getNum_paginas();
		}

		return total;
	}

	/***
	 * revisa el boolean de leido y si es false lo cambia por un no y si es true por
	 * un si
	 */
	public String leido(boolean leido) {
		String estadolectura = "";
		if (leido == false) {
			estadolectura = "No Leido";
		} else {
			estadolectura = "Leido";
		}
		return estadolectura;
	}

	
}
