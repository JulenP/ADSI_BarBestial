package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Toolkit;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaElegirRanking extends JFrame {

	private JPanel contentPane;
	private JButton btnTusMejoresPartidas;
	private JButton btnPuntuacionDia;
	private JButton btnMejoresPartidas;
	private JButton btnMejoresJugadores;
	private JButton btnAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaElegirRanking frame = new VentanaElegirRanking();
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
	public VentanaElegirRanking() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 485, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		
		this.btnPuntuacionDia = new JButton("Mejor Puntuacion del Dia");
		btnPuntuacionDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		this.btnMejoresPartidas = new JButton("Global Mejores Partidas");
		btnMejoresPartidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		this.btnTusMejoresPartidas = new JButton("Tus Mejores Partidas");
		btnTusMejoresPartidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		this.btnMejoresJugadores = new JButton("Global Mejores Jugadores");
		
		this.btnAtras = new JButton("Atr\u00E1s");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnMejoresPartidas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnTusMejoresPartidas, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnMejoresJugadores, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPuntuacionDia, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
					.addGap(25))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnAtras)
					.addContainerGap(406, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnAtras)
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnTusMejoresPartidas, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(btnPuntuacionDia, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
					.addGap(73)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMejoresPartidas, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMejoresJugadores, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(57))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	
	   public void addTusPartidasListener(ActionListener listenForTusMejoresPartidas) {
		   btnTusMejoresPartidas.addActionListener(listenForTusMejoresPartidas);
	    }
	   
	   public void addMejorPuntuacionDiaListener(ActionListener listenForMejorPuntuacionDia) {
	        btnPuntuacionDia.addActionListener(listenForMejorPuntuacionDia);
	    }
	   
	   public void addMejoresPartidasListener(ActionListener listenForMejoresPartidas) {
	        btnMejoresPartidas.addActionListener(listenForMejoresPartidas);
	    }
	   
	   public void addMejoresJugadoresListener(ActionListener listenForMejoresJugadores) {
	        btnMejoresJugadores.addActionListener(listenForMejoresJugadores);
	    }
	   
	   public void addAtrasRankingListener(ActionListener listenForBtnAtras) {
	        btnAtras.addActionListener(listenForBtnAtras);
	        dispose();
	    }

}
