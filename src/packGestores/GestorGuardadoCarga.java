package packGestores;


import java.sql.ResultSet;
import java.sql.SQLException;
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
	 * 
	 * @param nombrePartida
	 * @throws SQLException
	 * @throws nombreUsado ocurre cuando el jugador ya a usado el nombre en otra partida
	 * @throws JSONException 
	 */
	public void guardar(String nombrePartida) throws SQLException, nombreUsado, JSONException {
		String email=Partida.getMiPartida().obtenerEmail();
		ResultSet resultado;
		resultado = GestorBD.getMiGestorBD().execSQLSelect("SELECT nombrePartida FROM Partida WHERE nombrePartida="+nombrePartida+" AND email="+email);	
		if(!resultado.isBeforeFirst()) {
			throw new nombreUsado(); 	
		}
		JSONObject partida=Partida.getMiPartida().obtenerDatosPartida();
		JSONObject datosPersonalizacion=Personalizacion.getPersonalizacion().getDatosPersonalizacion();
		JSONObject jugador=(JSONObject) partida.get("jugador");
		JSONObject cpu=(JSONObject) partida.get("cpu");
		JSONArray manoJug=jugador.getJSONArray("mano");
		JSONArray mazoJug=jugador.getJSONArray("mazo");
		JSONArray manoCpu=cpu.getJSONArray("mano");
		JSONArray mazoCpu=cpu.getJSONArray("mazo");
		JSONArray bar=Bar.getMiBar().obtenerDatosBar();
		JSONArray tablero=Tablero.getMiTablero().obtenerDatosTablero();
		String nombrePersonalizacion=datosPersonalizacion.getString("nombre");
		String path=datosPersonalizacion.getString("path");
		GestorBD temp = GestorBD.getMiGestorBD();
		Date date=new Date();
		String fecha=date.toGMTString();
		temp.execSQL("INSERT INTO Partida VALUES("+nombrePartida+","+fecha+","+email+","+0+","+nombrePersonalizacion+")");
		for(int i=0;i<tablero.length();i++) {
			JSONObject cartaActual=(JSONObject) tablero.get(i);
			String especie=cartaActual.getString("especie");
			String color=cartaActual.getString("color");
			int posicion=cartaActual.getInt("indice");
			temp.execSQL("INSERT INTO Fila VALUES("+nombrePartida+","+especie+","+color+","+posicion+")");
		}
	}


}
