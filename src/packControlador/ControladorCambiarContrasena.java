package packControlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import packModelo.BarBestial;
import packVista.VentanaCambiarContrasena;

public class ControladorCambiarContrasena implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		String passwordAntigua = VentanaCambiarContrasena.getVentanaCambioContrasena().getTextFieldPasswordAntiguaValue();
		String passwordNueva = VentanaCambiarContrasena.getVentanaCambioContrasena().getTextFieldPasswordNuevaValue();
		String passwordNueva2 = VentanaCambiarContrasena.getVentanaCambioContrasena().getTextFieldPasswordNueva2Value();
		
		if(action.equals("aceptar")) { 
			if (passwordAntigua.length()<=0  || passwordNueva.length()<=0  | passwordNueva2.length()<=0 ){
				JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			} else {			
				
				try {
					String email = BarBestial.getBarBestial().obtenerEmailUsuarioActual();
					if (!BarBestial.getBarBestial().comprobarDatosUsuario(email, passwordAntigua)){
						JOptionPane.showMessageDialog(null, "La contraseña antigua no es correcta", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						if (passwordNueva.equals(passwordNueva2)){
							BarBestial.getBarBestial().cambiarContrasena(email, passwordNueva);
							VentanaCambiarContrasena.getVentanaCambioContrasena().dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		} else if (action.equals("cancelar")) {
			VentanaCambiarContrasena.getVentanaCambioContrasena().dispose();
		}	
	}
}
