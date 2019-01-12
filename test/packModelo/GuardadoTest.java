package packModelo;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packGestores.GestorBD;
import packVista.VentanaMenuInicial;

public class GuardadoTest {
	JugadorReal test;
	ListaCartas listaTest;
	Carta cartaTest;
	String nombre;
	String color;
	String especie;
	
	@Before
	public void setUp() throws Exception {
		EnumColor azul = EnumColor.AZUL;
		JugadorReal test=new JugadorReal("1@policia", "usuario", azul);
		cartaTest=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal("Canguro"), azul);
		listaTest=new ListaCartas();
		listaTest.anadirCarta(cartaTest);
		nombre="test";
		color="Verde";
		especie="mono";
		GestorBD.getMiGestorBD().conectar();
	}

	@After
	public void tearDown() throws Exception {
		test=null;
		cartaTest=null;
		listaTest=null;
		nombre=null;
		color=null;
		especie=null;
	}

	@Test
	public void obtenerDatosCarta() throws JSONException {
		System.out.println("Prueba obtenerDatosCarta\n");
		JSONObject JSONTest=cartaTest.obtenerDatosCarta();
		assertEquals(JSONTest.get("especie"),"Canguro");
		assertEquals(JSONTest.get("color"),EnumColor.AZUL);
	}
	
	@Test
	public void obtenerDatosLista() throws JSONException {
		System.out.println("Prueba obtenerDatosLista\n");
		JSONArray JSONTest=listaTest.obtenerDatosLista();
		JSONObject contenido=JSONTest.getJSONObject(0);
		assertEquals(contenido.get("especie"),"Canguro");
		assertEquals(contenido.get("color"),EnumColor.AZUL);
		assertEquals(contenido.get("indice"),0);
	}
	
	@Test
	public void insertarPartida() {
		System.out.println("Prueba insertarPartida\n");
		try {
			Date date=new Date();
			DateFormat formato=new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			String fecha=formato.format(date);
			GestorBD.getMiGestorBD().execSQL("INSERT OR REPLACE INTO Partida VALUES('test','"+fecha+"','emailTest','"+0+"','nombrePersonalizacion');");
			ResultSet resultado = GestorBD.getMiGestorBD().execSQLSelect("SELECT * From Partida WHERE nombrePartida='test';");
			resultado.next();
			System.out.println(resultado.getString("fechaHora"));
			assertEquals(resultado.getString("nombrePartida"),"test");
			GestorBD.getMiGestorBD().execSQL("DELETE FROM Partida WHERE nombrePartida='test';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void insertarMazoJug() {
		System.out.println("Prueba insertarMazoJug\n");
		try {
			GestorBD.getMiGestorBD().execSQL("INSERT OR REPLACE INTO MazoJug VALUES('"+nombre+"','"+especie+"','"+color+"','"+0+"');");
			ResultSet resultado = GestorBD.getMiGestorBD().execSQLSelect("SELECT * From MazoJug WHERE nombrePartida='"+nombre+"';");
			resultado.next();
			assertEquals(resultado.getString("especie"), "mono");
			GestorBD.getMiGestorBD().execSQL("DELETE FROM MazoJug WHERE nombrePartida='"+nombre+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertarManoJug() {
		System.out.println("Prueba insertarMazoJug\n");
		try {
			GestorBD.getMiGestorBD().execSQL("INSERT OR REPLACE INTO ManoJug VALUES('"+nombre+"','"+especie+"','"+color+"');");
			ResultSet resultado = GestorBD.getMiGestorBD().execSQLSelect("SELECT * From ManoJug WHERE nombrePartida='"+nombre+"';");
			resultado.next();
			assertEquals(resultado.getString("especie"), "mono");
			GestorBD.getMiGestorBD().execSQL("DELETE FROM ManoJug WHERE nombrePartida='"+nombre+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void insertarMazoOrd() {
		System.out.println("Prueba insertarMazoOrd\n");
		try {
			GestorBD.getMiGestorBD().execSQL("INSERT OR REPLACE INTO MazoOrd VALUES('"+nombre+"','"+especie+"','"+color+"','"+0+"');");
			ResultSet resultado = GestorBD.getMiGestorBD().execSQLSelect("SELECT * From MazoOrd WHERE nombrePartida='"+nombre+"';");
			resultado.next();
			assertEquals(resultado.getString("especie"), "mono");
			GestorBD.getMiGestorBD().execSQL("DELETE FROM MazoOrd WHERE nombrePartida='"+nombre+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertarManoOrd() {
		System.out.println("Prueba insertarManoOrd\n");
		try {
			GestorBD.getMiGestorBD().execSQL("INSERT OR REPLACE INTO ManoOrd VALUES('"+nombre+"','"+especie+"','"+color+"');");
			ResultSet resultado = GestorBD.getMiGestorBD().execSQLSelect("SELECT * From ManoOrd WHERE nombrePartida='"+nombre+"';");
			resultado.next();
			assertEquals(resultado.getString("especie"), "mono");
			GestorBD.getMiGestorBD().execSQL("DELETE FROM ManoOrd WHERE nombrePartida='"+nombre+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertarBar() {
		System.out.println("Prueba insertarBar\n");
		try {
			GestorBD.getMiGestorBD().execSQL("INSERT OR REPLACE INTO Bar VALUES('"+nombre+"','"+especie+"','"+color+"');");
			ResultSet resultado = GestorBD.getMiGestorBD().execSQLSelect("SELECT * From Bar WHERE nombrePartida='"+nombre+"';");
			resultado.next();
			assertEquals(resultado.getString("especie"), "mono");
			GestorBD.getMiGestorBD().execSQL("DELETE FROM Bar WHERE nombrePartida='"+nombre+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertarEnFila() {
		System.out.println("Prueba insertarEnFila\n");
		try {
			GestorBD.getMiGestorBD().execSQL("INSERT OR REPLACE INTO Fila (nombrePartida,especie,color,posicion) VALUES('"+nombre+"','"+especie+"','"+color+"',"+0+");");
			ResultSet resultado = GestorBD.getMiGestorBD().execSQLSelect("SELECT * From Fila WHERE nombrePartida='"+nombre+"';");
			resultado.next();
			assertEquals(resultado.getString("especie"), "mono");
			GestorBD.getMiGestorBD().execSQL("DELETE FROM Fila WHERE nombrePartida='"+nombre+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertarRanking() {
		System.out.println("Prueba insertarRanking\n");
		try {
			GestorBD.getMiGestorBD().execSQL("INSERT OR REPLACE INTO Ranking (fecha,emailUsuario,puntosJug,puntosCPU) Values('dia15','test',"+1+","+1+");");
			ResultSet resultado = GestorBD.getMiGestorBD().execSQLSelect("SELECT * From Ranking WHERE emailUsuario='test';");
			resultado.next();
			assertEquals(resultado.getString("emailUsuario"), "test");
			GestorBD.getMiGestorBD().execSQL("DELETE FROM Ranking WHERE emailUsuario='test';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
