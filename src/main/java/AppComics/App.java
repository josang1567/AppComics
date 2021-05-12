package AppComics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import AppComics.model.Autor;
import AppComics.model.AutorDAO;
import AppComics.model.Coleccion;
import AppComics.model.ColeccionDAO;
import AppComics.model.Comic;
import AppComics.model.ComicDao;
import AppComics.utils.SaveAndLoad;

/**
 * JavaFX App
 */
public class App extends Application {
 
	static SaveAndLoad snl = SaveAndLoad.getSingletoonInstance();
	
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Inicio"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
    	
        launch();
       
    }

}