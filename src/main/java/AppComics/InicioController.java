package AppComics;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

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
	@FXML
	private void ayuda() {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Visitar web");
	    alert.setContentText("https://www.google.es/?gws_rd=cr&ei=6b1BUqa_HoTZtAbMnIDgBg");
	    alert.showAndWait();
	}
	@FXML
	private void comousar() {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Usar app");
	    alert.setContentText("Para navegar por la aplicacion debes usar los paneles de arriba.");
	    alert.showAndWait();
	}
	
}
