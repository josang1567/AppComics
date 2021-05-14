package AppComics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AppComics.model.Autor;
import AppComics.model.AutorDAO;
import AppComics.model.ColeccionDAO;
import AppComics.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class CrearColeccionController {
	static String direccionurl = "src/main/resources/imagenes/Colecciones/";

	static ColeccionDAO cd = new ColeccionDAO();
	@FXML
	private TextArea titulotext;
	@FXML
	private ComboBox<String> NombresAutor;
	@FXML
	private ImageView portadaimage;
	@FXML
	private TextField urltext;
	@FXML
	private Button verimagen;

	@FXML
	private void initialize() {
		List<Autor> listaautores = AutorDAO.mostrartodos();
		ObservableList<String> Autores = FXCollections.observableArrayList(nombresautores(listaautores));
		NombresAutor.setItems(Autores);
		if (Autores.size() > 2) {
			NombresAutor.setVisibleRowCount(2);
		}
	}

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
		cd.setnombre_autor(NombresAutor.getValue());
		Utils.saveImage(urltext.getText(), direccionurl + cd.getTitulo() + ".jpg");
		cd.setUrlImagen(direccionurl + cd.getTitulo() + ".jpg");
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

	@FXML
	private void selecImagen() {
		File file = null;
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Selecionar imagen...");
		try {
			file = filechooser.showOpenDialog(null);
			if (file != null && file.getPath().matches(".+\\.png") || file.getPath().matches(".+\\.jpg")) {
				Image img = new Image("file:\\" + file.getPath());
				portadaimage.setImage(img);
				urltext.setText(file.getPath());
			} else { // si la extension es incorrecta sale esta alerta
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Información");
				alert.setContentText("Formato incorrecto: Debe elegir un tipo de archivo jpg o png.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			// TODO: handle exception;
		}
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

	private ArrayList<String> nombresautores(List<Autor> a) {
		ArrayList<String> nombres = new ArrayList<String>();
		for (int i = 0; i < a.size(); i++) {
			nombres.add(a.get(i).getNombre());
		}
		return nombres;
	}
}
