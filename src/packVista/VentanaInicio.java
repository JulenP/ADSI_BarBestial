package packVista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class VentanaInicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JButton btnJugar;
    private JButton btnAyuda;
    private JButton btnRanking;
    private JButton btnCambiarContrasena;
    private JButton btnPersonalizarCartas;
    
    private static VentanaInicio miVentanaInicio;
	
	public static VentanaInicio getVentanaInicio() {
		if (miVentanaInicio == null) {
			miVentanaInicio = new VentanaInicio();
		}
		return miVentanaInicio;
	}

    /**
     * Create the frame.
     */
    public VentanaInicio() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 850, 620);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
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

        JPanel panelMenu = new JPanel();
        contentPane.add(panelMenu, BorderLayout.SOUTH);
        
        btnCambiarContrasena = new JButton("Cambiar contrase\u00f1a");
        panelMenu.add(btnCambiarContrasena);
        
        
        this.btnJugar = new JButton("Jugar");
        panelMenu.add(btnJugar);
                
        btnPersonalizarCartas = new JButton("Personalizar cartas");
        panelMenu.add(btnPersonalizarCartas);
        
        this.btnAyuda = new JButton("Ayuda");
        panelMenu.add(btnAyuda);

        this.btnRanking = new JButton("Ranking");
        panelMenu.add(btnRanking);

        JPanel panelImagenBar = new JPanel();
        contentPane.add(panelImagenBar, BorderLayout.CENTER);

        JLabel labelBar = new JLabel("");
        labelBar.setIcon(new ImageIcon(VentanaInicio.class.getResource("/images/Bar.png")));
        panelImagenBar.add(labelBar);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaInicio frame = new VentanaInicio();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void addJugarListener(ActionListener listenForBtnJugar) {
        btnJugar.addActionListener(listenForBtnJugar);
    }

    public void addAyudaListener(ActionListener listenForBtnAyuda) {
        btnAyuda.addActionListener(listenForBtnAyuda);
    }

    public void addRankingListener(ActionListener listenForBtnRanking) {
        btnRanking.addActionListener(listenForBtnRanking);
    }
    
    public void addCambiarContrasenaListener(ActionListener listenForBtnCambiarContrasena) {
        btnCambiarContrasena.addActionListener(listenForBtnCambiarContrasena);
    }
    
    public void addPersonalizacionListener(ActionListener listenForBtnCPersonalizacion) {
        btnPersonalizarCartas.addActionListener(listenForBtnCPersonalizacion);
    }
    
    
    /*public String getTextFieldNombreValue() {
        return this.textFieldNombre.getText();
    }*/

   /* public void showNombreErrorMessage() {
        JOptionPane.showMessageDialog(this,
                "Introduce un nombre.");
    } */

    public void cerrarVentana() {
        setVisible(false);
        dispose();
    }
	
	 public void desactivarBotonCambiarContrasena() {
        btnCambiarContrasena.setEnabled(false);
    }
}
