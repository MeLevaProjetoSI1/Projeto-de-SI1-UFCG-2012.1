package testesJUnitProject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import projeto.Controler;

public class TesteJU01 {
	Controler meleva;

	@Before
	public void testeJU01() {
		meleva = new Controler();
	}

	@Test
	public void test() {

		// User Story 01 - Criação de conta

		meleva.zerarSistema();

		// entradas válidas
		try {
			meleva.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");
			meleva.criarUsuario("steve", "5t3v3", "Steven Paul Jobs",
					"Palo Alto, California", "jobs@apple.com");

			meleva.criarUsuario("bill", "severino", "William Henry Gates III",
					"Medina, Washington", "billzin@msn.com");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// o método 'abrirSessao' retorna o ID da sessão
		try {
			Assert.assertEquals("sessaoMark",
					meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("Mark Zuckerberg",
					meleva.getAtributoUsuario("mark", "nome"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("Palo Alto, California",
					meleva.getAtributoUsuario("mark", "endereco"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			Assert.assertEquals("sessaoSteve",
					meleva.abrirSessao("steve", "5t3v3"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("Steven Paul Jobs",
					meleva.getAtributoUsuario("steve", "nome"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("Palo Alto, California",
					meleva.getAtributoUsuario("steve", "endereco"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// entradas inválidas

		try {
			meleva.criarUsuario("", "xptz", "xpto", "xpto", "deuerro@gmail.com");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		try {
			meleva.criarUsuario(null, "xptz", "xpto", "xpto",
					"logininvalido@gmail.com");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}

		try {
			meleva.criarUsuario("xpto", "xptz", null, "xpto",
					"nomeinvalido@hotmail.com");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		try {
			meleva.criarUsuario("xpto", "xptz", "", "xpto",
					"nomevazio@email.com");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Nome inválido", e.getMessage());
		}

		try {
			meleva.criarUsuario("xpto", "xptz", "patriciano", "xpto", null);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Email inválido", e.getMessage());
		}
		try {
			meleva.criarUsuario("xpto", "xptz", "pantcho", "xpto", "");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Email inválido", e.getMessage());
		}

		try {
			meleva.criarUsuario("mark", "tttppp", "pantcho", "xpto",
					"markinho@facebook.com");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Já existe um usuário com este login",
					e.getMessage());
		}
		try {
			meleva.criarUsuario("xpto", "tttppp", "markito", "xpto",
					"mark@facebook.com");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Já existe um usuário com este email",
					e.getMessage());
		}

		try {
			meleva.abrirSessao(null, "teste");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		try {
			meleva.abrirSessao("", "segundoteste");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		try {
			meleva.abrirSessao("mark", "teste");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		try {
			meleva.abrirSessao("mark", "segundoteste");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}

		try {
			meleva.abrirSessao("xpto", "maisXptoAinda");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Usuário inexistente", e.getMessage());
		}

		try {
			meleva.abrirSessao(null, "nome");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		try {
			meleva.abrirSessao("", "nome");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}

		try {
			meleva.getAtributoUsuario("xpto", "nome");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Usuário inexistente", e.getMessage());
		}

		try {
			meleva.getAtributoUsuario("mark", null);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Atributo inválido", e.getMessage());
		}
		try {
			meleva.getAtributoUsuario("mark", "");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Atributo inválido", e.getMessage());
		}
		try {
			meleva.getAtributoUsuario("mark", "xpto");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Atributo inexistente", e.getMessage());
		}
				
				

		meleva.encerrarSistema();
		meleva.quit();

	}

}
