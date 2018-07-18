package org.luis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.luis.connection.Connect;
import org.luis.model.Alumnos;

public class AlumnosDao {

	//
	public AlumnosDao() {

	}

	public AlumnosDao(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Connect();
	}

	public Connect con;
	private Connection connection = null;
	PreparedStatement pst = null;
	Statement st = null;
	String sql = null;
	ResultSet rs = null;

	/* Crear base de datos  NOT WORKING YET

	public void createDB() throws SQLException {
		sql = "CREATE DATABASE IF NOT EXISTS colegio";
		st = connection.createStatement();
		rs = st.executeQuery(sql);	
		rs.close();
	}
	
	public void createDB() throws SQLException {
    sql = "CREATE DATABASE IF NOT EXISTS 'programas'";
    st = connection.createStatement();
    st.executeUpdate(sql);
    System.out.println("Base de datos creada");
}
*/
	// Crear tabla

	public void createTable() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS alumnos(Nombre varchar(50),Apellidos varchar(50),Dni integer(10)), Telefono varchar(20)";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs.close();
	}
	
	

	// INSERTAR ALUMNOS

	public boolean insertar(Alumnos alumno) throws SQLException {
		sql = "INSERT INTO alumnos (Nombre, Apellidos, Dni, Telefono) VALUES (?, ?, ?,?)";
		connection = Connect.getConnection();
		pst = connection.prepareStatement(sql);
		pst.setString(1, alumno.getNombreA());
		pst.setString(2, alumno.getApellidosA());
		pst.setInt(3, alumno.getDni());
		pst.setString(4, alumno.getTel());

		boolean rowInserted = pst.executeUpdate() > 0;
		pst.close();
		Connect.close(connection);
		return rowInserted;
	}

	// ELIMINAR REGISTRO
	public boolean eliminar(int dni) throws SQLException {
		boolean respuesta = true;
		sql = "delete from alumnos where dni ='" + dni + "';";
		System.out.println(sql);
		connection = Connect.getConnection();
		st = connection.createStatement();
		st.executeUpdate(sql);
		Connect.close(connection);
		return respuesta;
	}

	// ELIMINAR TODOS LOS REGISTROS
	public void deleteAll() throws SQLException {
		sql = "Truncate table alumnos;";
		connection = Connect.getConnection();
		st = connection.createStatement();
		st.executeUpdate(sql);
		Connect.close(connection);
	}

	// LISTAR TODOS LOS PRODUCTOS
	public List<Alumnos> listarAlumnos() throws SQLException {

		List<Alumnos> listaAlumnos = new ArrayList<Alumnos>();
		sql = "SELECT * FROM alumnos";
		connection = Connect.getConnection();
		st = connection.createStatement();
		 rs = st.executeQuery(sql);

		while (rs.next()) {
			String name = rs.getString("Nombre");
			String ape = rs.getString("Apellidos");
			int dni = rs.getInt("Dni");
			String tel = rs.getString("Telefono");

			Alumnos alumnos = new Alumnos(name, ape, dni, tel);
			listaAlumnos.add(alumnos);
		}
		st.close();
		Connect.close(connection);
		return listaAlumnos;
	}

	/*
	 * actualizar public boolean actualizar(Alumnos alumnos) throws SQLException {
	 * boolean rowActualizar = false; String sql =
	 * "UPDATE articulos SET nombre=?,apellidos=?,dni=?,telefono=? WHERE id=?";
	 * connection = con.getConnection();
	 * 
	 * st = connection.prepareStatement(sql); st.setString(1, alumnos.getNombreA());
	 * st.setString(2, alumnos.getApellidosA()); st.setInt(3, alumnos.getDni());
	 * st.setString(4, alumnos.getTel()); st.setInt(5, alumnos.getId());
	 * 
	 * rowActualizar = st.executeUpdate() > 0; st.close(); con.close(connection);
	 * return rowActualizar; }
	 */
}