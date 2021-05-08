package AppComics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import AppComics.model.ComicDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
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
	private CheckBox leidobox;
	@FXML
	private ChoiceBox<String> coleccionbox;

	ObservableList<String> Listatitulos = FXCollections.observableArrayList(nombrescolecciones(todascoleccion));
	@FXML
	private CheckBox localizacionbox;
	@FXML
	private CheckBox propiedadbox;
	
	@FXML
	private ChoiceBox<String> tapasbox;

	ObservableList<String> tapas = FXCollections.observableArrayList("dura", "blanda");
	@FXML
	private ChoiceBox<String> tiposbox;

	ObservableList<String> tipos = FXCollections.observableArrayList("comic", "manga", "manwha", "novela grafica");

	@FXML
	private void cancelar() throws IOException {
		App.setRoot("primary");
	}

	@FXML
	private void initialize() {
		tapasbox.setItems(tapas);
		tiposbox.setItems(tipos);
		coleccionbox.setItems(Listatitulos);
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
		c.setLeido(leidobox.isSelected());
		c.setTitulo_coleccion(coleccionbox.getValue());
		c.setLocalizacion(localizacionbox.isSelected());
		c.setPropiedad(propiedadbox.isSelected());
		c.setTipo(tiposbox.getValue());
		c.setTapa(tapasbox.getValue());

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


	private void mostrarAlert3(String error) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Recomendacion");

		alert.setContentText("Si usas el codigo: " + error + " el comic se sobreescribira");
		alert.showAndWait();
	}

	private ArrayList<String> nombrescolecciones(List<Coleccion> c) {
		ArrayList<String> titulos = new ArrayList<String>();
		for (int i = 0; i < c.size(); i++) {
			System.out.println(c.get(i).getTitulo());
			titulos.add(c.get(i).getTitulo());
		}
		return titulos;
	}
}