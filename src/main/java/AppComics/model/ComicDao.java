package AppComics.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AppComics.utils.conexion;

public class ComicDao extends Comic {

	// codigo para mostrar todos los comics
	private final static String GETALL = "SELECT * from comic";

	// string usado para insertar comnics
	private final static String INSERTUPDATE = "INSERT INTO  comic(titulo,leido,titulo_coleccion,localizacion,propiedad,tapa,tipo) "
			+ "VALUES (?,?,?,?,?,?,? )"
			+ "ON DUPLICATE KEY UPDATE leido=?,titulo_coleccion=?,localizacion=?,propiedad=?,tapa=?, tipo=?";

	// eliminar
	private final static String DELETE = "DELETE FROM comic WHERE titulo=?";

	// seleccionar por nombre
	private final static String SELECTBYTITLE = "SELECT * FROM comic WHERE  titulo like ?";

	// seleccionar por propiedad
	private final static String SELECTBYLOCALIZACION = "SELECT * FROM comic WHERE  localizacion like ?";

	// seleccionar por propiedad
	private final static String SELECTBYPROPIEDAD = "SELECT * FROM comic WHERE  propiedad like ?";

	// selecciona las coleccion con el titulo de la coleccion igual
	private final static String SELECTBYCOLECCION = "SELECT * FROM comic WHERE  titulo_coleccion like ?";

	public ComicDao(String titulo, boolean leido, String titulo_coleccion, boolean localizacion, boolean propiedad,
			String tapa, String tipo) {
		super(titulo, leido, titulo_coleccion, localizacion, propiedad, tapa, tipo);
	}

	public ComicDao() {
		super();
	}

	public ComicDao(Comic c) {
		this.Titulo = c.Titulo;

		this.Leido = c.Leido;
		this.titulo_coleccion = c.titulo_coleccion;
		this.localizacion = c.localizacion;
		this.propiedad = c.propiedad;
		this.tapa = c.tapa;
		this.tipo = c.tapa;
	}

	public ComicDao(String titulo) {
		// getByID from mariaDB
		// Conexion
		super();
		Connection con = conexion.getConexion();
		// Stament
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = SELECTBYTITLE + "'" + titulo + "'";
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.Titulo = rs.getString("Titulo");
					this.Leido = rs.getBoolean(leido(Leido));
					this.titulo_coleccion = rs.getString("titulo_coleccion");
					this.localizacion = rs.getBoolean(localizacion(localizacion));
					this.propiedad = rs.getBoolean(propiedad(propiedad));
					this.tapa = rs.getString(tapa);
					this.tipo = rs.getString(tipo);
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
				q.setString(1, this.Titulo);
				q.setBoolean(2, this.Leido);
				q.setString(3, this.titulo_coleccion);
				q.setBoolean(4, this.localizacion);
				q.setBoolean(5, this.propiedad);
				q.setString(6, this.tapa);
				q.setString(7, this.tipo);
				// on duplicate

				q.setBoolean(8, this.Leido);
				q.setString(9, this.titulo_coleccion);
				q.setBoolean(10, this.localizacion);
				q.setBoolean(11, this.propiedad);
				q.setString(12, this.tapa);
				q.setString(13, this.tipo);

				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public int eliminar(String codigo) {
		int rs = 0;
		Connection con = conexion.getConexion();
		System.out.println(codigo);
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setString(1, codigo);

				rs = q.executeUpdate();
				this.Titulo = "Desconocido";
				this.Leido = false;
				this.titulo_coleccion = "00000";
				this.localizacion = true;
				this.propiedad = false;
				this.tapa = "blanda";
				this.tipo = "comic";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public static List<Comic> buscaPorNombre(String nombre) {
		List<Comic> result = new ArrayList<Comic>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYTITLE);
				q.setString(1, nombre);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {

					Comic a = new Comic();
					a.setTitulo(rs.getString("Titulo"));
					a.setLeido(rs.getBoolean("Leido"));
					a.setTitulo_coleccion(rs.getString("titulo_coleccion"));
					a.setLocalizacion(rs.getBoolean("localizacion"));
					a.setPropiedad(rs.getBoolean("propiedad"));
					a.setTapa(rs.getString("tapa"));
					a.setTipo(rs.getString("tipo"));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public static List<Comic> buscaPorcoleccion(String coleccion) {
		List<Comic> result = new ArrayList<Comic>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYCOLECCION);
				q.setString(1, "%" + coleccion + "%");
				ResultSet rs = q.executeQuery();
				while (rs.next()) {

					Comic a = new Comic();
					a.setTitulo(rs.getString("Titulo"));
					a.setLeido(rs.getBoolean("Leido"));
					a.setTitulo_coleccion(rs.getString("titulo_coleccion"));
					a.setLocalizacion(rs.getBoolean("localizacion"));
					a.setPropiedad(rs.getBoolean("propiedad"));
					a.setTapa(rs.getString("tapa"));
					a.setTipo(rs.getString("tipo"));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public static List<Comic> mostrartodos() {
		List<Comic> result = new ArrayList<Comic>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(GETALL);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {

					Comic a = new Comic();
					a.setTitulo(rs.getString("Titulo"));
					a.setLeido(rs.getBoolean("Leido"));
					a.setTitulo_coleccion(rs.getString("titulo_coleccion"));
					a.setLocalizacion(rs.getBoolean("localizacion"));
					a.setPropiedad(rs.getBoolean("propiedad"));
					a.setTapa(rs.getString("tapa"));
					a.setTipo(rs.getString("tipo"));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public static List<Comic> buscaPorPropiedad(boolean propiedad) {
		List<Comic> result = new ArrayList<Comic>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYPROPIEDAD);
				q.setBoolean(1, propiedad);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {

					Comic a = new Comic();
					a.setTitulo(rs.getString("Titulo"));
					a.setLeido(rs.getBoolean("Leido"));
					a.setTitulo_coleccion(rs.getString("titulo_coleccion"));
					a.setLocalizacion(rs.getBoolean("localizacion"));
					a.setPropiedad(rs.getBoolean("propiedad"));
					a.setTapa(rs.getString("tapa"));
					a.setTipo(rs.getString("tipo"));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public static List<Comic> buscaPorLocalizacion(boolean localizacion) {
		List<Comic> result=new ArrayList<Comic>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTBYLOCALIZACION);
				q.setBoolean(1, localizacion);
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					
					Comic a=new Comic();
					a.setTitulo(rs.getString("Titulo"));
					a.setLeido(rs.getBoolean("Leido"));
					a.setTitulo_coleccion(rs.getString("titulo_coleccion"));
					a.setLocalizacion(rs.getBoolean("localizacion"));
					a.setPropiedad(rs.getBoolean("propiedad"));
					a.setTapa(rs.getString("tapa"));
					a.setTipo(rs.getString("tipo"));
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
