package AppComics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import AppComics.model.ComicDao;
import AppComics.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import AppComics.model.Coleccion;
import AppComics.model.ColeccionDAO;
import AppComics.model.Comic;

public class EditarComicController {
	 
	static List<Comic> todoscomic = ComicDao.mostrartodos();
	static ComicDao c = new ComicDao();
	@FXML
	private ComboBox<String> titulobox;
	ObservableList<String> Listatituloscomic = FXCollections.observableArrayList(tituloscomics(todoscomic));
	@FXML
	private ChoiceBox<String> leidobox;
	ObservableList<String> leido = FXCollections.observableArrayList("Leido", "No leido");
	@FXML
	private ComboBox<String> coleccionbox;

	
	@FXML
	private ChoiceBox<String> localizacionbox;
	ObservableList<String> localizacion = FXCollections.observableArrayList("En casa", "Prestado");
	@FXML
	private ChoiceBox<String> propiedadbox;
	ObservableList<String> propiedad = FXCollections.observableArrayList("En posesion", "Añadir a lista de deseos");
	
	@FXML
	private ChoiceBox<String> tapasbox;

	ObservableList<String> tapas = FXCollections.observableArrayList("dura", "blanda");
	@FXML
	private ChoiceBox<String> tiposbox;

	ObservableList<String> tipos = FXCollections.observableArrayList("comic", "manga", "manwha", "novela grafica");
	@FXML
	private ImageView portadaimage;
	@FXML
	private TextField urltext;
	@FXML
	private Button verimagen;
	static String direccionurl = "src/main/resources/imagenes/Comics/";
	
	@FXML
	private void cancelar() throws IOException {
		Utils.tipopestaña="todos";
		App.setRoot("primary");
	}

	@FXML
	private void initialize() {
		List<Coleccion> todascoleccion = ColeccionDAO.buscartodos();
		ObservableList<String> Listatitulos = FXCollections.observableArrayList(nombrescolecciones(todascoleccion));
		
		titulobox.setItems(Listatituloscomic);
		if(Listatituloscomic.size()>3) {
			titulobox.setVisibleRowCount(3);
		}
		leidobox.setValue(leido.get(0));
		leidobox.setItems(leido);
		localizacionbox.setValue(localizacion.get(0));
		localizacionbox.setItems(localizacion);
		propiedadbox.setValue(propiedad.get(0));
		propiedadbox.setItems(propiedad);
		tapasbox.setValue(tapas.get(0));
		tapasbox.setItems(tapas);
		tiposbox.setValue(tipos.get(0));
		tiposbox.setItems(tipos);
		coleccionbox.setValue(Listatitulos.get(0));
		coleccionbox.setItems(Listatitulos);
		if(Listatitulos.size()>3) {
			coleccionbox.setVisibleRowCount(3);
		}
		

	}

	// guardar los datos introducidos y volver a la pagina original
	@FXML
	private void switchToComics() throws IOException {

	
		

		c.setTitulo(titulobox.getValue());
		c.setLeido(cambiochoice(leidobox));
		c.setTitulo_coleccion(coleccionbox.getValue());
		c.setLocalizacion(cambiochoice(localizacionbox));
		c.setPropiedad(cambiochoice(propiedadbox));
		c.setTipo(tiposbox.getValue());
		c.setTapa(tapasbox.getValue());
		Utils.saveImage(urltext.getText(), direccionurl + c.getTitulo() + ".jpg");
		c.setUrlImagen(direccionurl + c.getTitulo() + ".jpg");
		// guarda el comic en la base de datos
		try {
			c.guardar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR AL GUARDAR");
			e.printStackTrace();
		}

		// muestra un mensaje de que se ha guardado correctamente
		mostrarAlertInfo();

		// vuelve a la escena de comic
		Utils.tipopestaña="todos";
		App.setRoot("primary");
	}

	

	private void mostrarAlertInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Comic guardado");
		alert.showAndWait();
	}

	


	

	private ArrayList<String> nombrescolecciones(List<Coleccion> c) {
		ArrayList<String> titulos = new ArrayList<String>();
		for (int i = 0; i < c.size(); i++) {
			titulos.add(c.get(i).getTitulo());
		}
		return titulos;
	}
	private ArrayList<String> tituloscomics(List<Comic> c) {
		ArrayList<String> titulos = new ArrayList<String>();
		for (int i = 0; i < c.size(); i++) {
			titulos.add(c.get(i).getTitulo());
		}
		return titulos;
	}
	private boolean cambiochoice(ChoiceBox<String>tipo) {
		boolean result= false;
		if(tipo.getValue()==localizacion.get(0)) {
			//true==en casa
			result =true;
		}else if(tipo.getValue()==localizacion.get(1)) {
			//false== prestado
			result=false;
		}else if(tipo.getValue()==leido.get(0)) {
			//true == leido
			result=true;
		}else if (result) {
			//false== no leido
			result=false;
		}else if (tipo.getValue()==propiedad.get(0)) {
			//true==en posesion
			result=true;
		}else if(tipo.getValue()==propiedad.get(1)) {
			//false==en lista de deseos
			result=false;
		}
		
		return result;
	}
	@FXML
	private void selecImagen() {
		File file = null;
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Selecionar imagen...");
		try {
			file = filechooser.showOpenDialog(null);
			if (file != null && file.getPath().matches(".+\\.png") || file.getPath().matches(".+\\.jpg")) {
				Image img = new Image("file:\\" + file.getPath());
				portadaimage.setImage(img);
				urltext.setText(file.getPath());
			} else { // si la extension es incorrecta sale esta alerta
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Información");
				alert.setContentText("Formato incorrecto: Debe elegir un tipo de archivo jpg o png.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			// TODO: handle exception;
		}
	}
}