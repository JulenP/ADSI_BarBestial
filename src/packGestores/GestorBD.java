package packGestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.simple.JSONObject;


public class GestorBD {
	private String url="jdbc:sqlite:BarBestial.bd";
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
	public void conectar() throws SQLException { 	// CAMBIAR LOS DATOS SEGUN LA BD Y EL USUARIO DE MYSQL
		
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
	
	public JSONArray sqlToJSON (ResultSet resultSet) throws Exception 
	 {
		 JSONArray jsonArray = new JSONArray();
		 while (resultSet.next()) {
			 int total_columns = resultSet.getMetaData().getColumnCount();
		     JSONObject obj = new JSONObject();
		     for (int i = 0; i < total_columns; i++) {
		    	 obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
		     }
		     jsonArray.put(obj);
		 }

		 return jsonArray;
	 }
}
