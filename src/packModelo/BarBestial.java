package packModelo;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;

import javax.mail.MessagingException;

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
	
		public void registrarUsuario(String pEmail, String pNombre, String pContrasena) throws SQLException{
			GestorUsuarios.getGestorUsuarios().registrarUsuario(pEmail, pNombre, pContrasena);
			JugadorReal jugador = new JugadorReal(pEmail, pNombre, EnumColor.AZUL);
			Partida.getMiPartida().anadirJugador(jugador);
		}
		
		public boolean existeUsuarioNombre(String pNombre) throws SQLException{
			return GestorUsuarios.getGestorUsuarios().existeUsuarioNombre(pNombre);
	 	}
		
		public void cambiarContrasena(String pEmail, String pContrasena) throws SQLException{
			GestorUsuarios.getGestorUsuarios().cambiarContrasena(pEmail, pContrasena);
		}
	
		public boolean comprobarDatosUsuario(String pEmail, String pContrasena) throws SQLException{
			return GestorUsuarios.getGestorUsuarios().comprobarDatosUsuario(pEmail, pContrasena);
		}
		
		public String obtenerEmailUsuarioActual(){
			return Partida.getMiPartida().obtenerEmail();
		}
	
		public boolean identificarse(String pEmail, String pContrasena) throws SQLException{
		
			if (GestorUsuarios.getGestorUsuarios().comprobarDatosUsuario(pEmail, pContrasena)){
				String nombre = GestorUsuarios.getGestorUsuarios().getNombreUsuario(pEmail);
				JugadorReal jugador = new JugadorReal(pEmail, nombre, EnumColor.AZUL);
				Partida.getMiPartida().anadirJugador(jugador);
				return true;
			}
			return false;
		}
	
		public boolean recuperarContrasena(String pEmail) throws SQLException, MessagingException{
		
			if (GestorUsuarios.getGestorUsuarios().existeUsuario(pEmail)){
				String password = new BigInteger(130, new SecureRandom()).toString(10);
				GestorUsuarios.getGestorUsuarios().enviarContrasenaAlCorreo(pEmail, password);
				GestorUsuarios.getGestorUsuarios().cambiarContrasena(pEmail, password);
				return true;
			} 
			return false;
		}
		
		public void guardar(String nombrePartida) throws SQLException, nombreUsado {
				GestorGuardadoCarga.getGestorGuardadoCarga().guardar(nombrePartida);
		}
}
