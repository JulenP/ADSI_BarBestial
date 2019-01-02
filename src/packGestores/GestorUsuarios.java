package packGestores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GestorUsuarios {

	private static GestorUsuarios miGestorUsuarios;

	public GestorUsuarios() {

	}

	public static GestorUsuarios getGestorUsuarios() {
		if(miGestorUsuarios==null) {
			miGestorUsuarios = new GestorUsuarios();
		}
		return miGestorUsuarios;
	}
	
	public boolean comprobarDatosUsuario(String pEmail, String pContrasena) throws SQLException{
		
		GestorBD.getMiGestorBD().conectar();
		
		ResultSet myRes = GestorBD.getMiGestorBD().execSQLSelect("select * from usuario where email='" +pEmail+ "' and clave='" +pContrasena+ "'");
		boolean res = myRes.next();
		myRes.close();	
		
		GestorBD.getMiGestorBD().cerrarConexion();	
		return res;
	}
	
	public void cambiarContrasena(String pEmail, String pContrasena) throws SQLException{
		
		GestorBD.getMiGestorBD().conectar();
		
		GestorBD.getMiGestorBD().execSQL("update usuario set clave='" +pContrasena+ "' where email='" +pEmail+ "'");
		
		GestorBD.getMiGestorBD().cerrarConexion();	
	}
	
	public boolean existeUsuario(String pEmail) throws SQLException{
		
		GestorBD.getMiGestorBD().conectar();
		
		ResultSet myRes = GestorBD.getMiGestorBD().execSQLSelect("select * from usuario where email='" +pEmail+ "'");
		boolean res = myRes.next();
		myRes.close();	
		
		GestorBD.getMiGestorBD().cerrarConexion();	
		return res;
	}
	
	public boolean existeUsuarioNombre(String pNombre) throws SQLException{
		
		GestorBD.getMiGestorBD().conectar();
		
		ResultSet myRes = GestorBD.getMiGestorBD().execSQLSelect("select * from usuario where nombre='" +pNombre+ "'");
		boolean res = myRes.next();
		myRes.close();
		
		GestorBD.getMiGestorBD().cerrarConexion();	
		return res;
	}
	
	public void enviarContrasenaAlCorreo(String pEmail, String pContrasena) throws MessagingException{ 
																										
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", "proyectoAdsi1819");
		props.put("mail.smtp.clave", "1234adsi");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		
	    message.setFrom(new InternetAddress("proyectoAdsi1819"));
	    message.addRecipients(Message.RecipientType.TO, pEmail);   
	    message.setSubject("Recuperación contraseña BarBestial");
	    message.setText("Tu contraseña nueva es:  " + pContrasena);
	    Transport transport = session.getTransport("smtp");
	   	transport.connect("smtp.gmail.com", "proyectoadsi1819", "1234adsi");
	    transport.sendMessage(message, message.getAllRecipients());
	    transport.close();
	}
	
	public void registrarUsuario(String pEmail, String pNombre, String pContrasena) throws SQLException{
	
		GestorBD.getMiGestorBD().conectar();
		
		GestorBD.getMiGestorBD().execSQL("insert into usuario(email,nombre,clave,puntosayuda) values('" +pEmail+ "','" +pNombre+ "','" +pContrasena+ "',0)");

		GestorBD.getMiGestorBD().cerrarConexion();	
	}
	
	public String getNombreUsuario(String pEmail) throws SQLException{
		
		GestorBD.getMiGestorBD().conectar();
		
		ResultSet myRes = GestorBD.getMiGestorBD().execSQLSelect("select nombre from usuario where email='" +pEmail+ "'");
		myRes.next();
		String nombre = myRes.getString("nombre");
		myRes.close();	
		
		GestorBD.getMiGestorBD().cerrarConexion();	
		return nombre;
	}
}
