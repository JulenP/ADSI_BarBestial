package packControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import packFacebook.Facebook;
import packModelo.BarBestial;
import packVista.VentanaLogin;
import packVista.VentanaMenuInicial;
import packVista.VentanaRecuperarContraseña;
import packVista.VentanaRegistro;

public class ControladorMenuInicial implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if(action.equals("inicioSesionFB")) { 
			
		    try {
		   	 	VentanaMenuInicial.getVentanaMenuInicial().dispose();
		   	 	Facebook.launch(Facebook.class);
		   	 	if (BarBestial.getBarBestial().obtenerEmailUsuarioActual()!=null){
		    			Controlador c = new Controlador();
					c.iniciarAplicacion();
		   	 	} else {
		    			VentanaMenuInicial.getVentanaMenuInicial().setVisible(true);
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
			VentanaRecuperarContraseña.getVentanaRecuperarContrasena().setVisible(true);
			VentanaMenuInicial.getVentanaMenuInicial().dispose();
	    }
	}
}
