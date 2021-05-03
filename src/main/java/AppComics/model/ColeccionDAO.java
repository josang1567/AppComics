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
	// codigo usado para mostrar los comics seguin el codigo

	private final static String GETBYCODIGO = "SELECT titulo,creador,codigo ,total_paginas,leido FROM  Coleccion WHERE codigo=";

	// string usado para insertar comnics
	private final static String INSERTUPDATE = "INSERT INTO  comic(titulo,creador,codigo ,total_paginas,leido) "
			+ "VALUES (?,?,?,?,?) " + "ON DUPLICATE KEY UPDATE titulo=?,creador=?,total_paginas=?,leido=?";

	// eliminar
	private final static String DELETE = "DELETE FROM Coleccion WHERE codigo=?";

	// seleccionar por nombre
	private final static String SELECTBYTITLE = "SELECT * FROM Coleccion WHERE  titulo like ?";
	// seleccionar por creador;
	private final static String SELECTBYCREADOR = "SELECT * FROM Coleccion WHERE  creador like ?";

	public ColeccionDAO(String titulo, String creador, String codigo, int total_paginas, boolean leido) {
		super(titulo, creador, codigo, total_paginas, leido);
	}

	public ColeccionDAO() {
		super();
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
					this.codigo = rs.getString("codigo");
					this.total_paginas = rs.getInt("total_paginas");
					this.leido = rs.getBoolean("leido");
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
						q.setString(3, this.codigo);
						q.setInt(4, this.total_paginas);
						q.setBoolean(5, this.leido);
						//on duplicate
						q.setString(6, this.titulo);
						q.setString(7, this.creador);
						q.setInt(8, this.total_paginas);
						q.setBoolean(9, this.leido);
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
				q.setString(1, this.codigo);
				rs =q.executeUpdate();
				this.titulo = "Desconocido";
				this.creador = "Desconocido";
				this.codigo="00000";
				this.total_paginas = 0;
				this.leido = false;
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
				PreparedStatement q=con.prepareStatement(SELECTBYTITLE);
				q.setString(1, "%"+nombre+"%");
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					//es que hay al menos un resultado
					Coleccion a=new Coleccion();
					a.setCreador(rs.getString("creador"));
					a.setLeido(rs.getBoolean("leido"));
					a.setCodigo(rs.getString("codigo"));
					a.setTitulo(rs.getString("titulo"));
					a.setTotal_paginas(rs.getInt("total_paginas"));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public ColeccionDAO(Coleccion c) {
		this.titulo = c.titulo;
		this.creador = c.creador;
		this.codigo = c.codigo;
		this.total_paginas = c.total_paginas;
		this.leido = c.leido;
	}

}
