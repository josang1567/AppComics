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

	private final static String GETBYCODIGO = "SELECT nombre,edad,dni,Descripcion FROM  autor WHERE dni=";

	// string usado para insertar comnics
	private final static String INSERTUPDATE = "INSERT INTO  autor(nombre,edad,dni,Descripcion) "
			+ "VALUES (?,?,?,?) " + "ON DUPLICATE KEY UPDATE nombre=?,edad=?,descripcion=?";

	// eliminar
	private final static String DELETE = "DELETE FROM autor WHERE dni=?";

	// seleccionar por nombre
	private final static String SELECTBYNAME = "SELECT * FROM autor WHERE  nombre like ?";
	private final static String SELECTBYDNI = "SELECT * FROM autor WHERE  dni like ?";

	public AutorDAO(String nombre, int edad, String dni, String descripcion, List<Coleccion> obras) {
		super(nombre, edad, dni, descripcion, obras);
	}

	public AutorDAO() {
		super();
	}

	public AutorDAO(Autor a) {
		this.nombre = a.nombre;
		this.edad = a.edad;
		this.dni = a.dni;
		this.Descripcion = a.Descripcion;
		this.obras = a.obras;
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
					this.edad = rs.getInt("edad");
					this.dni = rs.getString("dni");
					Descripcion = rs.getString("descripcion");
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
						q.setInt(2, this.edad);
						q.setString(3, this.dni);
						q.setString(4, this.Descripcion);
						q.setString(5, this.nombre);
						q.setInt(6, this.edad);
						q.setString(7, this.Descripcion);
						rs =q.executeUpdate();		
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return rs;
	}

	@Override
	public List<Coleccion> getObras(){
		if(obras==null) {
			obras=AutorDAO.buscarcoleccionesporautor(this.nombre);
		}
		return obras;
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
				q.setString(1, this.dni);
				rs =q.executeUpdate();
				this.nombre = "Desconocido";
				this.edad = 0;
				this.dni = "desconocido";
				this.Descripcion = "Desconocido";
				this.obras = null;
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
					a.setEdad(rs.getInt("edad"));
					a.setDni(rs.getString("dni"));
					a.setDescripcion(rs.getString("descripcion"));
					a.setObras(null);
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public static Autor buscarPorDni(String dni) {
		Autor nuevo= new Autor();
		Connection con= conexion.getConexion();
		if(con!=null) {
			
				PreparedStatement q;
				try {
					q = con.prepareStatement(SELECTBYDNI);
					q.setString(1, "%"+dni+"%");
					ResultSet rs=q.executeQuery();
					while(rs.next()) {
						//es que hay al menos un resultado
						
						nuevo.setNombre(rs.getString("nombre"));
						nuevo.setEdad(rs.getInt("edad"));
						nuevo.setDni(rs.getString("dni"));
						nuevo.setDescripcion(rs.getString("descripcion"));
						nuevo.setObras(null);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
									
			
		}
		return nuevo;
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
							nuevo.setEdad(rs.getInt("edad"));
							nuevo.setDni(rs.getString("dni"));
							nuevo.setDescripcion(rs.getString("descripcion"));
							nuevo.setObras(null);
							todos.add(nuevo);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
										
				
			}
		
		
		return todos;
		
	}
	
	/*public static List<Autor> listartodos() {
		List<Coleccion> listaColeccion= new ArrayList<Coleccion>();
		listaColeccion.add(new Coleccion("Vengadores"," stan lee", "123d2", 554, false));
		listaColeccion.add(new Coleccion("popeye", "mariano", "9493j", 23, false));
		
		
		List<Autor> listaautores = new ArrayList<>();
		listaautores.add(new Autor("Stan lee", 80, "11111s", "Creador de varios personajes de marvel comics", listaColeccion));
		listaautores.add(new Autor("manolo", 80, "111441d", "Creador de varios personajes de marvel comics", listaColeccion));

		return listaautores;
	}*/
}
