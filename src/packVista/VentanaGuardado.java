package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaGuardado extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGuardado frame = new VentanaGuardado();
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
	public VentanaGuardado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel campoInstruccion = new JPanel();
		contentPane.add(campoInstruccion, BorderLayout.NORTH);
		
		JLabel lblIntroduceUnNombre = new JLabel("Introduce un nombre para la Baraja");
		campoInstruccion.add(lblIntroduceUnNombre);
		
		JPanel campoText = new JPanel();
		contentPane.add(campoText, BorderLayout.CENTER);
		campoText.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(113, 45, 225, 19);
		campoText.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(164, 76, 114, 25);
		campoText.add(btnNewButton);
	}
}
