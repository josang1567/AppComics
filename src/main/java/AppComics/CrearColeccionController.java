package AppComics;

import java.awt.TextArea;
import java.io.IOException;
import javafx.fxml.FXML;

public class CrearColeccionController {

	// ir a todas las colecciones
	@FXML
	private void switchToColecciones() throws IOException {
		
		
		App.setRoot("secondary");
	}
	
	
	@FXML
	protected TextArea tituloArea;
	

}