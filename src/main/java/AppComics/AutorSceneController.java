package AppComics;

import java.io.IOException;
import java.util.List;

import AppComics.model.Autor;
import AppComics.model.AutorDAO;
import AppComics.model.Coleccion;
import AppComics.model.ColeccionDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AutorSceneController {

	@FXML
	private Label NombreLabel;
	@FXML
	private Label EdadLabel;
	@FXML
	
	private Label DniLabel;
	@FXML
	private Label DescripcionLabel;
	@FXML
	private Label ObrasLabel;

	@FXML
	private TableView<Autor> tablaAutores;
	@FXML
	private TableColumn<Autor, String> dniColumna;
	@FXML
	private TableColumn<Autor, String> nombreColumna;

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
	private void switchtocrear() throws IOException{
		App.setRoot("CrearAutorScene");
	}

	@FXML
	protected void initialize() {
		System.out.println("Cargando...");
		muestraInfo(null);
		configuraTabla();
		// Cargar de la base de datos!!!!!
		List<Autor> todos = AutorDAO.mostrartodos();
		tablaAutores.setItems(FXCollections.observableArrayList(todos));
		tablaAutores.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestraInfo(newValue);
		});
	}

	private void configuraTabla() {
		dniColumna.setCellValueFactory(cadapersona -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadapersona.getValue().getDni());
			return v;
		});
		nombreColumna.setCellValueFactory(cadapersona -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadapersona.getValue().getNombre());
			return v;
		});
	}

	private void muestraInfo(Autor a) {
		if (a != null) {

			if (DniLabel != null) {
				DniLabel.setText(a.getDni());
			} else {
				DniLabel.setText("Desconocido");
			}
			if (NombreLabel != null) {
				NombreLabel.setText(a.getNombre());
			} else {
				NombreLabel.setText("Desconocido");
			}
			if (EdadLabel != null) {
				EdadLabel.setText(a.getEdad() + "");
			} else {
				EdadLabel.setText("Desconocido");
			}
			if (DescripcionLabel != null) {
				DescripcionLabel.setText(a.getDescripcion());
			} else {
				DescripcionLabel.setText("No hay informacion");
			}
			if (ObrasLabel == null) {
				ObrasLabel.setText(mostrarobras(a));
			} /*else {
				ObrasLabel.setText("Desconocidas");
			}*/			
		} else {
			DniLabel.setText("Desconocido");
			NombreLabel.setText("Desconocido");
			EdadLabel.setText("Desconocido");
			DescripcionLabel.setText("No hay informacion");
			ObrasLabel.setText("Desconocidas");
		}
	}

	private static String mostrarobras(Autor a) {
		List<Coleccion> cd= ColeccionDAO.buscartodos();
		String listas="";
		for (int i = 0; i < cd.size(); i++) {
			if (cd.get(i).getCreador()==a.getNombre()) {
				listas+=cd.get(i).getTitulo()+"\n";
			}
		}
		return listas;

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
	private void infoautores() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("En esta pagina se muestra la informacion de los distintos autores");
		alert.showAndWait();
	}

	@FXML
	private void comoEditar() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Editar");
		alert.setContentText("Para editar los datos de una coleccion ya existente debes ir a crear "
				+ "y si el codigo coincide con una ya existente se sobreescribira encima.");
		alert.showAndWait();
	}
}
