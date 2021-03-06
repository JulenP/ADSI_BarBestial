package packControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import packFacebook.Facebook;
import packModelo.BarBestial;
import packVista.VentanaLogin;
import packVista.VentanaMenuInicial;
import packVista.VentanaRecuperarContrasena;
import packVista.VentanaRegistro;

public class ControladorMenuInicial implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if(action.equals("inicioSesionFB")) { 
			
		    try {
		   	 	Facebook.launch(Facebook.class);
				/*
		   	 	 *  Si el jugador esta añadido en la partida significa que se ha identificado coorectamente mediante Facebook
		   	 	 */
		    		if (BarBestial.getBarBestial().obtenerEmailUsuarioActual()!=null){
		    			VentanaMenuInicial.getVentanaMenuInicial().dispose();
		    			Controlador.getMiControlador().iniciarAplicacion();
		    			Controlador.getMiControlador().desactivarBotonCambiarContrasena();
		    		} 
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		} else if (action.equals("registro")) {
			VentanaRegistro.getVentanaRegistro().setVisible(true);
			VentanaMenuInicial.getVentanaMenuInicial().dispose();
	
		} else if (action.equals("login")) {
			VentanaLogin.getVentanaLogin().setVisible(true);
			VentanaMenuInicial.getVentanaMenuInicial().dispose();
		
		} else if (action.equals("recuperarContrasena")) {
			VentanaRecuperarContrasena.getVentanaRecuperarContrasena().setVisible(true);
			VentanaMenuInicial.getVentanaMenuInicial().dispose();
	    }
	}
}
