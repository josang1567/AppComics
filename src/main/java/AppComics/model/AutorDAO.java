package AppComics.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AppComics.utils.conexion;


public class AutorDAO extends Autor {
	//codigo para mostrar todos
	private final static String GETALL="SELECT * FROM autor";
	
	// codigo usado para mostrar los comics seguin el codigo

	private final static String GETBYCODIGO = "SELECT * FROM  autor WHERE dni=";

	// string usado para insertar comnics
	private final static String INSERTUPDATE = "INSERT INTO  autor(nombre,Descripcion) "
			+ "VALUES (?,?) " + "ON DUPLICATE KEY UPDATE descripcion=?";

	// eliminar
	private final static String DELETE = "DELETE FROM autor WHERE nombre=?";

	// seleccionar por nombre
	private final static String SELECTBYNAME = "SELECT * FROM autor WHERE  nombre like ?";

	public AutorDAO(String nombre, int edad, String dni, String descripcion, List<Coleccion> obras) {
		super(nombre,  descripcion);
	}

	public AutorDAO() {
		super();
	}

	public AutorDAO(Autor a) {
		this.nombre = a.nombre;
		this.Descripcion = a.Descripcion;
		
	}

	public AutorDAO(String dni) {
		// Conexion
		super();
		Connection con = conexion.getConexion();
		// Stament
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = GETBYCODIGO + "'" + dni + "'";
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.nombre = rs.getString("nombre");
					this.Descripcion = rs.getString("descripcion");
					// this.obras = rs.;
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
						q.setString(1, this.nombre);
						q.setString(2, this.Descripcion);
						q.setString(3, this.Descripcion);
						rs =q.executeUpdate();		
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return rs;
	}

	
	
	public static List<Coleccion> buscarcoleccionesporautor(String nombre){
		return null;
	}
	
	public int eliminar() {
		int rs=0;
		Connection con = conexion.getConexion();
		
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(DELETE);
				q.setString(1, this.nombre);
				rs =q.executeUpdate();
				this.nombre = "Desconocido";
				this.Descripcion = "Desconocido";
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public static List<Autor> buscaPorNombre(String nombre) {
		List<Autor> result=new ArrayList<Autor>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTBYNAME);
				q.setString(1, "%"+nombre+"%");
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					//es que hay al menos un resultado
					Autor a=new Autor();
					a.setNombre(rs.getString("nombre"));
					a.setDescripcion(rs.getString("descripcion"));
					
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

		public static List<Autor> mostrartodos() {
			
			List<Autor> todos = new ArrayList<Autor>();
			Connection con= conexion.getConexion();
			if(con!=null) {
				
					PreparedStatement q;
					try {
						q = con.prepareStatement(GETALL);
						ResultSet rs=q.executeQuery();
						while(rs.next()) {
							//es que hay al menos un resultado
							Autor nuevo= new Autor();
							nuevo.setNombre(rs.getString("nombre"));
							nuevo.setDescripcion(rs.getString("descripcion"));
							
							todos.add(nuevo);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
										
				
			}
		
		
		return todos;
		
	}
	
	
}
