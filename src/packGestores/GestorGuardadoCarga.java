package packGestores;

import java.sql.ResultSet;
import java.sql.SQLException;

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
		JSONArray bar=Bar.getMiBar().obtenerDatosBar();
		JSONArray tablero=Tablero.getMiTablero().obtenerDatosTablero();
		JSONObject datosPersonalizacion=Personalizacion.getPersonalizacion().getDatosPersonalizacion();
	}


}
