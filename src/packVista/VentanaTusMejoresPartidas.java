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
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

import packControlador.Controlador;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class VentanaTusMejoresPartidas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the frame.
	 * @throws JSONException 
	 */
	public VentanaTusMejoresPartidas(JSONArray pDatos) throws JSONException {
		setResizable(false);
		setTitle("Tus Mejores Partidas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 337);
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
	  
		//Aï¿½adir las columnas de la tabla	
		model.addColumn("JUGADOR");
		model.addColumn("PUNTUACI\u00d3N");
		model.addColumn("FECHA");
	
		//Añadir las filas a la tabla desde el JSON
		for (int i = 0; i < pDatos.length(); i++) 
		{
		 org.json.JSONObject one = pDatos.getJSONObject(i);
		 model.addRow(new Object[]{one.get("emailusuario"), one.get("puntosjug"), one.get("fecha")});
		}
		  
		//Para centrar los datos de la tabla
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(table);
		
		table = new JTable(model);
		table.setRowSelectionAllowed(false); //no poder seleccionar filas
		table.setRowHeight(25); //anchura de las filas
		scrollPane.setViewportView(table);
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setBackground(UIManager.getColor("Slider.background"));
			
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		
		table.getTableHeader().setPreferredSize(new java.awt.Dimension(30, 30)); //altura del header
		table.getTableHeader().setFont(new Font("Tahoma", 1, 16)); //letra del header
		table.getTableHeader().setReorderingAllowed(false); //para no cambiar las columnas de orden

		table.setVisible(true);
		
		JLabel lblRankingTusMejores = new JLabel("RANKING TUS MEJORES PARTIDAS");
		lblRankingTusMejores.setFont(new Font("Tahoma", Font.BOLD, 21));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblRankingTusMejores)
							.addGap(65))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRankingTusMejores)
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	
	/* Centrar */
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = getSize(); //TamaÃ±o del frame actual (ancho x alto)
    if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
    }
    setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	
	}
}
