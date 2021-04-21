package AppComics.model;

public class Comic {

	protected String Titulo;
	protected int Num_paginas;
	protected boolean Leido;
	protected String Colecccion;

	public Comic(String titulo, int num_paginas, boolean leido, String saga) {
		super();
		Titulo = titulo;
		Num_paginas = num_paginas;
		Leido = leido;
		this.Colecccion = saga;
	}

	public Comic() {
		super();
		Titulo = "Desconocido";
		Num_paginas = 0;
		Leido = false;
		this.Colecccion = "Desconocido";
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
		return Colecccion;
	}

	public void setSaga(String saga) {
		this.Colecccion = saga;
	}

}
