package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

public class VentanaPuntuacionDia extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JLabel lblMejorPuntuacinDel;

	/**
	 * Create the frame.
	 * @throws JSONException 
	 */
	public VentanaPuntuacionDia(JSONArray pDatos) throws JSONException {
		setResizable(false);
		setTitle("Mejor Puntuacion Dia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		model = new DefaultTableModel()
		{
			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) 
			{
			    return false;
			}
		};
	  
		//A�adir las columnas de la tabla	
		model.addColumn("JUGADOR");
		model.addColumn("PUNTUACI\u00d3N D\u00cdA");
	
		//A�adir las filas a la tabla desde el JSON
		for (int i = 0; i < pDatos.length(); i++) 
		{
			org.json.JSONObject one = pDatos.getJSONObject(i);
			model.addRow(new Object[]{one.get("emailusuario"), one.get("puntosjug")});
		}
		  
		//Para centrar los datos de la tabla
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(table);
		
		lblMejorPuntuacinDel = new JLabel("MEJOR PUNTUACI\u00D3N DEL D\u00CDA");
		lblMejorPuntuacinDel.setFont(new Font("Tahoma", Font.BOLD, 21));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblMejorPuntuacinDel)
							.addGap(88))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMejorPuntuacinDel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addGap(112))
		);
		
			table = new JTable(model);
			scrollPane.setViewportView(table);
			table.getTableHeader().setReorderingAllowed(false);
			table.setRowHeight(25);
			table.setBackground(UIManager.getColor("Slider.background"));
			table.setRowSelectionAllowed(false);
			table.setShowVerticalLines(false);
			table.setShowHorizontalLines(false);
			table.setFont(new Font("Tahoma", Font.PLAIN, 16));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setModel(model);
			table.getColumnModel().getColumn(0).setCellRenderer(tcr);
			table.getColumnModel().getColumn(1).setCellRenderer(tcr);
			
			table.getTableHeader().setPreferredSize(new java.awt.Dimension(30, 30)); //altura del header
			table.getTableHeader().setFont(new Font("Tahoma", 1, 16)); //letra del header
			
		table.setVisible(true);
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

}
