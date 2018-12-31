package packGestores;

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
}
