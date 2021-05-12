module ProyectoApp.AppComics {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires java.desktop;
	requires jdk.jconsole;
	

	opens AppComics.utils to java.xml;
    opens AppComics to javafx.fxml;
    exports AppComics;
}