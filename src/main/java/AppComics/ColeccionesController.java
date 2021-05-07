package AppComics;



import java.io.IOException;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import AppComics.model.Coleccion;
import AppComics.model.ColeccionDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ColeccionesController {
	
	@FXML
	private Label TituloLabel;

	@FXML
	private Label NumeroPaginasLabel;

	@FXML
	private Label CreadorLabel;

	@FXML
	private Label EstadoDeLecturaLabel;

	@FXML
	private Button codigoLabel;
	@FXML
	private TableView<Coleccion> tablaColecciones;
	@FXML
	private TableColumn<Coleccion, String> ColeccionColumna;
	

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

	// ir a crear coleccion
	@FXML
	private void SwitchToCrearColeccion() throws IOException {
		App.setRoot("CrearColeccion");
	}

	// ir a los comics
	@FXML
	private void switchToComics() throws IOException {
		App.setRoot("primary");
	}
	// ir a los comics de este codigo
		@FXML
		private void switchToComicsbycodigo() throws IOException {
			App.infoShared=codigoLabel.getText();
			App.setRoot("ComicsByCode");
			
		}
	
	@FXML
	private void eliminarColeccion()throws IOException{
		
	}
	
	@FXML
	protected void initialize() {
		System.out.println("Cargando...");
		muestraInfo(null);
		configuraTabla();
		// Cargar de la base de datos!!!!!
		List<Coleccion> todas = ColeccionDAO.buscartodos();
		tablaColecciones.setItems(FXCollections.observableArrayList(todas));
		tablaColecciones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
			muestraInfo(newValue);
		});
	}
	
	private void configuraTabla() {

		ColeccionColumna.setCellValueFactory(cadacoleccion -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadacoleccion.getValue().getTitulo());
			return v;
		});

	}
	
	
	private void muestraInfo(Coleccion c) {
		if(c!=null) {
			TituloLabel.setText(c.getTitulo());
			NumeroPaginasLabel.setText(c.getTotal_paginas()+"");
			CreadorLabel.setText(c.getCreador());
			EstadoDeLecturaLabel.setText(c.leido(c.isLeido()));
			codigoLabel.setText(c.getCodigo());;
		}else {
			TituloLabel.setText("Desconocido");
			NumeroPaginasLabel.setText("0");
			CreadorLabel.setText("Desconocido");
			EstadoDeLecturaLabel.setText("Desconocido");
			codigoLabel.setText("Desconocido");
		}
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
	private void comoEditar() {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Editar");
	    alert.setContentText("Para editar los datos de una coleccion ya existente debes ir a crear "
	    		+ "y si el codigo coincide con una ya existente se sobreescribira encima.");
	    alert.showAndWait();
	}
	@FXML
	private void infocolecciones() {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Informacion basica:");
	    alert.setContentText("En esta parte se muestra la informacion de las distintas colecciones");
	    alert.showAndWait();
	}

}