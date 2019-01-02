package packControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import packModelo.BarBestial;
import packVista.VentanaMenuInicial;
import packVista.VentanaRegistro;

public class ControladorRegistro implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		String email = VentanaRegistro.getVentanaRegistro().getTextFieldEmailValue();
		String nombre = VentanaRegistro.getVentanaRegistro().getTextFieldNombreValue();
		String password = VentanaRegistro.getVentanaRegistro().getTextFieldPasswordValue();
		String password2 = VentanaRegistro.getVentanaRegistro().getTextFieldPassword2Value();
		
		if(action.equals("aceptar")) { 
			if (email.length()<=0 || nombre.length()<=0 || password.length()<=0 || password2.length()<=0){
				 JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
			
				try{
					if (BarBestial.getBarBestial().existeUsuarioNombre(nombre)){
						 JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						 if (!password.equals(password2)){ 
							 JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE); 
						 } else {
							 BarBestial.getBarBestial().registrarUsuario(email, nombre, password);
							 Controlador c = new Controlador();
							 c.iniciarAplicacion();
							 VentanaRegistro.getVentanaRegistro().dispose();
						 } 
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese email", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		} else if (action.equals("cancelar")) {
			VentanaMenuInicial.getVentanaMenuInicial().setVisible(true);
			VentanaRegistro.getVentanaRegistro().dispose();
		}
	}	
}
