package packModelo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import packGestores.GestorBD;

public class RankingDB {
    private static RankingDB miRankingDB;

    private RankingDB(){}

    public static RankingDB getRankingDB() {
        if (miRankingDB == null) {
            miRankingDB = new RankingDB();
        }
        return miRankingDB;
    }

    //RANKING MEJORES PARTIDAS USUARIO
    public JSONArray obtenerRankingMPU() throws Exception
    {
    	// Se conecta a la BD
    	GestorBD.getMiGestorBD().conectar();
    	
    	JSONArray datos = null;
    	
    	// Obtenemos el email del usuario actual
    	String pEmail = BarBestial.getBarBestial().obtenerEmailUsuarioActual();
	
    	// Hacemos la consulta de datos
        ResultSet result = GestorBD.getMiGestorBD().execSQLSelect("SELECT emailUsuario,puntosJug,fecha FROM ranking WHERE emailUsuario = '"+pEmail+"' ORDER BY puntosJug DESC");
    
        // Si no tiene partidas jugadas, salta un error
        if (!result.next())
    	{
    		JOptionPane.showMessageDialog(null, "No tienes partidas finalizadas", "Error", JOptionPane.ERROR_MESSAGE);
    		return null;
        }
    	else
    	{
    	   	// Creamos el JSON con los datos que pasaremos a la vista	
    		datos = crearJSON(result);
    		
    		// Se cierra la conexion con la BD
        	GestorBD.getMiGestorBD().cerrarConexion();
 
        	return datos;
    	}
      

    }
    
    //RANKING MEJOR PUNTUACION DIA
    public JSONArray obtenerRankingMPD() throws Exception
    {
    	// Se conecta a la BD
    	GestorBD.getMiGestorBD().conectar();
    	
    	JSONArray datos = null;
    	
    	// Obtenemos la Fecha Actual
    	Date pFecha = new Date(); 
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(pFecha);
    	
        // Hacemos la consulta de datos
        ResultSet result = GestorBD.getMiGestorBD().execSQLSelect("SELECT emailUsuario,puntosJug FROM ranking WHERE fecha = '"+modifiedDate+"' ORDER BY puntosJug DESC LIMIT 0,1" );

        // Si no tiene partidas finalizadas en la fecha actual, salta un error
        if (!result.next())
    	{
    		JOptionPane.showMessageDialog(null, "No hay partidas finalizadas en el día de hoy", "Error", JOptionPane.ERROR_MESSAGE);
    		return null;
        }
    	else
    	{
    	   	// Creamos el JSON con los datos que pasaremos a la vista	
    		datos = crearJSON(result);
    		
    		// Se cierra la conexion con la BD
        	GestorBD.getMiGestorBD().cerrarConexion();
    		
        	return datos;
    		
    	}
    	

    }
    
    //RANKING MEJORES PARTIDAS GLOBAL
    public JSONArray obtenerRankingMPG() throws Exception
    {
    	// Se conecta a la BD
    	GestorBD.getMiGestorBD().conectar();
    	
    	JSONArray datos = null;
    	  	  
    	// Hacemos la consulta de datos
        ResultSet result = GestorBD.getMiGestorBD().execSQLSelect("SELECT emailUsuario,puntosJug,fecha FROM ranking ORDER BY puntosJug DESC");

        //Si no tiene partidas jugadas, salta un error
        if (!result.next())
        {
    		JOptionPane.showMessageDialog(null, "No hay partidas finalizadas", "Error", JOptionPane.ERROR_MESSAGE);
    		return null;
        }
    	else
    	{
    		// Creamos el JSON con los datos que pasaremos a la vista	
    		datos = crearJSON(result);
    		
    		//Se cierra la conexion con la BD
        	GestorBD.getMiGestorBD().cerrarConexion();
    		
    		return datos;
    	}

    }
    
    //RANKING MEJORES JUGADORES GLOBAL
    public JSONArray obtenerRankingMJG() throws Exception
    {
    	// Se conecta a la BD
    	GestorBD.getMiGestorBD().conectar();
    	
    	JSONArray JSONMedia = null;
    	
    	// Hacemos la consulta de datos
        ResultSet result = GestorBD.getMiGestorBD().execSQLSelect("SELECT emailusuario, AVG(puntosJug) FROM ranking GROUP BY emailusuario ORDER BY AVG(puntosJug) DESC");

        // Si tiene partidas jugadas, salta un error
        if (!result.next())
    	{
    		JOptionPane.showMessageDialog(null, "No hay partidas finalizadas", "Error", JOptionPane.ERROR_MESSAGE);
    		return null;
        }
    	else
    	{
    	   	// Creamos el JSON con los datos que pasaremos a la vista	
    		JSONMedia = crearJSON(result);
    		
    		//Se cierra la conexion con la BD
        	GestorBD.getMiGestorBD().cerrarConexion();
        	
    		return JSONMedia;
    	}

    }    

    //CREACION DEL JSON PARA ENVIAR LOS DATOS A LA INTERFAZ
    private JSONArray crearJSON (ResultSet resultSet) throws Exception 
	 {
		 JSONArray jsonArray = new JSONArray();
		 int total_columns = resultSet.getMetaData().getColumnCount();
		 
		 //Añadimos el primer elemento
		 JSONObject objFirst = new JSONObject();
		 for (int i = 0; i < total_columns; i++) 
	     {
	    	 objFirst.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
	    	 	 
	     }
	     
	     jsonArray.put(objFirst);
		 
	     //Continuamos añadiendo
		 while (resultSet.next()) {
	     
			 JSONObject obj = new JSONObject();
		     
		     for (int i = 0; i < total_columns; i++) 
		     {
		    	 obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
		 	 
		     }
		     
		     jsonArray.put(obj);
		     
		 }
		
		 return jsonArray;
	 }
    		
}

   
