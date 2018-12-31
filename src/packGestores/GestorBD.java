package packGestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GestorBD {
	
	private static GestorBD miGestorBD;
	private Connection myConn;
	private Statement myStmt;

	private GestorBD() {
	}

	public static GestorBD getMiGestorBD() {
		if (miGestorBD == null) {
			miGestorBD = new GestorBD();
		}
		return miGestorBD;
	}

	
	public void conectar() throws SQLException { 	// CAMBIAR LOS DATOS SEGUN LA BD Y EL USUARIO DE MYSQL
		
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombreBD", "usuario", "password");

		System.out.println("Database connection successful!\n");

		myStmt = myConn.createStatement();
	}

	public void cerrarConexion() throws SQLException {
		
		if (myStmt != null) {
			myStmt.close();
		}
	}

	/*
	 Consultas SELECT
	*/
	public ResultSet execSQLSelect(String pSentencia) throws SQLException {
		
		ResultSet myRs =  myStmt.executeQuery(pSentencia);
		return myRs;
	}

	/*
	Consultas UPDATE, INSERT Y DELETE
	*/
	public void execSQL(String pSentencia) throws SQLException {
	
		 myStmt.executeUpdate(pSentencia);
	}
}
