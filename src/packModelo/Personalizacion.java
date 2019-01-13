package packModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONException;
import org.json.JSONObject;

import packGestores.GestorBD;
import packVista.ventanaNombreBaraja;
import packVista.ventanaPersonalizacion;

public class Personalizacion {
	
	private static Personalizacion miPersonalizacion;
	private String nombrePersonalizacion;
	private String path;
	
	
	
	public static Personalizacion getPersonalizacion() {
        if (miPersonalizacion == null) {
            miPersonalizacion = new Personalizacion();
        }
        return miPersonalizacion;
    }
	
	


	public void crearBaraja(File pRuta, String pNombreBaraja) throws Exception {
	 
		File baraja = new File(pRuta, pNombreBaraja);
		//La carpeta NO existe -> intenta crearla
        if (!baraja.exists( )) {
    		//Se crea la carpeta, y se guarda el nombre y el path en la bd.
	    	baraja.mkdirs();
	    	File origen = new File("resources/images");
	    	File destino = new File("resources/barajas/"+pNombreBaraja);
	    	copiarBaraja(origen, destino); //ESTE METODO ES PARA LUEGO UNIR LAS IMAGENES.
	    	GestorBD.getMiGestorBD().conectar();
	    	String sentencia = "INSERT INTO personalizacion(nombrePersonalizacion, path) VALUES ('"+pNombreBaraja+"','"+destino.getAbsolutePath()+"')";
	    //	"DELETE FROM Personalizar WHERE Personalizar.nombreBaraja = '"+ nombre+"'"
	    	GestorBD.getMiGestorBD().execSQL(sentencia);
	    	ventanaPersonalizacion ventana = new ventanaPersonalizacion();
	    	ventana.setName(pNombreBaraja);
			ventana.setVisible(true);
        }      
        else {
        	//La carpeta YA existe
        	if(baraja.exists( )) {
        		JOptionPane.showMessageDialog( null, "La carpeta ya existe.");
	        	return;
        	}
        }
	}
	
	//COPIAR UN DIRECTORIO
	 public void copiarBaraja(File srcDir, File dstDir)
	    {
	        try{
	            if (srcDir.isDirectory()) {
	                String[] children = srcDir.list();
	                for (int i=0; i < children.length; i++) {
	                    copiarBaraja(new File(srcDir, children[i]), new File(dstDir, children[i]));
	                }
	            } else {
	                copyFile(srcDir, dstDir);
	            }
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	    }
	
	 //COPIAR UN ARCHIVO
	 public void copyFile(File s, File t)
	    {
	        try{
	              FileChannel in = new FileInputStream(s).getChannel();
	              FileChannel out = new FileOutputStream(t).getChannel();
	              in.transferTo(0, s.length(), out);
	              in.close();
	              out.close();
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	    }
	 

		public void insertarImagen(File dirDestino) throws Exception { 
			
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	File origen = new File(chooser.getSelectedFile().getAbsolutePath()); //Tengo la imagen origen
		    	InputStream in = new FileInputStream(origen); 
				OutputStream out = new FileOutputStream(dirDestino); 
				
				byte[] buffer = new byte[1024];
				int len;
			 
				try {
					// recorrer el array de bytes y recomponerlo
					while ((len = in.read(buffer)) > 0) { 
						out.write(buffer, 0, len); 
					} // end while
				} catch (Exception e) {
					throw e;
				} finally {
					in.close(); 
					out.close(); 
				} // end ty*/
			}
		    else {
		    	JOptionPane.showMessageDialog(null, "Selecciona una imagen");
		    	insertarImagen(dirDestino);
		
		    }
		    
		}
		
		public void eliminarCarpeta(File carpeta) throws Exception { 
		 
		    if (carpeta.isDirectory()) { 
		        for (File f : carpeta.listFiles()) { 
		            eliminarCarpeta(f);
		        }   
		    }
		    carpeta.delete();
		    
		}




		public String getNombre() {
			
			return nombrePersonalizacion;
		}  
		
		

		     

    
}

