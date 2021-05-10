package AppComics;
import java.io.IOException;
import AppComics.model.AutorDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class CrearAutorController {
	static AutorDAO au= new AutorDAO();
	@FXML
	private TextArea nombretext;
	@FXML
	private TextArea DescripcionText;
	
	
	@FXML
	private void cancelar() throws IOException{
		App.setRoot("AutorScene");
	}
	
	@FXML
	private void guardarautor() throws IOException{
		if (!validarFormulario()) 
			return;
		
		au.setNombre(nombretext.getText());
		au.setDescripcion(DescripcionText.getText());
		au.guardar();
		mostrarAlertInfo();
		App.setRoot("AutorScene");
	}
	
	
	private boolean validarFormulario() {
		boolean result = true;

		// comprueba que el titulo no este vacio
		if (nombretext.getText().trim().equals("")) {
			result = false;
			mostrarAlert("El Nombre del autor está vacío");
		}		

		// comprueba que el codigo de la coleccion no este vacio y sea correcto
		if (DescripcionText.getText().trim().equals("")) {
			result = false;
			mostrarAlert("La descripcion del autor esta vacia");
		}

		

		return result;
	}
	
	private void mostrarAlertInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Autor guardado");
		alert.showAndWait();
	}

	private void mostrarAlert(String error) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Alert");
		alert.setContentText("Rellene todos los campos: " + error);
		alert.showAndWait();
	}

	
}
