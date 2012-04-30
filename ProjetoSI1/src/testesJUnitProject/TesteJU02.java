package testesJUnitProject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import projeto.Controler;

public class TesteJU02 {

	Controler meleva;

	@Before
	public void testeJU02() {
		meleva = new Controler();
	}

	@Test
	public void test() {
		// User Story 02 - Cadastro de Caronas

		meleva.zerarSistema();

		// entradas válidas
		try {
			meleva.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("sessaoMark",
					meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			System.out.println(e2.getMessage());
		}

		// Localizar carona, sem carona cadastrada.
		try {
			Assert.assertEquals("{}", meleva.localizarCarona("sessaoMark",
					"Campina Grande", "João Pessoa"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("{}", meleva.localizarCarona("sessaoMark",
					"São Francisco", "Palo Alto"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("{}", meleva.localizarCarona("sessaoMark",
					"Rio de Janeiro", "São Paulo"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// o método cadastrar carona retorna id
		try {
			Assert.assertEquals("sessaoMark",
					meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("carona1ID", meleva.cadastrarCarona(
					"sessaoMark", "Campina Grande", "João Pessoa",
					"23/06/2012", "16:00", "3"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("Campina Grande",
					meleva.getAtributoCarona("carona1ID", "origem"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("João Pessoa",
					meleva.getAtributoCarona("carona1ID", "destino"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("Campina Grande - João Pessoa",
					meleva.getTrajeto("carona1ID"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// ////////////////////////////////////////////////
		try {
			Assert.assertEquals("carona2ID", meleva.cadastrarCarona(
					"sessaoMark", "Rio de Janeiro", "São Paulo", "31/05/2012",
					"08:00", "2"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("31/05/2012",
					meleva.getAtributoCarona("carona2ID", "data"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("2",
					meleva.getAtributoCarona("carona2ID", "vagas"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// //////////////////////////////////////////////////////////
		try {
			Assert.assertEquals("carona3ID", meleva.cadastrarCarona(
					"sessaoMark", "João Pessoa", "Campina Grande",
					"25/11/2026", "06:59", "4"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals(
					"João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59",
					meleva.getCarona("carona3ID"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// ///////////////////////////////////////////////////////////
		try {
			Assert.assertEquals("carona4ID", meleva.cadastrarCarona(
					"sessaoMark", "João Pessoa", "Lagoa Seca", "25/11/2016",
					"05:00", "4"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals(
					"João Pessoa para Lagoa Seca, no dia 25/11/2016, as 05:00",
					meleva.getCarona("carona4ID"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// ///////////////////////////////////////////////////////////////////////
		try {
			Assert.assertEquals("carona5ID", meleva.cadastrarCarona(
					"sessaoMark", "João Pessoa", "Lagoa Seca", "25/11/2017",
					"05:00", "4"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals(
					"João Pessoa para Lagoa Seca, no dia 25/11/2017, as 05:00",
					meleva.getCarona("carona5ID"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Localizar carona.
		try {
			Assert.assertEquals("{}", meleva.localizarCarona("sessaoMark",
					"São Francisco", "Palo Alto"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("{carona2ID}", meleva.localizarCarona(
					"sessaoMark", "Rio de Janeiro", "São Paulo"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("{carona3ID}", meleva.localizarCarona(
					"sessaoMark", "João Pessoa", "Campina Grande"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Todas as caronas que irá acontecer e que tem como origem João pessoa.
		try {
			Assert.assertEquals("{carona3ID,carona4ID,carona5ID}",
					meleva.localizarCarona("sessaoMark", "João Pessoa", ""));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Todas as caronas que irá acontecer e que tem como destino São Paulo.
		try {
			Assert.assertEquals("{carona2ID}",
					meleva.localizarCarona("sessaoMark", "", "São Paulo"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Todas as caronas que irá acontecer.
		try {
			Assert.assertEquals(
					"{carona1ID,carona2ID,carona3ID,carona4ID,carona5ID}",
					meleva.localizarCarona("sessaoMark", "", ""));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Cadastro de carona com entradas inválidas, Nenhuma carona aqui deve
		// ser cadastrada.
		try {
			meleva.cadastrarCarona(null, "Campina Grande", "João Pessoa",
					"23/06/2012", "16:00", "3");
			Assert.fail();
		} catch (Exception e1) {
			Assert.assertEquals("Sessão inválida", e1.getMessage());
		}
		try {
			meleva.cadastrarCarona("", "Patos", "São Paulo", "31/05/2012",
					"08:00", "2");
			Assert.fail();
		} catch (Exception e1) {
			Assert.assertEquals("Sessão inválida", e1.getMessage());
		}
		try {
			meleva.cadastrarCarona("teste", "João Pessoa", "Campina Grande",
					"25/11/2026", "06:59", "4");
			Assert.fail();
		} catch (Exception e1) {
			Assert.assertEquals("Sessão inexistente", e1.getMessage());
		}
		
		try {
			meleva.cadastrarCarona("sessaoMark", "Campina Grande", "João Pessoa",
					"30/02/2012", null, "3");
			Assert.fail();
		} catch (Exception e1) {
			Assert.assertEquals("Data inválida", e1.getMessage());
		}

	}

}
