package AppComics.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AppComics.utils.conexion;

public class ColeccionDAO extends Coleccion {
	//codigo usado para mostrar todas las colecciones
	private final static String GETALL="SELECT * from coleccion";
	
	// codigo usado para mostrar los comics seguin el codigo

	private final static String GETBYCODIGO = "SELECT * FROM  Coleccion WHERE titulo=";

	// string usado para insertar comnics
	private final static String INSERTUPDATE = "INSERT INTO  Coleccion(titulo, creador) "
			+ "VALUES (?,?) " + "ON DUPLICATE KEY UPDATE creador=?";

	// eliminar
	private final static String DELETE = "DELETE FROM Coleccion WHERE titulo=?";

	// seleccionar por nombre
	private final static String SELECTBYTITLE = "SELECT * FROM Coleccion WHERE  titulo like ?";
	// seleccionar por creador;
	private final static String SELECTBYCREADOR = "SELECT * FROM Coleccion WHERE  creador like ?";

	public ColeccionDAO(String titulo, String creador) {
		super(titulo, creador);
	}

	public ColeccionDAO() {
		super();
	}
	public ColeccionDAO(Coleccion c) {
		this.titulo = c.titulo;
		this.creador = c.creador;
		
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
					this.creador = rs.getString("creador");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int guardar() {
		// INSERT o UPDATE
				//INSERT -> si no existe OK
				//En caso de ERROR -> hago un update
				int rs=0;
				Connection con = conexion.getConexion();
				
				if (con != null) {
					try {
						PreparedStatement q=con.prepareStatement(INSERTUPDATE);
						//insert 
						q.setString(1, this.titulo);
						q.setString(2, this.creador);
						//on duplicate
						q.setString(3, this.creador);
						rs =q.executeUpdate();		
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return rs;
	}
	public int eliminar() {
		int rs=0;
		Connection con = conexion.getConexion();
		
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(DELETE);
				q.setString(1, this.titulo);
				rs =q.executeUpdate();
				this.titulo = "Desconocido";
				this.creador = "Desconocido";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}



	public static List<Coleccion> buscaPorNombre(String nombre) {
		List<Coleccion> result=new ArrayList<Coleccion>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTBYCREADOR);
				q.setString(1, nombre);
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					//es que hay al menos un resultado
					Coleccion a=new Coleccion();
					a.setTitulo(rs.getString("titulo"));
					a.setCreador(rs.getString("creador"));
					
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static List<Coleccion> buscartodos() {
		List<Coleccion> result=new ArrayList<Coleccion>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(GETALL);
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					//es que hay al menos un resultado
					Coleccion a=new Coleccion();
					a.setCreador(rs.getString("creador"));
					a.setTitulo(rs.getString("titulo"));
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
