package AppComics.model;

import java.util.List;

public class Coleccion {
	protected String titulo;
	protected String creador;
	protected int total_paginas;
	protected List<Comic> comics;
	protected boolean leido;

	public Coleccion(String titulo, String creador, List<Comic> comics) {
		super();
		this.titulo = titulo;
		creador = creador;
		this.comics = comics;
		this.total_paginas = totalpaginas(comics);
		// por defecto se pone que no has leido la coleccion
		this.leido = false;
	}

	public Coleccion() {
		super();
		this.titulo = "Desconocido";
		creador = "Desconocido";
		this.comics = null;
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
		creador = creador;
	}

	public int getTotal_paginas() {
		return total_paginas;
	}

	public void setTotal_paginas(int total_paginas) {
		this.total_paginas = total_paginas;
	}

	public List<Comic> getComics() {
		return comics;
	}

	public void setComics(List<Comic> comics) {
		this.comics = comics;
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

	@Override
	public String toString() {
		return "Saga [titulo=" + titulo + ", Creador=" + creador + ", total_paginas=" + total_paginas + ", comics="
				+ comics + ", leido=" + leido(isLeido()) + "]";
	}

}
