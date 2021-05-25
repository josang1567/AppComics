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
	private final static String INSERTUPDATE = "INSERT INTO  comic(titulo,leido,titulo_coleccion,localizacion,propiedad,tapa,tipo,urlimagen) "
			+ "VALUES (?,?,?,?,?,?,?,? )"
			+ "ON DUPLICATE KEY UPDATE leido=?,titulo_coleccion=?,localizacion=?,propiedad=?,tapa=?, tipo=?,urlimagen=?";

	// eliminar
	private final static String DELETE = "DELETE FROM comic WHERE titulo=?";

	// seleccionar por nombre
	private final static String SELECTBYTITLE = "SELECT * FROM comic WHERE  titulo = ?";

	// seleccionar por propiedad
	private final static String SELECTBYLOCALIZACION = "SELECT * FROM comic WHERE  localizacion = ?";

	// seleccionar por propiedad
	private final static String SELECTBYPROPIEDAD = "SELECT * FROM comic WHERE  propiedad = ?";

	
	// selecciona las coleccion con el titulo de la coleccion igual
	private final static String SELECTBYCOLECCION = "SELECT * FROM comic WHERE  titulo_coleccion = ?";
	
	//update estado lectura a true
	private final static String updatelecturatrue="UPDATE comic set leido=1 where titulo=?";
	//update estado lectura a false
	private final static String updatelecturafalse="UPDATE comic set leido=0 where titulo=?";
	// update estado localizacion a true
	private final static String updatelocalizaciontrue="UPDATE comic set localizacion=1 where titulo=?";
	// update estado localizacion a false
	private final static String updatelocalizacionfalse="UPDATE comic set localizacion=0 where titulo=?";
	//update estado propiedad a true
	private final static String updatepropiedadtrue="UPDATE comic set propiedad=1 where titulo=?";
	//update estado propiedad a false
	private final static String updatepropiedadfalse="UPDATE comic set propiedad=0 where titulo=?";
	
	
	
	public ComicDao(String titulo, boolean leido, String titulo_coleccion, boolean localizacion, boolean propiedad,
			String tapa, String tipo,String urlImagen) {
		super(titulo, leido, titulo_coleccion, localizacion, propiedad, tapa, tipo,urlImagen);
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
		this.urlImagen=c.urlImagen;
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
					this.urlImagen=rs.getString("urlimagen");
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
				q.setString(8, this.urlImagen);
				// on duplicate

				q.setBoolean(9, this.Leido);
				q.setString(10, this.titulo_coleccion);
				q.setBoolean(11, this.localizacion);
				q.setBoolean(12, this.propiedad);
				q.setString(13, this.tapa);
				q.setString(14, this.tipo);
				q.setString(15, this.urlImagen);

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
				this.urlImagen="";
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
					a.setUrlImagen(rs.getString("urlimagen"));
					a.setColeccion(ColeccionDAO.buscarpornombre(rs.getString("titulo_coleccion")));
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
		System.out.println("BUSCandpo");
		List<Comic> result = new ArrayList<Comic>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYCOLECCION);
				q.setString(1, coleccion);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					System.out.println(coleccion);
					System.out.println("AQUIII");
					Comic a = new Comic();
					a.setTitulo(rs.getString("Titulo"));
					a.setLeido(rs.getBoolean("Leido"));
					a.setTitulo_coleccion(rs.getString("titulo_coleccion"));
					a.setLocalizacion(rs.getBoolean("localizacion"));
					a.setPropiedad(rs.getBoolean("propiedad"));
					a.setTapa(rs.getString("tapa"));
					a.setTipo(rs.getString("tipo"));
					a.setUrlImagen(rs.getString("urlimagen"));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static List<Comic> mostrartodos()  {
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
					a.setUrlImagen(rs.getString("urlimagen"));
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
