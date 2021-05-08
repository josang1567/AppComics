package AppComics;

import java.io.IOException;
import java.util.List;
import AppComics.model.ComicDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import AppComics.model.Coleccion;
import AppComics.model.ColeccionDAO;
import AppComics.model.Comic;

public class CrearComicController {
	static List<Coleccion> todascoleccion = ColeccionDAO.buscartodos();
	static List<Comic> todoscomic = ComicDao.mostrartodos();
	static ComicDao c = new ComicDao();
	@FXML
	private TextArea titulotext;
	@FXML
	private TextArea numeropaginastext;
	@FXML
	private CheckBox leidobox;
	@FXML
	private TextArea titulocolecciontext;
	@FXML
	private CheckBox localizacionbox;
	@FXML
	private CheckBox propiedadbox;
	@FXML
	private TextArea tapatext;
	@FXML
	private TextArea tipotext;
	

	@FXML
	protected void initialize() {
		numeropaginastext.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					numeropaginastext.setText(newValue.replaceAll("[^\\d]", ""));
				}

			}
		});
	}

	@FXML
	private void cancelar() throws IOException{
		App.setRoot("primary");
	}
	
	// guardar los datos introducidos y volver a la pagina original
	@FXML
	private void switchToComics() throws IOException {

		// asigna los valores a el nuevo comic
		if (!validarFormulario())
			return;
		// si el validar fomulario da false esto que sigue no se ejecuta porque el
		// método acaba con el return

		c.setTitulo(titulotext.getText());
		c.setNum_paginas(Integer.parseInt(numeropaginastext.getText()));
		c.setLeido(leidobox.isSelected());
		c.setTitulo_coleccion(titulocolecciontext.getText());
		c.setLocalizacion(localizacionbox.isSelected());
		c.setPropiedad(propiedadbox.isSelected());
		c.setTipo(tipotext.getText());
		c.setTapa(tapatext.getText());

		// guarda el comic en la base de datos
		try {
			c.guardar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR AL GUARDAR");
			e.printStackTrace();
		}

		// muestra un mensaje de que se ha guardado correctamente
		mostrarAlertInfo();

		// vuelve a la escena de comic
		App.setRoot("primary");
	}

	private boolean validarFormulario() {
		boolean result = true;

		// comprueba que el titulo no este vacio
		if (titulotext.getText().trim().equals("")) {
			result = false;
			mostrarAlert("Titulo");
		}

		// comprueba que el numero de paginas no este vacio y sea mayor que 0
		if (numeropaginastext.getText().trim().equals("")) {
			result = false;
			mostrarAlert("Numero de páginas");
		} else if (Integer.valueOf(numeropaginastext.getText()) <= 0) {
			result = false;
			mostrarAlert("El numero de páginas debe ser mayor que 0");
		}

		// comprueba que el codigo de la coleccion no este vacio y sea correcto
		if (titulocolecciontext.getText().trim().equals("")) {
			result = false;
			mostrarAlert("titulo de coleccion");
		}
		//comprueba que el tipo de tapa de no esta vacio
		if(tapatext.getText().trim().equals("")) {
			result=false;
			mostrarAlert("tipo de tapa");
		}
		//comprueba que el tipo de comic no este vacio
		if(tipotext.getText().trim().equals("")) {
			result=false;
			mostrarAlert("tipo de comic");
		}

		return result;
	}

	private void mostrarAlertInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Comic guardado");
		alert.showAndWait();
	}

	private void mostrarAlert(String error) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Alert");
		alert.setContentText("Rellene todos los campos: " + error);
		alert.showAndWait();
	}

	private void mostrarAlert2(String error) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Recomendacion");
		alert.setContentText("El codigo de la coleccion no pertenece a ninguna coleccion:");
		alert.setContentText("Las colecciones existentes son estas:\n " + error);
		alert.showAndWait();
	}

	private void mostrarAlert3(String error) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Recomendacion");

		alert.setContentText("Si usas el codigo: " + error + " el comic se sobreescribira");
		alert.showAndWait();
	}

}