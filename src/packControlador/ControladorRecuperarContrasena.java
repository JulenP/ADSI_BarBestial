package packControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import packModelo.BarBestial;
import packVista.VentanaMenuInicial;
import packVista.VentanaRecuperarContraseña;

public class ControladorRecuperarContrasena implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		String email = VentanaRecuperarContraseña.getVentanaRecuperarContrasena().getTextFieldEmailValue();
		
		if(action.equals("aceptar")) { 
			if (email.length()<=0 ){
				 JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				
				try {
					if (BarBestial.getBarBestial().recuperarContrasena(email)){
						VentanaRecuperarContraseña.getVentanaRecuperarContrasena().dispose();
						VentanaMenuInicial.getVentanaMenuInicial().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Los datos no son correctos", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (SQLException | MessagingException e1) {
					JOptionPane.showMessageDialog(null, "No se ha podido enviar un mensaje con tu contraseña porque el formato del email no es correcto", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		} else if (action.equals("cancelar")) {
			VentanaMenuInicial.getVentanaMenuInicial().setVisible(true);
			VentanaRecuperarContraseña.getVentanaRecuperarContrasena().dispose();
		}
	}
}
