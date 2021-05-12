package AppComics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import AppComics.model.ComicDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import AppComics.model.Coleccion;
import AppComics.model.ColeccionDAO;
import AppComics.model.Comic;

public class CrearComicController {

	static List<Comic> todoscomic = ComicDao.mostrartodos();
	static ComicDao c = new ComicDao();
	@FXML
	private TextArea titulotext;
	

	@FXML
	private ChoiceBox<String> leidobox;
	ObservableList<String> leido = FXCollections.observableArrayList("Leido", "No leido");
	@FXML
	private ComboBox<String> coleccionbox;

	@FXML
	private ChoiceBox<String> localizacionbox;
	ObservableList<String> localizacion = FXCollections.observableArrayList("En casa", "Prestado");
	@FXML
	private ChoiceBox<String> propiedadbox;
	ObservableList<String> propiedad = FXCollections.observableArrayList("En posesion", "Añadir a lista de deseos");

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
		todoscomic=ComicDao.mostrartodos();
		List<Coleccion> todascoleccion = ColeccionDAO.buscartodos();
		ObservableList<String> Listatitulos = FXCollections.observableArrayList(nombrescolecciones(todascoleccion));

		leidobox.setValue(leido.get(0));
		leidobox.setItems(leido);
		localizacionbox.setValue(localizacion.get(0));
		localizacionbox.setItems(localizacion);
		propiedadbox.setValue(propiedad.get(0));
		propiedadbox.setItems(propiedad);
		tapasbox.setValue(tapas.get(0));
		tapasbox.setItems(tapas);
		tiposbox.setValue(tipos.get(0));
		tiposbox.setItems(tipos);
		coleccionbox.setValue(Listatitulos.get(0));
		coleccionbox.setItems(Listatitulos);
		if (Listatitulos.size() > 3) {
			coleccionbox.setVisibleRowCount(3);
		}

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
		c.setLeido(cambiochoice(leidobox));
		c.setTitulo_coleccion(coleccionbox.getValue());
		c.setLocalizacion(cambiochoice(localizacionbox));
		c.setPropiedad(cambiochoice(propiedadbox));
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

	private ArrayList<String> nombrescolecciones(List<Coleccion> c) {
		ArrayList<String> titulos = new ArrayList<String>();
		for (int i = 0; i < c.size(); i++) {
			titulos.add(c.get(i).getTitulo());
		}
		return titulos;
	}

	private boolean cambiochoice(ChoiceBox<String> tipo) {
		boolean result = false;
		if (tipo.getValue() == localizacion.get(0)) {
			// true==en casa
			result = true;
		} else if (tipo.getValue() == localizacion.get(1)) {
			// false== prestado
			result = false;
		} else if (tipo.getValue() == leido.get(0)) {
			// true == leido
			result = true;
		} else if (result) {
			// false== no leido
			result = false;
		} else if (tipo.getValue() == propiedad.get(0)) {
			// true==en posesion
			result = true;
		} else if (tipo.getValue() == propiedad.get(1)) {
			// false==en lista de deseos
			result = false;
		}

		return result;
	}

	
}