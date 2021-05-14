package AppComics;

import java.io.File;
import java.io.IOException;
import java.util.List;

import AppComics.model.Comic;
import AppComics.model.ComicDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;

public class ComicController {
	static ComicDao cd = new ComicDao();

	static List<Comic> comics = ComicDao.mostrartodos();
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
	//ir a editar
	@FXML
	private void switchtoeditar() throws IOException{
		App.setRoot("EditarComic");
	}
	// ir a crear inicio
	@FXML
	private void switchToInicio() throws IOException {
		App.setRoot("Inicio");
	}

	@FXML
	private Label TituloLabel;
	@FXML
	private Label LeidoLabel;
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
	private ImageView portada;
	
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
			Titulo_coleccionLabel.setText(c.getTitulo_coleccion());
			LocalizacioLabel.setText(c.localizacion(c.isLocalizacion()));
			propiedadLabel.setText(c.propiedad(c.isPropiedad()));
			tapaLabel.setText(c.getTapa());
			tipoLabel.setText(c.getTipo());
			File f=new File("file:"+c.getUrlImagen());
			Image cportada= new Image (f.getPath());
            portada.setImage(cportada);

		} else {
			TituloLabel.setText("Desconocido");
			LeidoLabel.setText("Desconocido");
			LocalizacioLabel.setText("Desconocido");
			propiedadLabel.setText("Desconocido");
			Titulo_coleccionLabel.setText("Ninguno");
			tapaLabel.setText("Ninguno");
			tipoLabel.setText("Desconocido");
			File f=new File("file: src/main/resources/Imagenes/Colecciones/vacio.png");
			Image cportada= new Image (f.getPath());
			portada.setImage(cportada);
		}
	}

	@FXML
	private void eliminar() throws IOException {

		cd.eliminar(TituloLabel.getText());
		App.setRoot("primary");
	}

	@FXML
	private void lectura() throws IOException {

		cd.setTitulo(TituloLabel.getText());
		cd.setLeido(cambialeido(LeidoLabel.getText()));
		cd.setTitulo_coleccion(Titulo_coleccionLabel.getText());
		cd.setLocalizacion(cambialocal(LocalizacioLabel.getText()));
		cd.setPropiedad(cambiapropi(propiedadLabel.getText()));
		cd.setTapa(tapaLabel.getText());
		cd.setTipo(tipoLabel.getText());

		if (cd.isLeido() == false) {
			cd.setLeido(true);
		} else if (cd.isLeido() == true) {
			cd.setLeido(false);
		}

		cd.guardar();
		App.setRoot("primary");
	}

	@FXML
	private void propiedad() throws IOException {
		
		cd.setTitulo(TituloLabel.getText());
		cd.setLeido(cambialeido(LeidoLabel.getText()));
		cd.setTitulo_coleccion(Titulo_coleccionLabel.getText());
		cd.setLocalizacion(cambialocal(LocalizacioLabel.getText()));
		cd.setPropiedad(cambiapropi(propiedadLabel.getText()));
		cd.setTapa(tapaLabel.getText());
		cd.setTipo(tipoLabel.getText());

		if (cd.isPropiedad() == false) {
			cd.setPropiedad(true);
		} else if (cd.isPropiedad() == true) {
			cd.setPropiedad(false);
		}

		cd.guardar();
		App.setRoot("primary");
	}

	@FXML
	private void localizacion() throws IOException {
		
		cd.setTitulo(TituloLabel.getText());
		cd.setLeido(cambialeido(LeidoLabel.getText()));
		cd.setTitulo_coleccion(Titulo_coleccionLabel.getText());
		cd.setLocalizacion(cambialocal(LocalizacioLabel.getText()));
		cd.setPropiedad(cambiapropi(propiedadLabel.getText()));
		cd.setTapa(tapaLabel.getText());
		cd.setTipo(tipoLabel.getText());
		if (cd.isLocalizacion() == false) {
			cd.setLocalizacion(true);
		} else if (cd.isLocalizacion() == true) {
			cd.setLocalizacion(false);
		}
		

		cd.guardar();
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

	private boolean cambialocal(String frase) {
		boolean cambio = false;
		if (frase.trim().equals("En casa")) {
			cambio = true;
		} else if (frase.trim().equals("Prestado")) {
			cambio = false;
		}
		return cambio;
	}

	private boolean cambialeido(String frase) {
		boolean cambio = false;
		if (frase.trim().equals("No leido")) {
			cambio = false;
		} else if (frase.trim().equals("Leido")) {
			cambio = true;
		}
		return cambio;
	}

	private boolean cambiapropi(String frase) {
		boolean cambio = false;
		if (frase.trim().equals("En posesion")) {
			cambio = true;
		} else if (frase.trim().equals("En lista de deseos")) {
			cambio = false;
		}
		return cambio;
	}

}
