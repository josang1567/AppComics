package AppComics;

import java.io.IOException;
import java.util.List;

import AppComics.model.Autor;
import AppComics.model.AutorDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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

	@FXML
    private void switchTocolecciones() throws IOException {
        App.setRoot("secondary");
    }
	
	@FXML
	protected void initialize() {
		System.out.println("Cargando...");
		muestraInfo(null);
		configuraTabla();
		// Cargar de la base de datos!!!!!
		List<Autor> todos = AutorDAO.listartodos();
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
			DniLabel.setText(a.getDni());
			NombreLabel.setText(a.getNombre());
			DescripcionLabel.setText(a.getDescripcion());
			ObrasLabel.setText(a.getObras() + "");
		} else {
			DniLabel.setText("");
			NombreLabel.setText("");
			DescripcionLabel.setText("");
			ObrasLabel.setText( "");
		}
	}
}
