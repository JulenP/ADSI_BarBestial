package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VentanaMejoresJugadores extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JLabel lblRankingGlobalMejores;


	/**
	 * Create the frame.
	 * @throws JSONException 
	 */
	public VentanaMejoresJugadores(JSONArray pDatos) throws JSONException {
		setTitle("Mejores Jugadores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 514, 347);
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
	  
		//Añadir las columnas de la tabla	
		model.addColumn("JUGADOR");
		model.addColumn("PUNTUACIÓN MEDIA");
	
		//Añadir las filas a la tabla desde el JSON
		for (int i = 0; i < pDatos.length(); i++) 
		{
			org.json.JSONObject one = pDatos.getJSONObject(i);
			model.addRow(new Object[]{one.get("emailusuario"), one.get("avg(puntosjug)")});
		}
		  
		//Para centrar los datos de la tabla
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(table);
		
		lblRankingGlobalMejores = new JLabel("RANKING GLOBAL MEJORES JUGADORES");
		lblRankingGlobalMejores.setFont(new Font("Tahoma", Font.BOLD, 21));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRankingGlobalMejores))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblRankingGlobalMejores, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		table = new JTable(model);
		table.setRowSelectionAllowed(false); //no poder seleccionar filas
		table.setRowHeight(25); //anchura de las filas
		
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setBackground(UIManager.getColor("Slider.background"));
		table.setModel(model);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		
		table.getTableHeader().setPreferredSize(new java.awt.Dimension(30, 30)); //altura del header
		table.getTableHeader().setFont(new Font("Tahoma", 1, 16)); //letra del header
		table.getTableHeader().setReorderingAllowed(false); //para no cambiar las columnas de orden
		
		table.setVisible(true);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
