package packControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import packControlador.Controlador.AtrasRankingListener;
import packControlador.Controlador.MejorPuntuacionDiaListener;
import packControlador.Controlador.MejoresJugadoresListener;
import packControlador.Controlador.MejoresPartidasListener;
import packControlador.Controlador.TusMejoresPartidasListener;
import packModelo.Jugador;
import packModelo.Partida;
import packModelo.RankingDB;
import packModelo.Tablero;
import packVista.VentanaAyuda;
import packVista.VentanaElegirRanking;
import packVista.VentanaInicio;
import packVista.VentanaJuego;
import packVista.VentanaMejoresJugadores;
import packVista.VentanaMejoresPartidas;
import packVista.VentanaPuntuacionDia;
import packVista.VentanaTusMejoresPartidas;
import packVista.VentanaCambiarContrasena;

public class Controlador {
	private static Controlador miControlador;
	
	/* Modelo */
	private Partida partida;
	private Tablero tablero;
	private RankingDB rankingDB;
	private JSONArray datos = new JSONArray();
	
	/* Vista */
	private VentanaInicio ventanaInicio;
	private VentanaJuego ventanaJuego;
	private VentanaAyuda ventanaAyuda;
	private VentanaElegirRanking ventanaElegirRanking;
	private VentanaTusMejoresPartidas ventanaTusMejoresPartidas;
	private VentanaPuntuacionDia ventanaMejorPuntuacionDia;
	private VentanaMejoresPartidas ventanaMejoresPartidas;
	private VentanaMejoresJugadores ventanaMejoresJugadores;
	private VentanaCambiarContrasena ventanaCambioContrasena;
	
	public Controlador() throws JSONException {
		this.partida = Partida.getMiPartida();
		this.tablero = Tablero.getMiTablero();
		this.rankingDB = RankingDB.getRankingDB();
		
		this.ventanaInicio = new VentanaInicio();
		this.ventanaJuego = new VentanaJuego();
		this.ventanaAyuda = new VentanaAyuda();
		this.ventanaCambioContrasena = VentanaCambiarContrasena.getVentanaCambiocontrasena();
	
		this.ventanaElegirRanking = new VentanaElegirRanking();
		this.ventanaTusMejoresPartidas = new VentanaTusMejoresPartidas(datos);
		this.ventanaMejorPuntuacionDia = new VentanaPuntuacionDia(datos);
		this.ventanaMejoresPartidas = new VentanaMejoresPartidas(datos);
		this.ventanaMejoresJugadores = new VentanaMejoresJugadores(datos);

		
		/* Listeners VentanaInicio */
		this.ventanaInicio.addJugarListener(new JugarListener());
		this.ventanaInicio.addAyudaListener(new AyudaListener());
		this.ventanaInicio.addRankingListener(new RankingListener());
		this.ventanaInicio.addCambiarContrasenaListener(new CambiarContrasenaListener());

		/* Listeners VentanaJuego */
		this.ventanaJuego.addJugarTurnoListener(new JugarTurnoListener());
		this.ventanaJuego.addElegirCarta1Listener(new ElegirCarta1Listener());
		this.ventanaJuego.addElegirCarta2Listener(new ElegirCarta2Listener());
		this.ventanaJuego.addElegirCarta3Listener(new ElegirCarta3Listener());
		this.ventanaJuego.addElegirCarta4Listener(new ElegirCarta4Listener());
		this.ventanaJuego.addSiguienteListener(new SiguienteListener());
		/*this.ventanaJuego.addGuardarListener(new GuardarListener());*/
		
		this.ventanaJuego.desactivarBotonJugarTurno();
		this.ventanaJuego.desactivarBotonSiguiente();
		
		/* Listeners VentanaElegirRanking */
		this.ventanaElegirRanking.addTusPartidasListener(new TusMejoresPartidasListener());
		this.ventanaElegirRanking.addMejorPuntuacionDiaListener(new MejorPuntuacionDiaListener());
		this.ventanaElegirRanking.addMejoresPartidasListener(new MejoresPartidasListener());
		this.ventanaElegirRanking.addMejoresJugadoresListener(new MejoresJugadoresListener());
		this.ventanaElegirRanking.addAtrasRankingListener(new AtrasRankingListener());
	}
	
	public static Controlador getMiControlador() throws JSONException {
        if (miControlador == null) {
        	miControlador = new Controlador();
        }
        return miControlador;
    }
	
	public void iniciarAplicacion() {
		this.mostrarVentanaInicio();
	}
	
	private void mostrarVentanaInicio() {
		this.ventanaInicio.setVisible(true);
	}
	
	private void mostrarVentanaJuego() {
		this.ventanaJuego.setVisible(true);
	}

	private void mostrarVentanaAyuda(){
	    this.ventanaAyuda.setVisible(true);
    }
	
	private void mostrarVentanaElegirRanking(){
        this.ventanaElegirRanking.setVisible(true);
    }
	
	private void mostrarVentanaTusMejoresPartidas() throws Exception{
        datos = rankingDB.obtenerRankingMPU();
        ventanaTusMejoresPartidas = new VentanaTusMejoresPartidas(datos);
        this.ventanaTusMejoresPartidas.setVisible(true);
        
    }
	
	private void mostrarVentanaPuntuacionDia() throws Exception{
        datos = rankingDB.obtenerRankingMPD();
        ventanaMejorPuntuacionDia = new VentanaPuntuacionDia(datos);
        this.ventanaMejorPuntuacionDia.setVisible(true);
    }
	
	private void mostrarVentanaMejoresPartidas() throws Exception{
        datos = rankingDB.obtenerRankingMPG();
        ventanaMejoresPartidas = new VentanaMejoresPartidas(datos);
        this.ventanaMejoresPartidas.setVisible(true);
    }
	
	private void mostrarVentanaMejoresJugadores() throws Exception{
		datos = rankingDB.obtenerRankingMJG();
		ventanaMejoresJugadores = new VentanaMejoresJugadores(datos);
		this.ventanaMejoresJugadores.setVisible(true);
             
    }
	
	private void atrasElegirRanking(){
        this.ventanaElegirRanking.setVisible(true);
    }
	
	
	private void setUpObservers() {
		ArrayList<Jugador> jugadores = this.partida.obtenerJugadores();
		for(Jugador jug : jugadores) {
			jug.anadirObservador(ventanaJuego);
			/* Notificacion artificial para el inicio de la partida
			 * los jugadores no eran observados mientras se repartian
			 * las cartas. */
			jug.notificar(jug.obtenerInformacionCartas());
		}
		tablero.addObserver(ventanaJuego);
		partida.addObserver(ventanaJuego);
	}
	
	
	class JugarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		/*	String nombre = ventanaInicio.getTextFieldNombreValue();
			
			if(nombre.length() > 0) {
				//ocultarVentanaInicio();
				mostrarVentanaJuego();
				partida.inicializarPartida(nombre);;
				setUpObservers();
			}			
			else ventanaInicio.showNombreErrorMessage();			
		}*/
			mostrarVentanaJuego();
			partida.inicializarPartida(null);;
			setUpObservers();
		}
	}
	
	class AyudaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		    mostrarVentanaAyuda();
		}
	}
	
	class RankingListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		    mostrarVentanaElegirRanking();
		}
	}
	
	class CambiarContrasenaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		   ventanaCambioContrasena.setVisible(true);
		}
	}
	
	class TusMejoresPartidasListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		    try {
				mostrarVentanaTusMejoresPartidas();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class MejorPuntuacionDiaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		    try {
				mostrarVentanaPuntuacionDia();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class MejoresPartidasListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				mostrarVentanaMejoresPartidas();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class MejoresJugadoresListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				mostrarVentanaMejoresJugadores();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class AtrasRankingListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(ventanaElegirRanking.isVisible())
			{
				ventanaElegirRanking.dispose();
				ventanaInicio.setVisible(true);
			}
			else
			{
				mostrarVentanaElegirRanking();
			}
					
		}
	}
	
	class ElegirCarta1Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			partida.obtenerJugadorTurnoActual().elegirCartaMano(0);
			ventanaJuego.desactivarBotonesElegir();
			ventanaJuego.desactivarBotonSiguiente();
			ventanaJuego.activarBotonJugarTurno();
		}
	}
	
	class ElegirCarta2Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			partida.obtenerJugadorTurnoActual().elegirCartaMano(1);
			ventanaJuego.desactivarBotonesElegir();
			ventanaJuego.desactivarBotonSiguiente();
			ventanaJuego.activarBotonJugarTurno();
		}
	}
	
	class ElegirCarta3Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			partida.obtenerJugadorTurnoActual().elegirCartaMano(2);
			ventanaJuego.desactivarBotonesElegir();
			ventanaJuego.desactivarBotonSiguiente();
			ventanaJuego.activarBotonJugarTurno();
		}
	}
	
	class ElegirCarta4Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			partida.obtenerJugadorTurnoActual().elegirCartaMano(3);
			ventanaJuego.desactivarBotonesElegir();
			ventanaJuego.desactivarBotonSiguiente();
			ventanaJuego.activarBotonJugarTurno();
		}
	}
	
	class JugarTurnoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			partida.jugarTurno();				
			
			ventanaJuego.desactivarBotonJugarTurno();
			ventanaJuego.activarBotonSiguiente();
		}
	}
	
	class SiguienteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			partida.obtenerJugadorTurnoActual().elegirCartaMano(0);
			partida.jugarTurno();
			
			ventanaJuego.activarBotonesElegir();
			ventanaJuego.desactivarBotonJugarTurno();
			ventanaJuego.desactivarBotonSiguiente();
		}
	}
	/**
	class GuardarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			 
			
		}		
	}
	**/
}
