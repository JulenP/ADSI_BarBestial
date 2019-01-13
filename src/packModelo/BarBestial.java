package packModelo;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;

import javax.mail.MessagingException;

import org.json.JSONException;

import packGestores.GestorGuardadoCarga;
import packGestores.GestorUsuarios;
import packExcepciones.nombreUsado;

public class BarBestial {
	
	private  static BarBestial miBarBestial;
	
	private BarBestial(){	
	}

	public static BarBestial getBarBestial(){
		if (miBarBestial==null){
			miBarBestial = new BarBestial();
		}
		return miBarBestial;
	}
	
	/*
	* Se registra un nuevo usuario en la BD y se añade a la partida
	*/
		public void registrarUsuario(String pEmail, String pNombre, String pContrasena) throws SQLException{
			GestorUsuarios.getGestorUsuarios().registrarUsuario(pEmail, pNombre, pContrasena);
			JugadorReal jugador = new JugadorReal(pEmail, pNombre, EnumColor.AZUL);	
			Partida.getMiPartida().anadirJugador(jugador);
		}
		/*
		*Se comprueba si esxiste un usuario con ese nombre
		*/
		public boolean existeUsuarioNombre(String pNombre) throws SQLException{
			return GestorUsuarios.getGestorUsuarios().existeUsuarioNombre(pNombre);
	 	}
		
		/*
		* Se cambia la contraseña del usuario al que corresponden los datos que se pasan como parametro
		*/
		public void cambiarContrasena(String pEmail, String pContrasena) throws SQLException{
			GestorUsuarios.getGestorUsuarios().cambiarContrasena(pEmail, pContrasena);
		}
	
		/*
		* Se comprueba si existe un usuario en la BD con esos datos
		*/
		public boolean comprobarDatosUsuario(String pEmail, String pContrasena) throws SQLException{
			return GestorUsuarios.getGestorUsuarios().comprobarDatosUsuario(pEmail, pContrasena);
		}
		
		/*
		* Se obtiene el email del jugador que esta identificado en ese momento
		*/
		public String obtenerEmailUsuarioActual(){
			return Partida.getMiPartida().obtenerEmail();
		}
	
		/*
		*	Se compruba si el usuario se ha identificado correctamente
		*/
		public boolean identificarse(String pEmail, String pContrasena) throws SQLException{
			// Si existe un usuario con esos datos se  crea un jugador real y se le añade a la partida
			if (GestorUsuarios.getGestorUsuarios().comprobarDatosUsuario(pEmail, pContrasena)){
				String nombre = GestorUsuarios.getGestorUsuarios().getNombreUsuario(pEmail);
				JugadorReal jugador = new JugadorReal(pEmail, nombre, EnumColor.AZUL);
				Partida.getMiPartida().anadirJugador(jugador);
				return true;
			}
			return false;
		}
	/*
	* Se envia la contraseña nueva al correo y se cambia en la BD
	*/
		public boolean recuperarContrasena(String pEmail) throws SQLException, MessagingException{

			if (GestorUsuarios.getGestorUsuarios().existeUsuario(pEmail)){
				String password = new BigInteger(130, new SecureRandom()).toString(10);	// Se crea una contraseña aleatoriamente
				GestorUsuarios.getGestorUsuarios().enviarContrasenaAlCorreo(pEmail, password); // Se envia la contraseña al correo
				GestorUsuarios.getGestorUsuarios().cambiarContrasena(pEmail, password); // Se cambia en la BD la nueva contraseña
				return true;
			} 
			return false;
		}
		
		public void guardar(String nombrePartida) throws SQLException, nombreUsado, JSONException {
				GestorGuardadoCarga.getGestorGuardadoCarga().guardar(nombrePartida);
		}
}
