package testesJUnitProject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import projectExeptions.LoginInvalidoException;
import projeto.Controler;

public class TesteJU08 {

	private Controler meleva;

	@Before
	public void testeJU() {
		meleva = new Controler();
	}

	@Test
	public void test() {
		// Limpar os dados do sistema.
		meleva.zerarSistema();

		// Criar usuário.
		try {
			meleva.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			meleva.criarUsuario("bill", "bilz@o", "William Henry Gates III",
					"Medina, Washington", "billzin@gmail.com");

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		try {
			meleva.criarUsuario("vader", "d4rth", "Anakin Skywalker",
					"Death Star I", "darthvader@empire.com");

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		// Iniciar sessão.
		try {
			Assert.assertEquals("sessaoMark",
					meleva.abrirSessao("mark", "m@rk"));

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		// Cadastrar caronas.

		try {
			Assert.assertEquals("carona1ID", meleva.cadastrarCarona(
					"sessaoMark", "Campina Grande", "João Pessoa",
					"02/06/2012", "12:00", "3"));

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("carona2ID", meleva.cadastrarCarona(
					"sessaoMark", "Campina Grande", "João Pessoa",
					"04/06/2012", "16:00", "2"));

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		// Iniciar sessão com outro usuário.
		try {
			Assert.assertEquals("sessaoBill",
					meleva.abrirSessao("bill", "bilz@o"));

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		// Requisitar vaga na carona.
		try {
			Assert.assertEquals("solicitacao1ID",
					meleva.solicitarVaga("sessaoBill", "carona4ID"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("Campina Grande",
					meleva.getAtributoSolicitacao("solicitacao1ID", "origem"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("João Pessoa",
					meleva.getAtributoSolicitacao("solicitacao1ID", "destino"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("Mark Zuckerberg", meleva
					.getAtributoSolicitacao("solicitacao1ID", "Dono da carona"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("William Henry Gates III", meleva
					.getAtributoSolicitacao("solicitacao1ID",
							"Dono da solicitacao"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Aceitar requisição
		try {
			meleva.aceitarSolicitacao("sessaoMark", "solicitacao1ID");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("2",
					meleva.getAtributoCarona("carona4ID", "vagas"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Requisitar vaga na carona.
		try {
			Assert.assertEquals("solicitacao2ID",
					meleva.solicitarVaga("sessaoBill", "carona5ID"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("Campina Grande",
					meleva.getAtributoSolicitacao("solicitacao2ID", "origem"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("João Pessoa",
					meleva.getAtributoSolicitacao("solicitacao2ID", "destino"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("Mark Zuckerberg", meleva
					.getAtributoSolicitacao("solicitacao2ID", "Dono da carona"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("William Henry Gates III", meleva
					.getAtributoSolicitacao("solicitacao2ID",
							"Dono da solicitacao"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Aceitar requisição
		try {
			meleva.aceitarSolicitacao("sessaoMark", "solicitacao2ID");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Assert.assertEquals("1",
					meleva.getAtributoCarona("carona5ID", "vagas"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Review de carona.
		try {
			meleva.reviewVagaEmCarona("sessaoMark", "carona4ID", "bill",
					"faltou");
		} catch (Exception e3) {
			System.out.println(e3.getMessage());
		}
		try {
			Assert.assertEquals("perfilBill",
					meleva.visualizarPerfil("sessaoBill", "bill"));
		} catch (LoginInvalidoException e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("William Henry Gates III",
					meleva.getAtributoPerfil("bill", "nome"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("Medina, Washington",
					meleva.getAtributoPerfil("bill", "endereco"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("billzin@gmail.com",
					meleva.getAtributoPerfil("bill", "email"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("[carona4ID,carona5ID]", meleva
					.getAtributoPerfil("bill", "historico de vagas em caronas"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("[]",
					meleva.getAtributoPerfil("bill", "historico de caronas"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("0", meleva.getAtributoPerfil("bill",
					"caronas seguras e tranquilas"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("0", meleva.getAtributoPerfil("bill",
					"caronas que não funcionaram"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("1", meleva.getAtributoPerfil("bill",
					"faltas em vagas de caronas"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("0", meleva.getAtributoPerfil("bill",
					"presenças em vagas de caronas"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		// ///////////////////////////
		try {
			meleva.reviewVagaEmCarona("sessaoMark", "carona5ID", "bill",
					"não faltou");
		} catch (Exception e3) {
			System.out.println(e3.getMessage());
		}
		try {
			Assert.assertEquals("perfilBill",
					meleva.visualizarPerfil("sessaoBill", "bill"));
		} catch (LoginInvalidoException e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("William Henry Gates III",
					meleva.getAtributoPerfil("bill", "nome"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("Medina, Washington",
					meleva.getAtributoPerfil("bill", "endereco"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("billzin@gmail.com",
					meleva.getAtributoPerfil("bill", "email"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("[carona4ID,carona5ID]", meleva
					.getAtributoPerfil("bill", "historico de vagas em caronas"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("[]",
					meleva.getAtributoPerfil("bill", "historico de caronas"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("0", meleva.getAtributoPerfil("bill",
					"caronas seguras e tranquilas"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("0", meleva.getAtributoPerfil("bill",
					"caronas que não funcionaram"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("1", meleva.getAtributoPerfil("bill",
					"faltas em vagas de caronas"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("0", meleva.getAtributoPerfil("bill",
					"presenças em vagas de caronas"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		// possiveis erros
		try {

			meleva.reviewVagaEmCarona("sessaoMark", "carona5ID", "bill",
					"não dou mais carona");
		} catch (Exception e2) {
			Assert.assertEquals("Opção inválida.", e2.getMessage());
		}

		// Iniciar sessão com outro usuário.
		try {
			Assert.assertEquals("sessaoVader",
					meleva.abrirSessao("vader", "d4rth"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		try {
			meleva.reviewVagaEmCarona("sessaoMark", "carona5ID", "vader",
					"não funcionou");

		} catch (Exception e) {
			Assert.assertEquals("Usuário não possui vaga na carona.",
					e.getMessage());
		}

		meleva.encerrarSistema();
		meleva.quit();
	}
}
