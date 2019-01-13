package packGestores;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import packExcepciones.nombreUsado;
import packModelo.Bar;
import packModelo.Partida;
import packModelo.Personalizacion;
import packModelo.Tablero;

public class GestorGuardadoCarga {
	private static GestorGuardadoCarga miGestorGuardadoCarga;
	public static GestorGuardadoCarga getGestorGuardadoCarga() {
		if(miGestorGuardadoCarga==null) {
			miGestorGuardadoCarga=new GestorGuardadoCarga();
		}
		return miGestorGuardadoCarga;
	}
	/**
	 * quedan guardadas en la base de datos los datos necesarios de la partida,en caso de que falle algo no se añade nada a la base de datos
	 * @param nombrePartida
	 * @throws SQLException
	 * @throws nombreUsado ocurre cuando el jugador ya a usado el nombre en otra partida
	 * @throws JSONException 
	 */
	public void guardar(String nombrePartida) throws SQLException, nombreUsado, JSONException {
		String email=Partida.getMiPartida().obtenerEmail();
		ResultSet resultado;
		resultado = GestorBD.getMiGestorBD().execSQLSelect("SELECT nombrePartida FROM Partida WHERE nombrePartida='"+nombrePartida+"' AND email='"+email+"';");	
		if(!resultado.isBeforeFirst()) {
			throw new nombreUsado(); 	
		}
		JSONObject partida=Partida.getMiPartida().obtenerDatosPartida();
		String nombre=Personalizacion.getPersonalizacion().getNombre();
		JSONObject jugador=(JSONObject) partida.get("jugador");
		JSONObject cpu=(JSONObject) partida.get("cpu");
		JSONArray manoJug=jugador.getJSONArray("mano");
		JSONArray mazoJug=jugador.getJSONArray("mazo");
		JSONArray manoCpu=cpu.getJSONArray("mano");
		JSONArray mazoCpu=cpu.getJSONArray("mazo");
		JSONArray bar=Bar.getMiBar().obtenerDatosBar();
		JSONArray tablero=Tablero.getMiTablero().obtenerDatosTablero();
		GestorBD temp = GestorBD.getMiGestorBD();
		temp.conectar();
		Date date=new Date();
		DateFormat formato=new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		String fecha=formato.format(date);
		try {
			temp.addToTransaction("INSERT INTO Partida VALUES('"+nombrePartida+"','"+fecha+"','"+email+"',"+0+",'"+nombre+"');");
			for(int i=0;i<tablero.length();i++) {
				JSONObject cartaActual=(JSONObject) tablero.get(i);
				String especie=cartaActual.getString("especie");
				String color=cartaActual.getString("color");
				int posicion=cartaActual.getInt("indice");
				temp.addToTransaction("INSERT INTO Fila VALUES('"+nombrePartida+"','"+especie+"','"+color+"',"+posicion+");");
			}
			for(int i=0;i<mazoJug.length();i++) {
				JSONObject cartaActual=(JSONObject) mazoJug.get(i);
				String especie=cartaActual.getString("especie");
				String color=cartaActual.getString("color");
				int posicion=cartaActual.getInt("indice");
				temp.addToTransaction("INSERT INTO MazoJug VALUES('"+nombrePartida+"','"+especie+"','"+color+"',"+posicion+");");
			}
			for(int i=0;i<mazoCpu.length();i++) {
				JSONObject cartaActual=(JSONObject) mazoCpu.get(i);
				String especie=cartaActual.getString("especie");
				String color=cartaActual.getString("color");
				int posicion=cartaActual.getInt("indice");
				temp.addToTransaction("INSERT INTO MazoOrd VALUES('"+nombrePartida+"','"+especie+"','"+color+"',"+posicion+");");
			}
			for(int i=0;i<manoJug.length();i++) {
				JSONObject cartaActual=(JSONObject) manoJug.get(i);
				String especie=cartaActual.getString("especie");
				String color=cartaActual.getString("color");
				temp.addToTransaction("INSERT INTO ManoJug VALUES('"+nombrePartida+"','"+especie+"','"+color+"');");
			}
			for(int i=0;i<manoCpu.length();i++) {
				JSONObject cartaActual=(JSONObject) manoCpu.get(i);
				String especie=cartaActual.getString("especie");
				String color=cartaActual.getString("color");
				temp.addToTransaction("INSERT INTO ManoOrd VALUES('"+nombrePartida+"','"+especie+"','"+color+"');");
			}
			for(int i=0;i<manoCpu.length();i++) {
				JSONObject cartaActual=(JSONObject) manoCpu.get(i);
				String especie=cartaActual.getString("especie");
				String color=cartaActual.getString("color");
				temp.addToTransaction("INSERT INTO ManoOrd VALUES('"+nombrePartida+"','"+especie+"','"+color+"');");
			}
			temp.cerrarConexion();
		}catch (Exception e) {
			throw (e );
		}
		
	}


}
