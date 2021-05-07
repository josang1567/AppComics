package AppComics;
import java.io.IOException;
import AppComics.model.AutorDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class CrearAutorController {
	static AutorDAO au= new AutorDAO();
	@FXML
	private TextArea nombretext;
	@FXML
	private TextArea edadtext;
	@FXML
	private TextArea dnitext;
	@FXML
	private TextArea DescripcionText;
	
	
	
	@FXML
	protected void initialize() {
		edadtext.textProperty().addListener(new ChangeListener<String>() {
		
		@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					edadtext.setText(newValue.replaceAll("[^\\d]", ""));
				}
				
			}
		});
	}
	@FXML
	private void guardarautor() throws IOException{
		if (!validarFormulario()) 
			return;
		
		au.setNombre(nombretext.getText());
		au.setDni(dnitext.getText());
		au.setEdad(Integer.parseInt(edadtext.getText()));
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
			mostrarAlert("El título está vacío");
		}

		// comprueba que el numero de paginas no este vacio y sea mayor que 0
		if (dnitext.getText().trim().equals("")) {
			result = false;
			mostrarAlert("El numero de páginas está vacío");
		} 

		// comprueba que el codigo del comic no este vacio
		if (edadtext.getText().trim().equals("")) {
			result = false;
			mostrarAlert("El codigo está vacío");
		}else if (Integer.valueOf(edadtext.getText()) <= 18) {
			result = false;
			mostrarAlert("La edad debe se mayor que  18");
		}
		

		// comprueba que el codigo de la coleccion no este vacio y sea correcto
		if (DescripcionText.getText().trim().equals("")) {
			result = false;
			mostrarAlert("El codigo de la coleccion está vacío");
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
		alert.setContentText("Si usas el codigo: " + error + " El autor se sobreescribira");
		alert.showAndWait();
	}
}
