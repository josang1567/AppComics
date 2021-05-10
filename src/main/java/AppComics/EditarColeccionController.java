package AppComics;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AppComics.model.Autor;
import AppComics.model.AutorDAO;
import AppComics.model.Coleccion;
import AppComics.model.ColeccionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

public class EditarColeccionController {
	
	static ColeccionDAO cd= new ColeccionDAO();
	@FXML
	private ComboBox<String> titulotext;
	
	@FXML
	private ComboBox<String> NombresAutor;
	
	
	@FXML
	private void initialize() {
		 List<Autor> listaautores= AutorDAO.mostrartodos(); 
		 ObservableList<String> Autores = FXCollections.observableArrayList(nombresautores(listaautores));
		 List<Coleccion> listarcoleccion=ColeccionDAO.buscartodos();
		 ObservableList<String> titulos= FXCollections.observableArrayList(nombresColecciones(listarcoleccion));
		 
		NombresAutor.setItems(Autores);
		if(Autores.size()>2) {
			NombresAutor.setVisibleRowCount(2);
		}
		titulotext.setItems(titulos);
		if(titulos.size()>2) {
			titulotext.setVisibleRowCount(2);
		}
	}
	
	
	
	
	@FXML
	private void cancelar() throws IOException {
		App.setRoot("secondary");
	}
	
	// guardar la coleccion nueva
	@FXML
	private void switchToColecciones() throws IOException {

		cd.setTitulo(titulotext.getValue());
		cd.setnombre_autor(NombresAutor.getValue());
		cd.guardar();
		mostrarAlertInfo();
		App.setRoot("secondary");
	}	
	

	
	private void mostrarAlertInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Coleccion guardada");
		alert.showAndWait();
	}

	
	private ArrayList<String> nombresautores(List<Autor> a) {
		ArrayList<String> nombres = new ArrayList<String>();
		for (int i = 0; i < a.size(); i++) {
			nombres.add(a.get(i).getNombre());
		}
		return nombres;
	}
	private ArrayList<String> nombresColecciones(List<Coleccion> a) {
		ArrayList<String> nombres = new ArrayList<String>();
		for (int i = 0; i < a.size(); i++) {
			nombres.add(a.get(i).getTitulo());
		}
		return nombres;
	}
}
