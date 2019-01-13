package packTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import packGestores.GestorUsuarios;
import packModelo.BarBestial;
import packModelo.RankingDB;

public class RankingDBTest {

	@Test
	public void testObtenerRankingMPU() {
		//Registramos un usuario nuevo para que no tenga partidas jugadas
		try {
			GestorUsuarios.getGestorUsuarios().registrarUsuario("x@x.com", "x", "x");
			System.out.println(BarBestial.getBarBestial().obtenerEmailUsuarioActual());
			
			// Al ejecutar, debería saltar un error de que no hay partidas finalizadas
			RankingDB.getRankingDB().obtenerRankingMPU();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testObtenerRankingMPD() {
		fail("Not yet implemented");
	}

	@Test
	public void testObtenerRankingMPG() {
		fail("Not yet implemented");
	}

	@Test
	public void testObtenerRankingMJG() {
		fail("Not yet implemented");
	}

}
