package AppComics;

import java.io.IOException;
import java.util.List;

import AppComics.model.Comic;
import AppComics.model.ComicDao;
import AppComics.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;

public class ComicController {
	static ComicDao cd = new ComicDao();
	
	static List<Comic> comics= cd.mostrartodos();
	static String code = "";

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
	private Label LeidoLabel;
	@FXML
	private Label num_paginaslabel;
	@FXML
	private Label Titulo_coleccionLabel;
	@FXML
	private Label LocalizacioLabel;
	@FXML
	private Label propiedadLabel;
	@FXML
	private Label tapaLabel;
	@FXML
	private Label tipoLabel;

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

		List<Comic> todas = ComicDao.mostrartodos();
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
			LeidoLabel.setText(c.leido(c.isLeido()));
			num_paginaslabel.setText(Integer.toString(c.getNum_paginas()));
			Titulo_coleccionLabel.setText(c.getTitulo_coleccion());
			LocalizacioLabel.setText(c.localizacion(c.isLocalizacion()));
			propiedadLabel.setText(c.propiedad(c.isPropiedad()));
			tapaLabel.setText(c.getTapa());
			tipoLabel.setText(c.getTipo());


		} else {
			TituloLabel.setText("Desconocido");
			LeidoLabel.setText("Desconocido");
			Titulo_coleccionLabel.setText("Ninguno");
			tapaLabel.setText("Ninguno");
			
		}
	}

	@FXML
	private void eliminar() throws IOException {

		cd.eliminar(TituloLabel.getText());
		App.setRoot("primary");
	}
	@FXML
	private void lectura() throws IOException{
		boolean encontrado=false;
		for (int i = 0; i < comics.size()&& encontrado==false; i++) {
			if(TituloLabel.equals(comics.get(i).getTitulo())) {
				cd=(ComicDao) comics.get(i);
				
				if(cd.isLeido()==true) {
					cd.setLeido(false);
				}else if(cd.isLeido()==false){
					cd.setLeido(true);
				}
				encontrado=true;
			}
		}
		
		
		cd.guardar();
		App.setRoot("primary");
	}
	
	@FXML
	private void propiedad() throws IOException{
		
		App.setRoot("primary");
	}
	@FXML 
	private void localizacion() throws IOException{
		
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
