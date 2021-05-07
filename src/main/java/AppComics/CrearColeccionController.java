package AppComics;


import java.io.IOException;
import AppComics.model.ColeccionDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class CrearColeccionController {
	static ColeccionDAO cd= new ColeccionDAO();
	@FXML
	private TextArea titulotext;
	@FXML
	private TextArea creadorText;
	@FXML
	private TextArea codigotext;
	@FXML
	private TextArea total_paginastext;
	@FXML
	private CheckBox leidobox;
	
	
	@FXML
	protected void initialize() {
		total_paginastext.textProperty().addListener(new ChangeListener<String>() {
		
		@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					total_paginastext.setText(newValue.replaceAll("[^\\d]", ""));
				}
				/*if (!newValue.matches("\\d*")) {
					codigotext.setText(newValue.replaceAll("[^\\d]", ""));
				}*/
				
			}
		});
	}
	
	
	
	
	// guardar la coleccion nueva
	@FXML
	private void switchToColecciones() throws IOException {
		if (!validarFormulario())
			return;
		
		cd.setTitulo(titulotext.getText());
		cd.setCreador(creadorText.getText());
		cd.setTotal_paginas(Integer.parseInt(total_paginastext.getText()));
		cd.setCodigo(codigotext.getText());
		cd.setLeido(leidobox.isSelected());
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

		// comprueba que el numero de paginas no este vacio y sea mayor que 0
		if (total_paginastext.getText().trim().equals("")) {
			result = false;
			mostrarAlert("El numero de páginas está vacío");
		} else if (Integer.valueOf(total_paginastext.getText()) <= 1) {
			result = false;
			mostrarAlert("El numero de páginas debe ser mayor que 1");
		}

		// comprueba que el codigo del comic no este vacio
		if (codigotext.getText().trim().equals("")) {
			result = false;
			mostrarAlert("El codigo está vacío");
		}
		

		// comprueba que el codigo de la coleccion no este vacio y sea correcto
		if (codigotext.getText().trim().equals("")) {
			result = false;
			mostrarAlert("El codigo de la coleccion está vacío");
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
