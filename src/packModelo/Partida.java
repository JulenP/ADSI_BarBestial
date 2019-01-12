package packModelo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import packGestores.GestorBD;

public class Partida extends Observable {
    private static Partida miPartida;

    /* El turno se representa como un numero
     * que indica la posicion de la lista de jugadores
     * que jugara cada turno. Incrementara y volverá
     * a 0 cuando todos hayan jugado. */
    private int turnoActual;
    private ArrayList<Jugador> listaJugadores;

    private Partida() {
        this.listaJugadores = new ArrayList<Jugador>();
    }

    public static Partida getMiPartida() {
        if (miPartida == null) {
            miPartida = new Partida();
        }
        return miPartida;
    }

    public void inicializarPartida(String pNombreJugador) {
        Tablero tablero = Tablero.getMiTablero();
        tablero.vaciar();

        Bar bar = Bar.getMiBar();
        bar.vaciar();

        EsLoQueHay elqh = EsLoQueHay.getMiEsLoQueHay();
        elqh.vaciar();

       //this.listaJugadores.add(new JugadorReal(pNombreJugador, EnumColor.AZUL));
        this.listaJugadores.add(new Maquina("Maquina", EnumColor.VERDE));

        this.repartirCartas();
        this.turnoActual = 0;
    }
    
    public void jugarTurno() throws SQLException {
        Tablero tablero = Tablero.getMiTablero();
        Jugador jugador;
        jugador = this.obtenerJugadorTurnoActual();
        jugador.jugarTurno();
        tablero.hacerUltimaAnimalada();
        tablero.hacerAnimaladasRecurrentes();
        tablero.revisarCola();

        if (this.comprobarFinalizacion()) {
            this.finalizar();
        } else {
            this.avanzarTurno();
        }

    }

    public ArrayList<Jugador> obtenerJugadores() {
        return this.listaJugadores;
    }

    public Jugador obtenerJugadorTurnoActual() {
        return this.listaJugadores.get(turnoActual);
    }

    private void repartirCartas() {
        Iterator<Jugador> iterator = this.listaJugadores.iterator();
        Jugador jugador;

        while (iterator.hasNext()) {
            jugador = iterator.next();
            jugador.repartirCartas();
        }
    }

    private void avanzarTurno() {
        this.turnoActual++;

        if (this.turnoActual == this.listaJugadores.size()) {
            turnoActual = 0;
        }
    }

    private void finalizar() throws SQLException {
        String infoGanador = this.obtenerInformacionGanador();
       
        /* Guardar ganador en la base de datos */
        this.anadirRankingDatabase();

        /* Notificar a la interfaz */
        this.notificar("fin-" + infoGanador);
    }

    private boolean comprobarFinalizacion() {
        Iterator<Jugador> it = this.listaJugadores.iterator();
        Jugador j;
        boolean quedanCartas = false;

        while (it.hasNext() && !quedanCartas) {
            j = it.next();
            if (j.tieneCartas()) {
                quedanCartas = true;
            }
        }
        return !quedanCartas;
    }

    private String obtenerInformacionGanador() {
        Bar bar = Bar.getMiBar();
        EnumColor color = bar.calcularGanador();
        String infoGanador = null;
        if (color == null) {
            infoGanador = "Empate";
        } else {
            boolean ganadorEncontrado = false;
            Iterator<Jugador> it = this.listaJugadores.iterator();
            Jugador jugador = null;

            while (it.hasNext() && !ganadorEncontrado) {
                jugador = it.next();
                if (jugador.getColorJugador().equals(color)) {
                    ganadorEncontrado = true;
                    infoGanador = jugador.getNombre();
                }
            }
            String numeroDeCartasGanador = Integer.toString(this.obtenerNumeroDeCartasColorEnBar(color));
            String fuerzaGanador = Integer.toString(this.obtenerFuerzaColorEnBar(color));
            infoGanador = infoGanador + " " + numeroDeCartasGanador + " " + fuerzaGanador;
        }
        return infoGanador;
    }

    private int obtenerNumeroDeCartasColorEnBar(EnumColor pColor) {
        return Bar.getMiBar().obtenerNumeroDeCartasColor(pColor);
    }

    private int obtenerFuerzaColorEnBar(EnumColor pColor) {
        return Bar.getMiBar().obtenerFuerzaColor(pColor);
    }

    private void anadirRankingDatabase() throws SQLException {
               
        String emailUsuario = BarBestial.getBarBestial().obtenerEmailUsuarioActual();
        
        EnumColor jugador = EnumColor.AZUL;
        EnumColor pc = EnumColor.VERDE;
        
        int puntosJug = obtenerNumeroDeCartasColorEnBar(jugador);
        int puntosCPU = obtenerNumeroDeCartasColorEnBar(pc);
        
        Date pFecha = new Date(); //Fecha Actual
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(pFecha);		
        String fecha = modifiedDate.toString();
       
        //Insertamos a la tabla Ranking los datos de la Partida 
        GestorBD.getMiGestorBD().execSQL("INSERT INTO Ranking(fecha,emailUsuario,puntosJug,puntosCPU) VALUES ('"+modifiedDate+"','"+emailUsuario+"',"+puntosJug+","+puntosCPU+");");
    }
    
    private void notificar(String pInformacion) {
        super.setChanged();
        super.notifyObservers(pInformacion);
    }
    
    public String obtenerEmail() {
		if (listaJugadores.isEmpty()){
     		return null;
     	}
    		return ((JugadorReal) obtenerJugadorTurnoActual()).obtenerEmail();
    }
    
    public JSONObject obtenerDatosPartida() {
    	JSONObject jugador0=listaJugadores.get(0).obtenerDatosJugador();
    	JSONObject jugador1=listaJugadores.get(1).obtenerDatosJugador();
    	JSONObject datosPartida=new JSONObject();
    	try {
			datosPartida.put("jugador", jugador0);
	    	datosPartida.put("cpu", jugador1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return datosPartida;
    }
	
	public void anadirJugador(JugadorReal pJugador){	
   	 	listaJugadores.add(pJugador);
    }
}
