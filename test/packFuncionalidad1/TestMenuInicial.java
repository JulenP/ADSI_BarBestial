package packFuncionalidad1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import packVista.VentanaLogin;
import packVista.VentanaMenuInicial;
import packVista.VentanaRecuperarContrasena;
import packVista.VentanaRegistro;

public class TestMenuInicial {
	
	private VentanaMenuInicial menu;

	@Before
	public void setUp() throws Exception {
		 menu = VentanaMenuInicial.getVentanaMenuInicial();
	}

	@Test
	public void testClick() {
		
		menu.getLoginButton().doClick();
		assertTrue(VentanaLogin.getVentanaLogin().isActive());	//La ventana login se ha abierto
		assertFalse(menu.isActive());							// La ventana menu inicial se ha cerrado
		
		menu.getRegistroButton().doClick();
		assertTrue(VentanaRegistro.getVentanaRegistro().isActive());	//La ventana registro se ha abierto
		assertFalse(menu.isActive());									// La ventana menu inicial se ha cerrado
		
		menu.getRecuperarButton().doClick();
		assertTrue(VentanaRecuperarContrasena.getVentanaRecuperarContrasena().isActive());	//La ventana recuperar contraseña se ha abierto
		assertFalse(menu.isActive());														// La ventana menu inicial se ha cerrado
	}
}
