package packGestores;

public class GestorGuardadoCarga {
	private static GestorGuardadoCarga miGestorGuardadoCarga;
	public static GestorGuardadoCarga getGestorGuardadoCarga() {
		if(miGestorGuardadoCarga==null) {
			miGestorGuardadoCarga=new GestorGuardadoCarga();
		}
		return miGestorGuardadoCarga;
	}
	
	public void guardarPartida(String nombrePartida) {
		
	}


}
