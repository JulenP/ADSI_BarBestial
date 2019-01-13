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
	
	/*
	 * Comprueba en la base de datos si existe un usuario que tenga ese email y esa contraseña
	*/
	public boolean comprobarDatosUsuario(String pEmail, String pContrasena) throws SQLException{
		
		GestorBD.getMiGestorBD().conectar();
		
		ResultSet myRes = GestorBD.getMiGestorBD().execSQLSelect("select * from usuario where email='" +pEmail+ "' and clave='" +pContrasena+ "'");
		boolean res = myRes.next();	// Si devuelve false es que no hay ningun usuario con esos datos
		myRes.close();	
		
		GestorBD.getMiGestorBD().cerrarConexion();	
		return res;
	}
	
	/*
	* Cambia la contraseña en la BD. 
	*/
	
	public void cambiarContrasena(String pEmail, String pContrasena) throws SQLException{
		
		GestorBD.getMiGestorBD().conectar();
		
		GestorBD.getMiGestorBD().execSQL("update usuario set clave='" +pContrasena+ "' where email='" +pEmail+ "'");
		
		GestorBD.getMiGestorBD().cerrarConexion();	
	}
	
	/*
	* Comprueba si existe un usuario con ese email en la BD
	*/
	
	public boolean existeUsuario(String pEmail) throws SQLException{
		
		GestorBD.getMiGestorBD().conectar();
		
		ResultSet myRes = GestorBD.getMiGestorBD().execSQLSelect("select * from usuario where email='" +pEmail+ "'");
		boolean res = myRes.next(); // Si devuelve false es que no hay ningun usuario con ese email
		myRes.close();	
		
		GestorBD.getMiGestorBD().cerrarConexion();	
		return res;
	}
	
	/*
	* Comprueba si existe un usuario con ese nombre en la BD
	*/
	
	public boolean existeUsuarioNombre(String pNombre) throws SQLException{
		
		GestorBD.getMiGestorBD().conectar();
		
		ResultSet myRes = GestorBD.getMiGestorBD().execSQLSelect("select * from usuario where nombre='" +pNombre+ "'");
		boolean res = myRes.next();	// Si devuelve false es que no hay ningun usuario con ese nombre
		myRes.close();
		
		GestorBD.getMiGestorBD().cerrarConexion();	
		return res;
	}
	
	/*
	* Envia la contraseña al email utilizando la libreria Java Mail
	*/
	public void enviarContrasenaAlCorreo(String pEmail, String pContrasena) throws MessagingException{ 
						
		// Se añaden los datos del email que se va a usar para enviar el correo																								
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
		 //Se indica que mensaje y a quien se le va a enviar
	    message.setFrom(new InternetAddress("proyectoAdsi1819"));
	    message.addRecipients(Message.RecipientType.TO, pEmail);   // Si el email no tiene un formato "ej@ej.com" lanza la excepcion MessagingException
	    message.setSubject("Recuperación contraseña BarBestial");
	    message.setText("Tu contraseña nueva es:  " + pContrasena);
	    Transport transport = session.getTransport("smtp");
	   	transport.connect("smtp.gmail.com", "proyectoadsi1819", "1234adsi");
	    transport.sendMessage(message, message.getAllRecipients());
	    transport.close();
	}
	
	/*
	* Añade un nuevo usuario a la BD. Si no se lanza ninguna excepcion significa que se ha registrado correctamente
	*/
	public void registrarUsuario(String pEmail, String pNombre, String pContrasena) throws SQLException{
	
		GestorBD.getMiGestorBD().conectar();
		
		GestorBD.getMiGestorBD().execSQL("insert into usuario(email,nombre,clave,puntosayuda) values('" +pEmail+ "','" +pNombre+ "','" +pContrasena+ "',0)");

		GestorBD.getMiGestorBD().cerrarConexion();	
	}
	/*
	* Devuelve el nombre del usuario que tiene como email el pasado como parametro
	*/
	public String getNombreUsuario(String pEmail) throws SQLException{
		
		GestorBD.getMiGestorBD().conectar();
		
		ResultSet myRes = GestorBD.getMiGestorBD().execSQLSelect("select nombre from usuario where email='" +pEmail+ "'");
		myRes.next();	// Solo va a ver un usuario con ese email. Por eso no se hace un bucle el cual recorreria myRes
		String nombre = myRes.getString("nombre");
		myRes.close();	
		
		GestorBD.getMiGestorBD().cerrarConexion();	
		return nombre;
	}
}
