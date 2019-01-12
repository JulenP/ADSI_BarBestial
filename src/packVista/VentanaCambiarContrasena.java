package packVista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packControlador.ControladorCambiarContrasena;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;

public class VentanaCambiarContrasena extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordFieldAntigua;
	private JPasswordField passwordFieldNueva;
	private JPasswordField passwordFieldNueva2;
	private JButton btnAceptar;
	private JButton btnCancelar;

	
	private static VentanaCambiarContrasena miVentanaCambioContrasena;
	
	public static VentanaCambiarContrasena getVentanaCambioContrasena() {
		if (miVentanaCambioContrasena == null) {
			miVentanaCambioContrasena = new VentanaCambiarContrasena();
		}
		return miVentanaCambioContrasena;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCambiarContrasena frame = new VentanaCambiarContrasena();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCambiarContrasena() {
		setTitle("Cambio de contraseña");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblContrasenaAntigua = new JLabel("Contraseña antigua");
		
		JLabel lblContrasenaNueva = new JLabel("Nueva Contraseña");
		
		JLabel lblConfirmarContrasena = new JLabel("Confirmar Contraseña ");
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ControladorCambiarContrasena());
		btnAceptar.setActionCommand("aceptar");
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ControladorCambiarContrasena());
		btnCancelar.setActionCommand("cancelar");
		
		passwordFieldAntigua = new JPasswordField();
		
		passwordFieldNueva = new JPasswordField();
		
		passwordFieldNueva2 = new JPasswordField();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAceptar, Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblContrasenaAntigua, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblContrasenaNueva, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblConfirmarContrasena, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(86)
							.addComponent(btnCancelar))
						.addComponent(passwordFieldNueva, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
						.addComponent(passwordFieldNueva2, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordFieldAntigua, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)))
					.addGap(33))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordFieldAntigua, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContrasenaAntigua, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(lblContrasenaNueva)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(passwordFieldNueva2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblConfirmarContrasena)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(passwordFieldNueva, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		 /* Centrar */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize(); //Tamaño del frame actual (ancho x alto)
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	}
	
	public String getTextFieldPasswordAntiguaValue() {
        return passwordFieldAntigua.getText();
    }
	
	public String getTextFieldPasswordNuevaValue() {
        return passwordFieldNueva.getText();
    }
	
	public String getTextFieldPasswordNueva2Value() {
        return passwordFieldNueva2.getText();
    }
	
	public JButton getCancelarButton(){
		return btnCancelar;
	}
}
