module ProyectoApp.AppComics {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens AppComics.app to javafx.fxml;
    exports AppComics.app;
}