package AppComics.model;

public class Comic {
	protected int numero_capitulos;
	protected String Titulo;
	protected int Num_paginas;
	protected boolean Leido;
	protected String saga;

	
	private Comic(int numero_capitulos, String titulo, int num_paginas, boolean leido, String saga) {
		super();
		this.numero_capitulos = numero_capitulos;
		Titulo = titulo;
		Num_paginas = num_paginas;
		Leido = leido;
		this.saga = saga;
	}

		
	private Comic() {
		super();
		this.numero_capitulos = 0;
		Titulo = "Desconocido";
		Num_paginas = 0;
		Leido = false;
		this.saga = "Desconocido";
	}



	public int getNumero_capitulos() {
		return numero_capitulos;
	}

	public void setNumero_capitulos(int numero_capitulos) {
		this.numero_capitulos = numero_capitulos;
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

	public String getSaga() {
		return saga;
	}

	public void setSaga(String saga) {
		this.saga = saga;
	}

}
