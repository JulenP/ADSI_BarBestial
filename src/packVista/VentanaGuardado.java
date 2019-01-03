package packVista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.TextField;
import java.awt.Label;

public class VentanaGuardado {

	private JFrame frame;
	private JTextField nombrePartida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGuardado window = new VentanaGuardado();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaGuardado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(154, 145, 114, 25);
		frame.getContentPane().add(btnAceptar);
		
		nombrePartida = new JTextField();
		nombrePartida.setBounds(40, 102, 358, 19);
		frame.getContentPane().add(nombrePartida);
		nombrePartida.setColumns(10);
		
		Label informacion = new Label("Introduce un nombre para guardarla");
		informacion.setBounds(97, 58, 243, 21);
		frame.getContentPane().add(informacion);
	}
}
