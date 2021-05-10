package AppComics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AppComics.model.Autor;
import AppComics.model.AutorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class EditarAutorController {
	
	static AutorDAO au= new AutorDAO();
	@FXML
	private ComboBox<String> nombrebox;
	
	@FXML
	private TextArea DescripcionText;
	
	
	@FXML
	private void cancelar() throws IOException{
		App.setRoot("AutorScene");
	}
	@FXML
	private void initialize() {
		 List<Autor> listaautores= AutorDAO.mostrartodos();
		ObservableList<String> Autores = FXCollections.observableArrayList(nombresautores(listaautores));
		nombrebox.setItems(Autores);
		if(Autores.size()>2) {
			nombrebox.setVisibleRowCount(2);
		}
			}
	
	@FXML
	private void guardarautor() throws IOException{
		
		
		au.setNombre(nombrebox.getValue());
		au.setDescripcion(DescripcionText.getText());
		au.guardar();
		mostrarAlertInfo();
		App.setRoot("AutorScene");
	}
	
	
	
	private void mostrarAlertInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Autor guardado");
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
