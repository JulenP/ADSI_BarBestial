package packFuncionalidad1;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import packGestores.GestorBD;
import packGestores.GestorUsuarios;
import packModelo.BarBestial;
import packVista.VentanaMenuInicial;
import packVista.VentanaRegistro;

public class TestRegistro {
	
	@Test
	public void test() {
		
		/*
		 * El metodo registrarUsuario() deja introducir dos usuarios con el mismo nombre debido a que la clave primaria es el email.
		 * Para que no haya dos usuarios con el mismo nombre, se compureba en la vista si ya existe un usuario con ese nombre.
		 * Si las contraseñas no coinciden o hay algun campo sin rellenar, se comprueba en la vista. 
		 */
		try {
			
			assertFalse(BarBestial.getBarBestial().existeUsuarioNombre("Marta"));
			assertNull(BarBestial.getBarBestial().obtenerEmailUsuarioActual());	//Todavia no hay ningun usuario identificado
			
			BarBestial.getBarBestial().registrarUsuario("ee", "ee", "ee");	//Formato email incorrecto
			BarBestial.getBarBestial().registrarUsuario("ejemplo@gmail.com", "Marta", "1234");
			
			assertTrue(GestorUsuarios.getGestorUsuarios().existeUsuario("ee"));	//El usuario esta registrado aunque el formato del email no sea correcto
			assertTrue(BarBestial.getBarBestial().existeUsuarioNombre("ee"));	//El usuario esta registrado aunque el formato del email no sea correcto
			assertTrue(GestorUsuarios.getGestorUsuarios().existeUsuario("ejemplo@gmail.com"));
			assertTrue(BarBestial.getBarBestial().existeUsuarioNombre("Marta"));
			assertNotNull(BarBestial.getBarBestial().obtenerEmailUsuarioActual()); // Como ya se ha registrado se ha añadido al jugador a la partida
			
			BarBestial.getBarBestial().registrarUsuario("ejemplo@gmail.com", "Ejemplo", "ej"); // Lanza la excepcion porque ya existe un usuario con el mismo email
			
		} catch (SQLException e) {	
			System.out.println("Ya existe un usuario con ese email\n");
		} finally {
			
			try {
				assertFalse(BarBestial.getBarBestial().existeUsuarioNombre("Ejemplo"));	// El usuario no se ha registrado porque ha lanzado la excepcion
				
				GestorBD.getMiGestorBD().conectar();
				GestorBD.getMiGestorBD().execSQL("delete from usuario where email='ejemplo@gmail.com'");
				GestorBD.getMiGestorBD().execSQL("delete from usuario where email='ee'");
				GestorBD.getMiGestorBD().cerrarConexion();
				
				VentanaRegistro.getVentanaRegistro().getCancelarButton().doClick();
				assertTrue(VentanaMenuInicial.getVentanaMenuInicial().isActive());	// La ventana menu inicial se ha abierto
				assertFalse(VentanaRegistro.getVentanaRegistro().isActive());	// La ventana registro se ha cerrado
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
