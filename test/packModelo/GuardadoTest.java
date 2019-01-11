package packModelo;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packVista.VentanaMenuInicial;

public class GuardadoTest {
	JugadorReal test;
	ListaCartas listaTest;
	Carta cartaTest;
	
	@Before
	public void setUp() throws Exception {
		EnumColor azul = EnumColor.AZUL;
		JugadorReal test=new JugadorReal("1@policia", "usuario", azul);
		cartaTest=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal("Canguro"), azul);
		listaTest=new ListaCartas();
		listaTest.anadirCarta(cartaTest);
	}

	@After
	public void tearDown() throws Exception {
		test=null;
		cartaTest=null;
		listaTest=null;
	}

	@Test
	public void obtenerDatosCarta() throws JSONException {
		JSONObject JSONTest=cartaTest.obtenerDatosCarta();
		assertEquals(JSONTest.get("especie"),"Canguro");
		assertEquals(JSONTest.get("color"),EnumColor.AZUL);
	}
	
	@Test
	public void obtenerDatosLista() throws JSONException {
		JSONArray JSONTest=listaTest.obtenerDatosLista();
		JSONObject contenido=JSONTest.getJSONObject(0);
		assertEquals(contenido.get("especie"),"Canguro");
		assertEquals(contenido.get("color"),EnumColor.AZUL);
		assertEquals(contenido.get("indice"),0);
	}
	
}
