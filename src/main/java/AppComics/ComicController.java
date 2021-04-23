package AppComics;

import java.io.IOException;
import java.util.List;

import AppComics.model.Comic;
import AppComics.model.ComicDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ComicController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private Label TituloLabel;
    @FXML
    private Label Num_paginasLabel;
    @FXML
    private Label LeidoLabel;
    @FXML
    private Label ColeccionLabel;
    
    @FXML
    private Label CodigoLabel;
   
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
			
			Num_paginasLabel.setText(c.getNum_paginas()+"");
			
			LeidoLabel.setText(c.leido(c.isLeido()));
			
			CodigoLabel.setText(c.getCodigo());
			
		} else {
			TituloLabel.setText("");
			Num_paginasLabel.setText("");
			LeidoLabel.setText("");
			ColeccionLabel.setText("");
			CodigoLabel.setText("");
		}
}
}
