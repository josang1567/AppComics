package AppComics;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import AppComics.model.Autor;
import AppComics.model.Coleccion;
import AppComics.model.ColeccionDAO;
import AppComics.utils.Utils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ColeccionesController {
	static ColeccionDAO coleccion= new ColeccionDAO();
	@FXML
	private Button Titulobutton;
	@FXML
	private Label CreadorLabel;
	@FXML
	private TableView<Coleccion> tablaColecciones;
	@FXML
	private TableColumn<Coleccion, String> ColeccionColumna;
	@FXML
	private ImageView portada;
	

	// ir a todas las colecciones
	@FXML
	private void switchToColecciones() throws IOException {
		Utils.tipopestaña="todos";
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

	// ir a todos comics
		@FXML
		private void switchToComics() throws IOException {
			Utils.tipopestaña="todos";
			App.setRoot("primary");
		}

	// ir a crear inicio
			@FXML
			private void switchToInicio() throws IOException {
				App.setRoot("Inicio");
			}
	// ir a los comics de este codigo
		@FXML
		private void switchToComicsbycodigo() throws IOException {
			//Utils.dato=Titulobutton.getText();
			Utils.dato=this.Titulobutton.getText();
			System.out.println(this.Titulobutton.getText());
			Utils.tipopestaña="codigo";
			App.setRoot("primary");
			
		}
		//ir a editar
		@FXML
		private void switchtoeditar() throws IOException{
			App.setRoot("EditarColeccion");
		}
	@FXML
	private void eliminarColeccion()throws IOException{
		coleccion.setTitulo(this.Titulobutton.getText());
		coleccion.eliminar();
	
		App.setRoot("secondary");
	}
	
	@FXML
	protected void initialize() {
	
	List<Coleccion> todas= new ArrayList<Coleccion>();
		System.out.println("Cargando...");
		muestraInfo(null);
		configuraTabla();
		// Cargar de la base de datos!!!!!
		switch (Utils.tipopestaña) {
		case "todos":
			 todas = ColeccionDAO.buscartodos();
			break;
		case "por autor":
			 todas = ColeccionDAO.buscarporautor((String) Utils.dato);
			break;
		
		default:
			 todas = ColeccionDAO.buscartodos();
			break;
		}
		
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
		portada.setVisible(true);
		
		if(c!=null) {
			Titulobutton.setText(c.getTitulo());
			CreadorLabel.setText(c.getnombre_autor());
			File f=new File("file:"+c.getUrlImagen());
			Image cportada= new Image (f.getPath());
            portada.setImage(cportada);
		}else {
			Titulobutton.setText("Desconocido");
			CreadorLabel.setText("Desconocido");
			File f=new File("file: src/main/resources/Imagenes/Colecciones/vacio.png");
			Image cportada= new Image (f.getPath());
			portada.setImage(cportada);
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
	    alert.setContentText("Para editar los datos de una coleccion ya existente debes ir a editar ");
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