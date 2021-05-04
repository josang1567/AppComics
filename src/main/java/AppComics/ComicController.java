package AppComics;

import java.io.IOException;
import java.util.List;

import AppComics.model.Comic;
import AppComics.model.ComicDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;

public class ComicController {
	static ComicDao cd = new ComicDao();

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
	private void eliminarComic() throws IOException {

	}

	@FXML
	protected void initialize() {
		System.out.println("Cargando...");
		muestraInfo(null);
		configuraTabla();
		// Cargar de la base de datos!!!!!
		List<Comic> todas = ComicDao.listartodos();
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
	private void eliminar() {
		cd.eliminar();
	}
}
