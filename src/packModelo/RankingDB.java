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
    	// Se conecta
    	GestorBD.getMiGestorBD().conectar();
    	
    	JSONArray datos = null;
    	
    	String pEmail = "a@a.com";//obtenerEmailUsuarioActual();
	
        String query = "SELECT * FROM rankingdb WHERE emailUsuario = '"+pEmail+"'";
        ResultSet result = GestorBD.getMiGestorBD().execSQLSelect(query);
        
        if (!result.next())
    	{
    		JOptionPane.showMessageDialog(null, "No tienes partidas finalizadas", "Error", JOptionPane.ERROR_MESSAGE);
    		return null;
        }
    	else
    	{
    		result = GestorBD.getMiGestorBD().execSQLSelect("SELECT emailUsuario,puntosJug,fecha FROM rankingdb WHERE emailUsuario = '"+pEmail+"' ORDER BY puntosJug DESC");
    		datos = GestorBD.getMiGestorBD().sqlToJSON(result);
    		
    		//Se cierra
        	GestorBD.getMiGestorBD().cerrarConexion();
 
        	return datos;
    	}
      

    }
    
    //RANKING MEJOR PUNTUACION DIA
    public JSONArray obtenerRankingMPD() throws Exception
    {
    	// Se conecta
    	GestorBD.getMiGestorBD().conectar();
    	
    	JSONArray datos = null;
    	
        Date pFecha = new Date(); //obtenerFechaActual();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(pFecha);
    	
        String query = "SELECT * FROM rankingdb WHERE fecha = '"+modifiedDate+"';";
        ResultSet result = GestorBD.getMiGestorBD().execSQLSelect(query);
        
        if (!result.next())
    	{
    		JOptionPane.showMessageDialog(null, "No hay partidas finalizadas en este día", "Error", JOptionPane.ERROR_MESSAGE);
    		return null;
        }
    	else
    	{
    		result = GestorBD.getMiGestorBD().execSQLSelect("SELECT emailUsuario,puntosJug FROM rankingDB WHERE fecha = '"+modifiedDate+"' ORDER BY puntosJug DESC LIMIT 0,1" );
    		datos = GestorBD.getMiGestorBD().sqlToJSON(result);
    		
    		//Se cierra
        	GestorBD.getMiGestorBD().cerrarConexion();
    		
        	return datos;
    		
    	}
    	

    }
    
    //RANKING MEJORES PARTIDAS GLOBAL
    public JSONArray obtenerRankingMPG() throws Exception
    {
    	// Se conecta
    	GestorBD.getMiGestorBD().conectar();
    	
    	JSONArray datos = null;
    	  	   	
        String query = "SELECT * FROM rankingdb;";
        ResultSet result = GestorBD.getMiGestorBD().execSQLSelect(query);
        
        if (!result.next())
        {
    		JOptionPane.showMessageDialog(null, "No hay partidas finalizadas", "Error", JOptionPane.ERROR_MESSAGE);
    		return null;
        }
    	else
    	{
    		result = GestorBD.getMiGestorBD().execSQLSelect("SELECT emailUsuario,puntosJug,fecha FROM rankingdb ORDER BY puntosJug DESC");
    		datos = GestorBD.getMiGestorBD().sqlToJSON(result);
    		
    		//Se cierra
        	GestorBD.getMiGestorBD().cerrarConexion();
    		
    		return datos;
    	}

    }
    
    //RANKING MEJORES JUGADORES GLOBAL
    public JSONArray obtenerRankingMJG() throws Exception
    {
    	// Se conecta
    	GestorBD.getMiGestorBD().conectar();
    	
    	JSONArray JSONMedia = null;
    	String query = "SELECT * FROM rankingdb;";
        ResultSet result = GestorBD.getMiGestorBD().execSQLSelect(query);
         
        if (!result.next())
    	{
    		JOptionPane.showMessageDialog(null, "No hay partidas finalizadas", "Error", JOptionPane.ERROR_MESSAGE);
    		return null;
        }
    	else
    	{
    		result = GestorBD.getMiGestorBD().execSQLSelect("SELECT emailusuario, AVG(puntosJug) FROM rankingdb GROUP BY emailusuario ORDER BY AVG(puntosJug) DESC");
    		JSONMedia = GestorBD.getMiGestorBD().sqlToJSON(result);
    		
    		//Se cierra
        	GestorBD.getMiGestorBD().cerrarConexion();
        	
    		return JSONMedia;
    	}

    }    
    		
}

   