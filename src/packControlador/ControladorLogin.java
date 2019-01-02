package packControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import packModelo.BarBestial;
import packVista.VentanaLogin;
import packVista.VentanaMenuInicial;
import org.json.JSONException;

public class ControladorLogin implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		String email = VentanaLogin.getVentanaLogin().getTextFieldEmailValue();
		String password = VentanaLogin.getVentanaLogin().getTextFieldPasswordValue();
		
		if(action.equals("aceptar")) { 
			
			if (email.length()<=0  || password.length()<=0 ){
				 JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			} else{
				try {
					if (!BarBestial.getBarBestial().identificarse(email, password)){
						JOptionPane.showMessageDialog(null, "Los datos no son correctos", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						VentanaLogin.getVentanaLogin().dispose();
						Controlador c = new Controlador();
						c.iniciarAplicacion();
					}
				} catch (SQLException | JSONException e1) {
					e1.printStackTrace();
				}
			}
	
		} else if (action.equals("cancelar")) {
			VentanaMenuInicial.getVentanaMenuInicial().setVisible(true);
			VentanaLogin.getVentanaLogin().dispose();
		}	
	}
}
