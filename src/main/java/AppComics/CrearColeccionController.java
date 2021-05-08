package AppComics;


import java.io.IOException;
import AppComics.model.ColeccionDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.scene.control.TextArea;

public class CrearColeccionController {
	static ColeccionDAO cd= new ColeccionDAO();
	@FXML
	private TextArea titulotext;
	@FXML
	private TextArea creadorText;
	
	
	
	
	
	
	@FXML
	private void cancelar() throws IOException {
		App.setRoot("secondary");
	}
	
	// guardar la coleccion nueva
	@FXML
	private void switchToColecciones() throws IOException {
		if (!validarFormulario())
			return;
		
		cd.setTitulo(titulotext.getText());
		cd.setCreador(creadorText.getText());
		cd.guardar();
		mostrarAlertInfo();
		App.setRoot("secondary");
	}	
	private boolean validarFormulario() {
		boolean result = true;

		// comprueba que el titulo no este vacio
		if (titulotext.getText().trim().equals("")) {
			result = false;
			mostrarAlert("El título está vacío");
		}
		

		return result;
	}

	
	private void mostrarAlertInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Coleccion guardada");
		alert.showAndWait();
	}

	private void mostrarAlert(String error) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Alert");
		alert.setContentText("Rellene todos los campos: " + error);
		alert.showAndWait();
	}

	private void mostrarAlert2(String error) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Recomendacion");
		
		alert.setContentText("Si usas el codigo: " + error + " la coleccion se sobreescribira");
		alert.showAndWait();
	}
}
