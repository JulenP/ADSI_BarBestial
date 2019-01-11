package packPrincipal;

import org.json.JSONException;

import packControlador.Controlador;
import packVista.VentanaJuego;
import packVista.VentanaMenuInicial;

public class Directamente {

	public static void main(String[] args) {
		try {
			Controlador.getMiControlador().mostrarVentanaJuego();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
