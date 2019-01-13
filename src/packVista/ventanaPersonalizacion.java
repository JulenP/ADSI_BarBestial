package packVista;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packGestores.GestorBD;
import packModelo.Personalizacion;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ventanaPersonalizacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPersonalizacion frame = new ventanaPersonalizacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param nombrePersonalizacion 
	 * @param nombreBaraja 
	 */
	public ventanaPersonalizacion( ) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				String nombre = getName();
				File carpeta = new File("resources/barajas/"+nombre);
				try {
					GestorBD.getMiGestorBD().conectar();
					String sentencia = "DELETE FROM personalizacion WHERE personalizacion.nombrePersonalizacion = '"+ nombre+"'";
				    GestorBD.getMiGestorBD().execSQL(sentencia);
					Personalizacion.getPersonalizacion().eliminarCarpeta(carpeta);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 751);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnMofeta = new JButton("1. MOFETA");
		btnMofeta.setIcon(null);
		btnMofeta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnMofeta.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/MofetaAzul.jpg");
					try {			
						btnMofeta.setSelected(true);
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnMofeta.setText("PERSONALIZADO");
						btnMofeta.setEnabled(false);
	
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				}
		});
		JButton btnLoro = new JButton("2. LORO");
		btnLoro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnLoro.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/LoroAzul.jpg");
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnLoro.setText("PERSONALIZADO");
						btnLoro.setEnabled(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}	
				}
			}
		});
		
		JButton btnCanguro = new JButton("3. CANGURO");
		btnCanguro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnCanguro.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/CanguroAzul.jpg");
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnCanguro.setText("PERSONALIZADO");
						btnCanguro.setEnabled(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}	
				}
			}
		});
		
		JButton btnMono = new JButton("4. MONO");
		btnMono.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnMono.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/MonoAzul.jpg");
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnMono.setEnabled(false);
						btnMono.setText("PERSONALIZADO");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton btnCamaleon = new JButton("5. CAMALEON");
		btnCamaleon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnCamaleon.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/CamaleonAzul.jpg");
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnCamaleon.setText("PERSONALIZADO");
						btnCamaleon.setEnabled(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton btnFoca = new JButton("6. FOCA");
		btnFoca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnFoca.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/FocaAzul.jpg");
					System.out.println(dirDestino.getAbsolutePath());
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnFoca.setText("PERSONALIZADO");
						btnFoca.setEnabled(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton btnCebra = new JButton("7. CEBRA");
		btnCebra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnCebra.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/CebraAzul.jpg");
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnCebra.setText("PERSONALIZADO");
						btnCebra.setEnabled(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		JButton btnSerpiente = new JButton("9. SERPIENTE");
		btnSerpiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnSerpiente.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/SerpienteAzul.jpg");
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnSerpiente.setText("PERSONALIZADO");
						btnSerpiente.setEnabled(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton btnJirafa = new JButton("8. JIRAFA");
		btnJirafa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnJirafa.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/JirafaAzul.jpg");
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						if(btnMofeta != null) {
							btnJirafa.setText("PERSONALIZADO");
							btnJirafa.setEnabled(false);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
				
		JButton btnCocodrilo = new JButton("10. COCODRILO");
		btnCocodrilo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnCocodrilo.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/CocodriloAzul.jpg");
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnCocodrilo.setText("PERSONALIZADO");
						btnCocodrilo.setEnabled(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton btnHipopotamo = new JButton("11. HIPOPOTAMO");
		btnHipopotamo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnHipopotamo.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/HipopotamoAzul.jpg");
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnHipopotamo.setText("PERSONALIZADO");
						btnHipopotamo.setEnabled(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton btnLeon = new JButton("12. LEON");
		btnLeon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnLeon.isEnabled() == true) {
					String nombre = getName();
					File dirDestino = new File("resources/barajas/"+nombre+"/LeonAzul.jpg");
					try {					
						Personalizacion.getPersonalizacion().insertarImagen(dirDestino);
						btnLeon.setText("PERSONALIZADO");
						btnLeon.setEnabled(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnMofeta.getText()== "PERSONALIZADO" && btnLoro.getText() == "PERSONALIZADO" && btnCanguro.getText() == "PERSONALIZADO" && btnMono.getText() == "PERSONALIZADO"
						&& btnCamaleon.getText() == "PERSONALIZADO" && btnFoca.getText() == "PERSONALIZADO" && btnCebra.getText() == "PERSONALIZADO" && btnSerpiente.getText() == "PERSONALIZADO" 
						&& btnJirafa.getText() == "PERSONALIZADO" && btnCocodrilo.getText() == "PERSONALIZADO" && btnHipopotamo.getText() == "PERSONALIZADO" && btnLeon.getText() == "PERSONALIZADO") {
					btnGuardar.setEnabled(true);
					VentanaInicio ventana = new VentanaInicio();
					ventana.setVisible(true);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Tienes que personalizar las 12 cartas");
					return;
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(44, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnCocodrilo, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
							.addComponent(btnCebra, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnMono, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(btnMofeta, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnJirafa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnLoro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnHipopotamo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCamaleon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnLeon, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFoca, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSerpiente, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addComponent(btnCanguro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(52))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoro, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCanguro, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMofeta, GroupLayout.PREFERRED_SIZE, 109, Short.MAX_VALUE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCamaleon, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnFoca, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnMono, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCebra, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnJirafa, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSerpiente, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnHipopotamo, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLeon, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnCocodrilo, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addComponent(btnGuardar)
					.addGap(48))
		);
		contentPane.setLayout(gl_contentPane);
	}	
}
