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
	
	//codigo para mostrar todos los comics
	private final static String GETALL="SELECT * from comic";
	
	// codigo usado para mostrar los comics seguin el codigo

	private final static String GETBYCODIGO = "SELECT titulo,num_paginas,leido,codigo,codigo_coleccion FROM  comic WHERE codigo=";

	// string usado para insertar comnics
	private final static String INSERTUPDATE = "INSERT INTO  comic(titulo,num_paginas,leido,codigo,codigo_coleccion) "
			+ "VALUES (?,?,?,?,?) " + "ON DUPLICATE KEY UPDATE titulo=?,Num_paginas=?";

	// eliminar
	private final static String DELETE = "DELETE FROM comic WHERE codigo=?";

	// seleccionar por nombre
	private final static String SELECTBYTITLE = "SELECT * FROM comic WHERE  titulo like ?";
	private final static String SELECTBYCOLECCION = "SELECT * FROM comic WHERE  codigo_coleccion like ?";
	public ComicDao(String titulo, int num_paginas, boolean leido, String codigo, String codigo_coleccion) {
		super(titulo, num_paginas, leido, codigo,codigo_coleccion);
	}

	public ComicDao() {
		super();
	}

	public ComicDao(Comic c) {
		this.Titulo = c.Titulo;
		this.Num_paginas = c.Num_paginas;
		this.Leido = c.Leido;
		this.codigo = c.codigo;
	}

	public ComicDao(String codigo) {
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
					this.Titulo = rs.getString("Titulo");
					this.Num_paginas = rs.getInt(Num_paginas);
					this.Leido = rs.getBoolean(leido(Leido));
					this.codigo = rs.getString("codigo");
					this.codigo_coleccion=rs.getString("codigo_coleccion");
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
						q.setString(1, this.Titulo);
						q.setInt(2, this.Num_paginas);
						q.setBoolean(3, this.Leido);
						q.setString(4, this.codigo);
						q.setString(5, this.codigo_coleccion);
						//on duplicate
						q.setString(6, this.Titulo);
						q.setInt(7, this.Num_paginas);
						
						rs =q.executeUpdate();		
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return rs;
	}
	
	public int eliminar(String codigo) {
		int rs=0;
		Connection con = conexion.getConexion();
		System.out.println(codigo);
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(DELETE);
				q.setString(1, codigo);
				
				rs =q.executeUpdate();
				Titulo = "Desconocido";
				Num_paginas = 0;
				Leido = false;
				this.codigo = "Desconocido";
				codigo_coleccion="0";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public static List<Comic> buscaPorNombre(String nombre) {
		List<Comic> result=new ArrayList<Comic>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTBYTITLE);
				q.setString(1, "%"+nombre+"%");
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					
					Comic a=new Comic();
					a.setTitulo(rs.getString("Titulo"));
					a.setCodigo(rs.getString("codigo"));
					a.setNum_paginas(rs.getInt("Num_paginas"));
					a.setLeido(rs.getBoolean("Leido"));
					a.setCodigo_coleccion(rs.getString("codigo_coleccion"));
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
		List<Comic> result=new ArrayList<Comic>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(SELECTBYCOLECCION);
				q.setString(1, "%"+coleccion+"%");
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					
					Comic a=new Comic();
					a.setTitulo(rs.getString("Titulo"));
					a.setCodigo(rs.getString("codigo"));
					a.setNum_paginas(rs.getInt("Num_paginas"));
					a.setLeido(rs.getBoolean("Leido"));
					a.setCodigo_coleccion(rs.getString("codigo_coleccion"));
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
		List<Comic> result=new ArrayList<Comic>();
		Connection con = conexion.getConexion();
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(GETALL);
				ResultSet rs=q.executeQuery();
				while(rs.next()) {
					
					Comic a=new Comic();
					a.setTitulo(rs.getString("Titulo"));
					a.setCodigo(rs.getString("codigo"));
					a.setNum_paginas(rs.getInt("Num_paginas"));
					a.setLeido(rs.getBoolean("Leido"));
					a.setCodigo_coleccion(rs.getString("codigo_coleccion"));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}


	/*
	public static List<Comic> listartodos() {
		List<Comic> listacomics = new ArrayList<>();
		listacomics.add(new Comic("comic 1", 12, false, "0001","0001"));
		listacomics.add(new Comic("comic 2", 12, false, "0002","0002"));
		return listacomics;
	}*/
}
