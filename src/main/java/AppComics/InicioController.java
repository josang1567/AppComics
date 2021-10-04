package AppComics;

import java.io.File;
import java.io.IOException;

import AppComics.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InicioController {
	// ir a todas las colecciones
	@FXML
	private ImageView portada;

	@FXML
	private void switchToColecciones() throws IOException {
		Utils.tipopestaña = "todos";
		App.setRoot("secondary");
	}

	// ir a todos los autores
	@FXML
	private void switchToAutores() throws IOException {
		App.setRoot("AutorScene");
	}

	// ir a todos comics
	@FXML
	private void switchToComics() throws IOException {
		Utils.tipopestaña = "todos";
		App.setRoot("primary");
	}

	// ir a los comics en propiedad
	@FXML
	private void switchToPropiedad() throws IOException {
		Utils.tipopestaña = "propiedad";
		App.setRoot("primary");
	}

	// ir a los comics en lista de deseos
	@FXML
	private void switchToDeseos() throws IOException {
		Utils.tipopestaña = "deseos";
		App.setRoot("primary");
	}

	// ir a los comics en casa
	@FXML
	private void switchToCasa() throws IOException {
		Utils.tipopestaña = "casa";
		App.setRoot("primary");
	}

	// ir a los comics prestados
	@FXML
	private void switchToPrestado() throws IOException {
		Utils.tipopestaña = "prestado";
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

	protected void initialize() {

		String direccion = "src/main/resources/Imagenes/Logo/";
		portada.setVisible(true);
		File f = new File("file:" + direccion+"logo.png");
		System.out.println(direccion);
		Image cportada = new Image(f.getPath());
		portada.setImage(cportada);
	}
}
