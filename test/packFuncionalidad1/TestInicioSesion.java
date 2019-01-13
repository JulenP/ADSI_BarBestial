package packFuncionalidad1;

import static org.junit.Assert.*;
import java.sql.SQLException;

import org.junit.Test;

import packGestores.GestorBD;
import packGestores.GestorUsuarios;
import packModelo.BarBestial;
import packVista.VentanaLogin;
import packVista.VentanaMenuInicial;

public class TestInicioSesion {	
	
	@Test
	public void test() {
		
		/*
		 * El caso de si hay algun campo sin rellenar se comprueba en la vista
		 */
		try {
			assertFalse(BarBestial.getBarBestial().existeUsuarioNombre("Marta"));
			assertFalse(BarBestial.getBarBestial().identificarse("marta@gmail.com", "edurne"));
				
			GestorBD.getMiGestorBD().conectar();
			GestorBD.getMiGestorBD().execSQL("insert into usuario values('marta@gmail.com', 'Marta', 'marta', 0)");
			GestorBD.getMiGestorBD().cerrarConexion();
				
			assertEquals(GestorUsuarios.getGestorUsuarios().getNombreUsuario("marta@gmail.com"), "Marta");
			assertFalse(BarBestial.getBarBestial().identificarse("ejemplo", "ej"));	// Usuario incorrecto y contraseña incorrecta
			assertFalse(BarBestial.getBarBestial().identificarse("ejemplo", "marta"));	// Usuario incorrecto y contraseña correcta
			assertFalse(BarBestial.getBarBestial().identificarse("marta@gmail.com", "1234")); // Usuario correcto y contraseña incorrecta
			
			assertTrue(BarBestial.getBarBestial().existeUsuarioNombre("Marta"));
			assertNull(BarBestial.getBarBestial().obtenerEmailUsuarioActual());	// Como todavia no se ha identificado, el email es null
			assertTrue(BarBestial.getBarBestial().identificarse("marta@gmail.com", "marta"));	//Usuario correcto y contraseña correcta
			assertNotNull(BarBestial.getBarBestial().obtenerEmailUsuarioActual()); // Una vez identificado se añade al usuario a la partida
				
			GestorBD.getMiGestorBD().conectar();
			GestorBD.getMiGestorBD().execSQL("delete from usuario where email='marta@gmail.com'");
			GestorBD.getMiGestorBD().cerrarConexion();
			
			assertFalse(VentanaMenuInicial.getVentanaMenuInicial().isActive());	//La ventana  menu inicial no esta abierta
			VentanaLogin.getVentanaLogin().getCancelarButton().doClick();
			assertTrue(VentanaMenuInicial.getVentanaMenuInicial().isActive());	//La ventana  menu inicial se ha abierto
			assertFalse(VentanaLogin.getVentanaLogin().isActive());				// La ventana  login se ha cerrado
		
				
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
