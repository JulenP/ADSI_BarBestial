package packFuncionalidad1;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import packGestores.GestorBD;
import packModelo.BarBestial;
import packVista.VentanaCambiarContrasena;

public class TestCambiarContrasena {

	@Test
	public void test() {
		
		/*
		 * El caso de comprobar si hay algun campo sin rellenar o si las contraseñas nuevas no coinciden se comprueba en la vista
		 */
		
		try {
			
			GestorBD.getMiGestorBD().conectar();
			GestorBD.getMiGestorBD().execSQL("insert into usuario values('marta@gmail.com', 'Marta', 'marta', 0)");
			GestorBD.getMiGestorBD().cerrarConexion();
				
			assertTrue(BarBestial.getBarBestial().identificarse("marta@gmail.com", "marta"));
			
			assertFalse(BarBestial.getBarBestial().comprobarDatosUsuario("marta@gmail.com", "qq"));	// "qq" es la contraseña que el usuario ha introducido como antigua
			assertTrue(BarBestial.getBarBestial().comprobarDatosUsuario("marta@gmail.com", "marta"));	// La contraseña antigua es correcta			
			
			BarBestial.getBarBestial().cambiarContrasena("marta@gmail.com", "1234" );	//Como la contraseña antigua es correcta y las nuevas coinciden, la contraña se cambia
			
			
			assertFalse(BarBestial.getBarBestial().identificarse("marta@gmail.com", "marta")); // Identificarse devuelve false porque la contraseña se ha cambiado	
			assertTrue(BarBestial.getBarBestial().identificarse("marta@gmail.com", "1234"));    // Con la nueva contraseña si te puedes identificar
		
			GestorBD.getMiGestorBD().conectar();
			GestorBD.getMiGestorBD().execSQL("delete from usuario where email='marta@gmail.com'");
			GestorBD.getMiGestorBD().cerrarConexion();
			
			VentanaCambiarContrasena.getVentanaCambioContrasena().getCancelarButton().doClick();
			assertFalse(VentanaCambiarContrasena.getVentanaCambioContrasena().isActive());	//La ventana de cambio de contraseña se ha cerrado
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
