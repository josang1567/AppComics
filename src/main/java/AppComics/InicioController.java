package AppComics;

import java.io.IOException;
import javafx.fxml.FXML;

public class InicioController {

	

	// ir a todas las colecciones
	@FXML
	private void switchToColecciones() throws IOException {
		App.setRoot("secondary");
	}

	// ir a todos los autores
	@FXML
	private void switchToAutores() throws IOException {
		App.setRoot("AutorScene");
	}

	// ir a los comics
	@FXML
	private void switchToComics() throws IOException {
		App.setRoot("primary");
	}
}
