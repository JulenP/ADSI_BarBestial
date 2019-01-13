package packFuncionalidad1;

import static org.junit.Assert.*;
import java.sql.SQLException;
import javax.mail.MessagingException;
import org.junit.Test;

import packGestores.GestorBD;
import packModelo.BarBestial;
import packVista.VentanaMenuInicial;
import packVista.VentanaRecuperarContrasena;

public class TestRecuperarContrasena {
	
	@Test
	public void test() {
		
		/*
		 * El  caso de comprobar los campos sin rellenar se hace en la vista
		 */
		try {
			
			GestorBD.getMiGestorBD().conectar();
			GestorBD.getMiGestorBD().execSQL("insert into usuario values('marta@gmail.com', 'Marta', '1234', 0)");
			GestorBD.getMiGestorBD().execSQL("insert into usuario values('ejemplo', 'ejemplo', 'ej', 0)");
			GestorBD.getMiGestorBD().cerrarConexion();
			
			assertTrue(BarBestial.getBarBestial().identificarse("marta@gmail.com", "1234"));
			assertTrue(BarBestial.getBarBestial().identificarse("ejemplo", "ej"));
			
			assertFalse(BarBestial.getBarBestial().recuperarContrasena("abc@gmail.com")); // El email no existe
			
			BarBestial.getBarBestial().recuperarContrasena("marta@gmail.com");	// La contraseña se ha enviado al correo y se ha cambiado en la BD
			BarBestial.getBarBestial().recuperarContrasena("ejemplo"); // Como el formato del correo no es correcto, lanzara la excepcion

		} catch (SQLException | MessagingException e) {
			System.out.println("El formato del email no es correcto");
		}  finally {
			try {
				assertFalse(BarBestial.getBarBestial().identificarse("marta@gmail.com", "1234")); /* No te puedes identificar con la contraseña antigua porque al enviar la contraseña
																										  al correo, tambien se cambia en la BD */
				assertTrue(BarBestial.getBarBestial().identificarse("ejemplo", "ej"));	// La contraseña no se ha cambiado, ni enviado por correo porque el formato no es correcto
			
				GestorBD.getMiGestorBD().conectar();
				GestorBD.getMiGestorBD().execSQL("delete from usuario where email='marta@gmail.com'");
				GestorBD.getMiGestorBD().execSQL("delete from usuario where email='ejemplo'");
				GestorBD.getMiGestorBD().cerrarConexion();
				
				
				assertFalse(VentanaMenuInicial.getVentanaMenuInicial().isActive());						//La ventana  menu inicial no esta abierta
				VentanaRecuperarContrasena.getVentanaRecuperarContrasena().getCancelarButton().doClick();
				assertTrue(VentanaMenuInicial.getVentanaMenuInicial().isActive());						//La ventana  menu inicial se ha abierto
				assertFalse(VentanaRecuperarContrasena.getVentanaRecuperarContrasena().isActive());		// La ventana  recuperar contraseña se ha cerrado
			
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}
