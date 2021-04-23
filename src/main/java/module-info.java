module ProyectoApp.AppComics {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.base;

    opens AppComics to javafx.fxml;
    exports AppComics;
}