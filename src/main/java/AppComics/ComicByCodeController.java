package AppComics;

import java.io.IOException;
import java.util.List;

import AppComics.model.Coleccion;
import AppComics.model.Comic;
import AppComics.model.ComicDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;

public class ComicByCodeController {
	static ComicDao cd = new ComicDao();
	static String code="";
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
	// ir a crear comic
	@FXML
	private void switchToCrearComic() throws IOException {
		App.setRoot("CrearComicScene");
	}

	@FXML
	private Label TituloLabel;
	@FXML
	private Label Num_paginasLabel;
	@FXML
	private Label LeidoLabel;

	@FXML
	private Label CodigoLabel;

	@FXML
	private Label Codigo_coleccionLabel;

	@FXML
	private TableView<Comic> tablacomics;
	@FXML
	private TableColumn<Comic, String> ComicColumna;


	@FXML
	protected void initialize() {
		System.out.println("Cargando...");
		muestraInfo(null);
		configuraTabla();
		// Cargar de la base de datos!!!!!
		
		List<Comic> todas = ComicDao.buscaPorcoleccion((String) App.infoShared);
		tablacomics.setItems(FXCollections.observableArrayList(todas));
		tablacomics.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestraInfo(newValue);
		});
	}
	
	
	
	
	private void configuraTabla() {

		ComicColumna.setCellValueFactory(cadacomic -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadacomic.getValue().getTitulo());
			return v;
		});

	}

	private void muestraInfo(Comic c) {
		if (c != null) {

			TituloLabel.setText(c.getTitulo());

			Num_paginasLabel.setText(c.getNum_paginas() + "");

			LeidoLabel.setText(c.leido(c.isLeido()));

			CodigoLabel.setText(c.getCodigo());

			Codigo_coleccionLabel.setText(c.getCodigo_coleccion());

		} else {
			TituloLabel.setText("Desconocido");
			Num_paginasLabel.setText("Ninguna");
			LeidoLabel.setText("Desconocido");
			CodigoLabel.setText("Ninguno");
			Codigo_coleccionLabel.setText("Ninguno");
		}
	}
	
	@FXML
	private void eliminar() throws IOException {
		
		
		cd.eliminar(CodigoLabel.getText());
		App.setRoot("primary");
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
	private void infocomics() {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Informacion basica:");
	    alert.setContentText("En esta parte se muestra la informacion de los comics");
	    alert.showAndWait();
	}
	
}
