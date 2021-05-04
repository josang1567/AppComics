package AppComics;

import java.io.IOException;


import AppComics.model.ComicDao;
import javafx.fxml.FXML;

public class CrearComicController {
	static ComicDao c = new ComicDao();
	
	
	
	// guardar los datos introducidos y volver a la pagina original
	@FXML
	private void switchToComics() throws IOException {
		
		
		
		c.setTitulo(null);
		c.setNum_paginas(0);
		c.setLeido(false);
		c.setCodigo(null);
		c.setCodigo_coleccion(null);
		c.guardar();
		
		
		App.setRoot("primary");
	}

	

}