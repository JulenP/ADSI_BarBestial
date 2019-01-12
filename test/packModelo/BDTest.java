package packModelo;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packGestores.GestorBD;

public class BDTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SQLException {
		GestorBD.getMiGestorBD().conectar();
	}
	
	@Test
	public void ranking() throws SQLException {
		GestorBD.getMiGestorBD().execSQLSelect("SELECT * FROM Ranking");
	}

}
