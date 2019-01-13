package packFuncionalidad2;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.junit.Test;

import packGestores.GestorBD;
import packGestores.GestorUsuarios;
import packModelo.BarBestial;
import packModelo.Partida;
import packModelo.RankingDB;
import packVista.VentanaMejoresJugadores;
import packVista.VentanaMejoresPartidas;
import packVista.VentanaPuntuacionDia;
import packVista.VentanaTusMejoresPartidas;

//EJECUTAR INDIVIDUALMENTE PARA QUE LOS DIÁLOGOS NO SE SOLAPEN ENTRE SÍ.
public class RankingDBTest {

	@Test
	public void testObtenerRankingMPU() {
		try {
			// Registramos un usuario nuevo para que no tenga partidas jugadas
			GestorUsuarios.getGestorUsuarios().registrarUsuario("x@x.com", "x", "x");
					
			// Iniciamos sesion con el usuario
			BarBestial.getBarBestial().identificarse("x@x.com", "x");
			
			// Al ejecutar, deberia saltar un error de que no hay partidas finalizadas
			RankingDB.getRankingDB().obtenerRankingMPU();
			
			//Añadimos una partida de ese jugador al ranking
			GestorBD.getMiGestorBD().conectar();
	        GestorBD.getMiGestorBD().execSQL("INSERT INTO Ranking(fecha,emailUsuario,puntosJug,puntosCPU) VALUES ('2019-01-13','x@x.com',7,2)");  		
	        
			// Al ejecutar, deberia aparecer la ventana con la informacion del ranking
	        JSONArray pDatos = RankingDB.getRankingDB().obtenerRankingMPU();
			VentanaTusMejoresPartidas v1 = new VentanaTusMejoresPartidas(pDatos);
			v1.setVisible(true);
			
			TimeUnit.SECONDS.sleep(3);
			
			// Borramos el usuario registrado y sus partidas
			GestorBD.getMiGestorBD().conectar();
			GestorBD.getMiGestorBD().execSQL("DELETE FROM usuario WHERE email = 'x@x.com'");
			GestorBD.getMiGestorBD().execSQL("DELETE FROM ranking WHERE emailUsuario = 'x@x.com'");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	@Test
	public void testObtenerRankingMPD() {
		try {
			// Registramos un usuario nuevo para que no tenga partidas jugadas
			GestorUsuarios.getGestorUsuarios().registrarUsuario("x@x.com", "x", "x");
					
			// Iniciamos sesion con el usuario
			BarBestial.getBarBestial().identificarse("x@x.com", "x");
			
			// Al ejecutar, deberia saltar un error de que no hay partidas finalizadas en el día de hoy
			RankingDB.getRankingDB().obtenerRankingMPD();
			
			//Obtenemos la fecha actual
			Date pFecha = new Date(); //Fecha Actual
	        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(pFecha);		
	        String fecha = modifiedDate.toString();
			
			//Añadimos una partida de ese jugador al ranking con la fecha actual
			GestorBD.getMiGestorBD().conectar();
	        GestorBD.getMiGestorBD().execSQL("INSERT INTO Ranking(fecha,emailUsuario,puntosJug,puntosCPU) VALUES ('"+modifiedDate+"','x@x.com',7,2)");  		
	        
			// Al ejecutar, deberia aparecer la ventana con la informacion del ranking
	        JSONArray pDatos = RankingDB.getRankingDB().obtenerRankingMPD();
			VentanaPuntuacionDia v1 = new VentanaPuntuacionDia(pDatos);
			v1.setVisible(true);
			
			TimeUnit.SECONDS.sleep(3);
			
			// Borramos el usuario registrado y sus partidas
			GestorBD.getMiGestorBD().conectar();
			GestorBD.getMiGestorBD().execSQL("DELETE FROM usuario WHERE email = 'x@x.com'");
			GestorBD.getMiGestorBD().execSQL("DELETE FROM ranking WHERE emailUsuario = 'x@x.com'");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testObtenerRankingMPG() {
		try {
			// Registramos un usuario nuevo
			GestorUsuarios.getGestorUsuarios().registrarUsuario("x@x.com", "x", "x");
					
			// Iniciamos sesion con el usuario
			BarBestial.getBarBestial().identificarse("x@x.com", "x");
			
			//Añadimos un par de partidas
			GestorBD.getMiGestorBD().conectar();
	        GestorBD.getMiGestorBD().execSQL("INSERT INTO Ranking(fecha,emailUsuario,puntosJug,puntosCPU) VALUES ('2019-01-13','x@x.com',7,2)");  		
	        GestorBD.getMiGestorBD().execSQL("INSERT INTO Ranking(fecha,emailUsuario,puntosJug,puntosCPU) VALUES ('2018-12-30','a@a.com',9,4)");  		

			// Al ejecutar, deberia aparecer la ventana con la informacion del ranking
	        JSONArray pDatos = RankingDB.getRankingDB().obtenerRankingMPG();
			VentanaMejoresPartidas v1 = new VentanaMejoresPartidas(pDatos);
			v1.setVisible(true);
			
			TimeUnit.SECONDS.sleep(3);
    		JOptionPane.showMessageDialog(null, "SE HAN BORRADO LAS PARTIDAS ACTUALES", "Info", JOptionPane.INFORMATION_MESSAGE);
			v1.dispose();
			
			//Borramos los datos del ranking para que no haya partidas finalizadas
			GestorBD.getMiGestorBD().conectar();
	        GestorBD.getMiGestorBD().execSQL("TRUNCATE ranking");
	        
			// Al ejecutar, deberia saltar un error de que no hay partidas finalizadas 
			RankingDB.getRankingDB().obtenerRankingMPG();		
			
			// Borramos el usuario registrado
			GestorBD.getMiGestorBD().conectar();
			GestorBD.getMiGestorBD().execSQL("DELETE FROM usuario WHERE email = 'x@x.com'");
			GestorBD.getMiGestorBD().cerrarConexion();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testObtenerRankingMJG() {
		try {
			// Registramos un usuario nuevo
			GestorUsuarios.getGestorUsuarios().registrarUsuario("x@x.com", "x", "x");
					
			// Iniciamos sesion con el usuario
			BarBestial.getBarBestial().identificarse("x@x.com", "x");
			
			//Añadimos unas cuantas partidas
			GestorBD.getMiGestorBD().conectar();
	        GestorBD.getMiGestorBD().execSQL("INSERT INTO Ranking(fecha,emailUsuario,puntosJug,puntosCPU) VALUES ('2019-01-13','x@x.com',7,2)");  		
	        GestorBD.getMiGestorBD().execSQL("INSERT INTO Ranking(fecha,emailUsuario,puntosJug,puntosCPU) VALUES ('2018-12-30','a@a.com',9,4)");  		
	        GestorBD.getMiGestorBD().execSQL("INSERT INTO Ranking(fecha,emailUsuario,puntosJug,puntosCPU) VALUES ('2019-01-13','b@b.com',5,3)");  		
	        GestorBD.getMiGestorBD().execSQL("INSERT INTO Ranking(fecha,emailUsuario,puntosJug,puntosCPU) VALUES ('2018-12-30','a@a.com',4,2)");  		
	        GestorBD.getMiGestorBD().execSQL("INSERT INTO Ranking(fecha,emailUsuario,puntosJug,puntosCPU) VALUES ('2018-12-30','x@x.com',9,1)");
	        
			// Al ejecutar, deberia aparecer la ventana con la informacion del ranking
	        JSONArray pDatos = RankingDB.getRankingDB().obtenerRankingMJG();
			VentanaMejoresJugadores v1 = new VentanaMejoresJugadores(pDatos);
			v1.setVisible(true);
			
			TimeUnit.SECONDS.sleep(3);
    		JOptionPane.showMessageDialog(null, "SE HAN BORRADO LAS PARTIDAS ACTUALES", "Info", JOptionPane.INFORMATION_MESSAGE);
			v1.dispose();
			
			//Borramos los datos del ranking para que no haya partidas finalizadas
			GestorBD.getMiGestorBD().conectar();
	        GestorBD.getMiGestorBD().execSQL("TRUNCATE ranking");
	        
			// Al ejecutar, deberia saltar un error de que no hay partidas finalizadas 
			RankingDB.getRankingDB().obtenerRankingMJG();		
			
			// Borramos el usuario registrado
			GestorBD.getMiGestorBD().conectar();
			GestorBD.getMiGestorBD().execSQL("DELETE FROM usuario WHERE email = 'x@x.com'");
			GestorBD.getMiGestorBD().cerrarConexion();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
