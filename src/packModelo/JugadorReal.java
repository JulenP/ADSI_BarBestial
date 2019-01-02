package packModelo;

public class JugadorReal extends Jugador {	
    private String email;

	public JugadorReal(String pEmail,String pNombre, EnumColor pColorJugador) {
    		super(pNombre, pColorJugador);
		email = pEmail;
    }  
    
    public void elegirCartaMano(int pPos) {
    		super.cartaElegidaMano = super.obtenerCartaManoEnPosicion(pPos);
    }
    
    public void elegirSaltosCanguro() {
    		this.notificar("saltos- ");
    }

    public void elegirEspecieCola() {
   		 Tablero tablero = Tablero.getMiTablero();
   	 	String especies = tablero.obtenerEspeciesDeAnimalesEnLaCola();
    		this.notificar("especiecola-" + especies);
    }
        
    public String obtenerInformacionCartasMano() {
    		return "jugadorreal-" + this.mano.obtenerInformacionCartas();
    }
    
    public void actualizarEspecieElegida(String pEspecie) {
    		this.especieElegidaCola = pEspecie;
    }

    public void actualizarSaltosCanguro(int pSaltos) {
   	 	this.saltosElegidos = pSaltos;
    }
    
    public String obtenerEmail() {
    		return this.email;
    }
}