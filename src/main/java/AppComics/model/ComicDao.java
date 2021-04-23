package AppComics.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AppComics.utils.conexion;

public class ComicDao extends Comic {
	// codigo usado para mostrar los comics seguin el codigo

	private final static String GETBYCODIGO = "SELECT titulo,num_paginas,leido,codigo FROM  comic WHERE codigo=";

	// string usado para insertar comnics
	private final static String INSERTUPDATE = "INSERT INTO  comic(titulo,num_paginas,leido,codigo) "
			+ "VALUES (?,?,?,?,?) " + "ON DUPLICATE KEY UPDATE titulo=?,total_paginas=?";

	// eliminar
	private final static String DELETE = "DELETE FROM comic WHERE codigo=?";

	// seleccionar por nombre
	private final static String SELECTBYTITLE = "SELECT * FROM comic WHERE  titulo like ?";

	public ComicDao(String titulo, int num_paginas, boolean leido, String codigo) {
		super(titulo, num_paginas, leido, codigo);
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
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static List<Comic> listartodos() {
		List<Comic> listacomics = new ArrayList<>();
		listacomics.add(new Comic("comic 1", 12, false, "0001"));
		listacomics.add(new Comic("comic 2", 12, false, "0002"));
		return listacomics;
	}
}
