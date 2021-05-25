package AppComics.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import AppComics.utils.conexion;

public class ColeccionDAO extends Coleccion {
	// codigo usado para mostrar todas las colecciones
	private final static String GETALL = "SELECT * from coleccion";

	// codigo usado para mostrar los comics seguin el codigo

	private final static String GETBYCODIGO = "SELECT * FROM  Coleccion WHERE titulo=";

	// string usado para insertar comnics
	private final static String INSERTUPDATE = "INSERT INTO  Coleccion(titulo, nombre_autor,urlimagen) "
			+ "VALUES (?,?,?) " + "ON DUPLICATE KEY UPDATE nombre_autor=?,urlimagen=?";

	// eliminar
	private final static String DELETE = "DELETE FROM Coleccion WHERE titulo=?";

	// seleccionar por nombre_autor;
	private final static String SELECTBYnombre_autor = "SELECT * FROM Coleccion WHERE  nombre_autor like ?";
	private final static String SELECTBYNAME = "SELECT * FROM Coleccion WHERE  titulo like ?";

	public ColeccionDAO(String titulo, String nombre_autor, String urlimagen) {
		super(titulo, nombre_autor, urlimagen);
	}

	public ColeccionDAO() {
		super();
	}

	public ColeccionDAO(Coleccion c) {
		this.titulo = c.titulo;
		this.nombre_autor = c.nombre_autor;
		this.urlImagen = c.urlImagen;

	}

	public ColeccionDAO(String codigo) {
		// getByID from mariaDB
		// Conexion
		super();
		Connection con = conexion.getConexion();
		// Stament
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = GETBYCODIGO + "'" + codigo + "'";
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.titulo = rs.getString("titulo");
					this.nombre_autor = rs.getString("nombre_autor");
					this.urlImagen = rs.getString("urlimagen");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int guardar() {
		// INSERT o UPDATE
		// INSERT -> si no existe OK
		// En caso de ERROR -> hago un update
		int rs = 0;
		Connection con = conexion.getConexion();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				// insert
				q.setString(1, this.titulo);
				q.setString(2, this.nombre_autor);
				q.setString(3, this.urlImagen);
				// on duplicate
				q.setString(4, this.nombre_autor);
				q.setString(5, this.urlImagen);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public int eliminar() {
		int rs = 0;
		Connection con = conexion.getConexion();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setString(1, this.titulo);
				rs = q.executeUpdate();
				this.titulo = "Desconocido";
				this.nombre_autor = "Desconocido";
				this.urlImagen = "";
				this.autor = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public static List<Coleccion> buscarporautor(String nombre) {
		List<Coleccion> result = new ArrayList<Coleccion>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYnombre_autor);
				q.setString(1, nombre);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// es que hay al menos un resultado
					Coleccion a = new Coleccion();
					a.setTitulo(rs.getString("titulo"));
					a.setnombre_autor(rs.getString("nombre_autor"));
					a.setUrlImagen("urlimagen");
					a.setAutor(AutorDAO.buscaPorNombre(rs.getString("nombre_autor")));
					a.setComics(ComicDao.buscaPorcoleccion(rs.getString("titulo")));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	public static Coleccion buscarpornombre(String nombre) {
		Coleccion result = new Coleccion();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYNAME);
				q.setString(1, nombre);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// es que hay al menos un resultado
					Coleccion a = new Coleccion();
					a.setTitulo(rs.getString("titulo"));
					a.setnombre_autor(rs.getString("nombre_autor"));
					a.setUrlImagen("urlimagen");
					a.setAutor(AutorDAO.buscaPorNombre(rs.getString("nombre_autor")));
					a.setComics(ComicDao.buscaPorcoleccion(rs.getString("titulo")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	public static List<Coleccion> buscartodos() {
		List<Coleccion> result = new ArrayList<Coleccion>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(GETALL);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// es que hay al menos un resultado
					Coleccion a = new Coleccion();
					a.setnombre_autor(rs.getString("nombre_autor"));
					a.setTitulo(rs.getString("titulo"));
					a.setUrlImagen(rs.getString("urlimagen"));
					a.setAutor(AutorDAO.buscaPorNombre(rs.getString("nombre_autor")));
					a.setComics(ComicDao.buscaPorcoleccion(rs.getString("titulo")));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	
	
}
