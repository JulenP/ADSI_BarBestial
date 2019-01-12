package packVista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packControlador.ControladorMenuInicial;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class VentanaMenuInicial extends JFrame {

	private JPanel contentPane;
	private JButton btnIniciarSesionRRSS;
	private JButton btnLogin;
	private JButton btnRecuperarContrasena;
	private JButton btnRegistrarse;
	
	private static VentanaMenuInicial miVentanaMenuInicial;
	
	public static VentanaMenuInicial getVentanaMenuInicial() {
		if (miVentanaMenuInicial == null) {
			miVentanaMenuInicial = new VentanaMenuInicial();
		}
		return miVentanaMenuInicial;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenuInicial frame = new VentanaMenuInicial();
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
	public VentanaMenuInicial() {
		setTitle("Menu Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnIniciarSesionRRSS = new JButton("Iniciar Sesion con Facebook");
		btnIniciarSesionRRSS.addActionListener(new ControladorMenuInicial());
		btnIniciarSesionRRSS.setActionCommand("inicioSesionFB");
		
		btnRegistrarse = new JButton("Registrarte ");
		btnRegistrarse.addActionListener(new ControladorMenuInicial());
		btnRegistrarse.setActionCommand("registro");
		
		btnLogin = new JButton("Iniciar Sesion ");
		btnLogin.addActionListener(new ControladorMenuInicial());
		btnLogin.setActionCommand("login");
		
		btnRecuperarContrasena = new JButton("Recuperar Contrase�a");
		btnRecuperarContrasena.addActionListener(new ControladorMenuInicial());
		btnRecuperarContrasena.setActionCommand("recuperarContrasena");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRecuperarContrasena, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIniciarSesionRRSS, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRegistrarse, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(95, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(btnIniciarSesionRRSS, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRegistrarse, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRecuperarContrasena, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(431, Short.MAX_VALUE))
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
	
		public JButton getLoginButton(){
		return btnLogin;
	}
	
	public JButton getRegistroButton(){
		return btnRegistrarse;
	}
	
	public JButton getRecuperarButton(){
		return btnRecuperarContrasena;
	}
}
