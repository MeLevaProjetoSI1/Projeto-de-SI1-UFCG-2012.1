package testesJUnitProject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import projeto.Controler;

public class TesteJU04 {
	private Controler meleva;

	@Before
	public void testeJU04() {
		meleva = new Controler();
	}

	@Test
	public void test() {
		// User Story 04 - Solicitação de vagas com marcação de ponto de
		// encontro

		/*
		 * US04 - Solicitação de vagas. Permitir a solicitação de uma vaga em
		 * uma carona disponível. Este processo envolve os seguintes passos: 1.
		 * Requisitar vaga. Um usuário pode solicitar uma vaga em qualquer
		 * carona disponível no sistema. 2. Usuário sugerir ponto de encontro 3.
		 * Aprovar vaga ou segerir novo lugar. O usuário que cadastrou a carona
		 * recebe a solicitação e pode aprová-la ou sugerir um outro ponto de
		 * encontro. Ao aprová-la a quantidade de vagas disponíveis na carona é
		 * atualizada. 4. Se for sugerido um novo ponto de encontro pelo usuário
		 * que cadastrou a carona o solicitante pode aprovar a carona ou
		 * recusar.
		 */

		// Limpar os dados do sistema.
		meleva.zerarSistema();

		// Criar usuário.
		try {
			meleva.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}

		// Iniciar sessão.
		try {
			Assert.assertEquals("sessaoMark",
					meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}

		// Cadastrar caronas.
		try {
			Assert.assertEquals("carona1ID", meleva.cadastrarCarona(
					"sessaoMark", "Cajazeiras", "Patos", "20/07/2012", "14:00",
					"4"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("carona2ID", meleva.cadastrarCarona(
					"sessaoMark", "São Francisco", "Palo Alto", "12/09/2012",
					"21:00", "2"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("carona3ID", meleva.cadastrarCarona(
					"sessaoMark", "Campina Grande", "João Pessoa",
					"01/06/2012", "12:00", "1"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("carona4ID", meleva.cadastrarCarona(
					"sessaoMark", "Campina Grande", "João Pessoa",
					"02/06/2012", "12:00", "3"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("carona5ID", meleva.cadastrarCarona(
					"sessaoMark", "Campina Grande", "João Pessoa",
					"04/06/2012", "16:00", "2"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("carona6ID", meleva.cadastrarCarona(
					"sessaoMark", "Leeds", "Londres", "10/02/2013", "10:00",
					"3"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}

		// Encerrar a sessao de mark.
		Assert.assertEquals(true, meleva.encerrarSessao("mark"));

		// Iniciar sessão com outro usuário.
		try {
			meleva.criarUsuario("bill", "billz@o", "William Henry Gates III",
					"Medina, Washington", "billzin@gmail.com");
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("sessaoBill",
					meleva.abrirSessao("bill", "billz@o"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}

		// Sugerir ponto de encontro para uma carona
		try {
			Assert.assertEquals("sugestaoID", meleva.sugerirPontoEncontro(
					"sessaoBill", "carona4ID", "Acude Velho; Hiper Bompreco"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		Assert.assertEquals(true, meleva.encerrarSessao("bill"));

		// Resposta a requisicao
		try {
			Assert.assertEquals("sessaoMark",
					meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			meleva.responderSugestaoPontoEncontro("sessaoMark", "carona4ID",
					"sugestaoID", "Acude Velho;Parque da Crianca");
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		Assert.assertEquals(true, meleva.encerrarSessao("mark"));

		// Requisitar vaga na carona.
		try {
			Assert.assertEquals("sessaoBill",
					meleva.abrirSessao("bill", "billz@o"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("solicitacao1ID", meleva
					.solicitarVagaPontoEncontro("sessaoBill", "carona4ID",
							"Acude Velho"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("Campina Grande",
					meleva.getAtributoSolicitacao("solicitacao1ID", "origem"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("João Pessoa",
					meleva.getAtributoSolicitacao("solicitacao1ID", "destino"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("Mark Zuckerberg", meleva
					.getAtributoSolicitacao("solicitacao1ID", "Dono da carona"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("William Henry Gates III", meleva
					.getAtributoSolicitacao("solicitacao1ID",
							"Dono da solicitacao"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			Assert.assertEquals("Acude Velho", meleva.getAtributoSolicitacao(
					"solicitacao1ID", "Ponto de Encontro"));
		} catch (Exception e1) {
			System.err.println("aki" + e1.getMessage());
		}
		
		meleva.encerrarSessao("bill");

		// Aceitar requisição
		try {
			Assert.assertEquals("sessaoMark",
					meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			meleva.aceitarSolicitacaoPontoEncontro("sessaoMark", "solicitacao1ID");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			System.out.println(e2.getMessage());
		}
		try {
			Assert.assertEquals("2",
					meleva.getAtributoCarona("carona4ID", "vagas"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			//Assert.assertEquals("Acude Velho",
			//		meleva.getAtributoCarona("carona4ID", "Ponto de Encontro"));
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}

		// Tentar aceitar novamente a requisição

		try {
			Assert.assertEquals("sessaoBill",
					meleva.abrirSessao("bill", "billz@o"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		try {
			meleva.aceitarSolicitacao("sessaoBill", "solicitacao1ID");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Solicitação inexistente", e.getMessage());
		}

		try {
			Assert.assertEquals("2",
					meleva.getAtributoCarona("carona4ID", "vagas"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// Sugerir ponto de encontro para uma carona
		try {
			//////////////////////////////AKAIAKAIAKAIAKAIAKAAIAKIAK
			//////////////////////////////PERGUNTAR
			////////////////////////////Eu posso dar outra sugestao se naum for a mesma carona
			Assert.assertEquals("sugestao1ID", meleva.sugerirPontoEncontro(
					"sessaoBill", "carona5ID", "Acude Velho; Hiper Bompreco"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// Resposta a requisicao
		try {
			Assert.assertEquals("sessaoMark",
					meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			Assert.assertEquals("respostaID", meleva
					.responderSugestaoPontoEncontro("sessaoMark", "carona4ID",
							"sugestaoID", "Acude Velho;Parque da Crianca"));
		} catch (Exception e) {
			System.err.println(e.getMessage());

		}

		// Requisitar vaga na carona.
		meleva.desistirRequisicao("sessaoBill", "carona4ID", "solocitacao1ID");

		try {
			Assert.assertEquals("sugestaoID", meleva.sugerirPontoEncontro(
					"sessaoBill", "carona4ID", "Acude Velho; Hiper Bompreco"));
			Assert.fail();
		} catch (Exception e1) {
			Assert.assertEquals("Ponto Inválido", e1.getMessage());
		}
		try {
			Assert.assertEquals("sugestaoID", meleva
					.responderSugestaoPontoEncontro("sessaoMark", "carona4ID",
							"sugestaoID", ""));
			Assert.fail();
		} catch (Exception e1) {
			Assert.assertEquals("Ponto Inválido", e1.getMessage());
		}

		meleva.encerrarSessao("mark");
		meleva.encerrarSessao("bill");

		meleva.encerrarSistema();
		meleva.quit();

	}
}
