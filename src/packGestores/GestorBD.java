package packGestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.simple.JSONObject;


public class GestorBD {
	private String url="jdbc:sqlite:BarBestial.db";
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
	/*jdbc:mysql://localhost:3306/nombreBD", "usuario", "password*/
	public void conectar() throws SQLException { 	
		
		myConn = DriverManager.getConnection(url);

		if(myConn.isClosed() == false)
		{
			System.out.println("Database connection successful!\n");
			myStmt = myConn.createStatement();
		}
		else
		{
			System.out.println("Error de conexion con la base de datos");
		}
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
