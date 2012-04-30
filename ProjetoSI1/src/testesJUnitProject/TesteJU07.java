package testesJUnitProject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import projeto.Controler;

public class TesteJU07 {
	private Controler meleva;
	private int contador;

	@Before
	public void testeJU() {
		meleva = new Controler();
	}

	@Test
	public void testCriaUsuario() throws Exception {
		//Limpar os dados do sistema.
		meleva.zerarSistema();
		
		//Criar usuário.
		try {
			meleva.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		try {
			meleva.criarUsuario("steve", "5t3v3", "Steve Paul Jobs", "Palo Alto, California", "jobs@apple.com");
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		
		//Iniciar sessão.
		try {
			Assert.assertEquals("sessaoSteve", meleva.abrirSessao("steve", "5t3v3"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		
		try {
			Assert.assertEquals("Steve Paul Jobs", meleva.getAtributoUsuario("steve", "nome"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		try {
			Assert.assertEquals("Palo Alto, California", meleva.getAtributoUsuario("steve", "endereco"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}

		try {
			Assert.assertEquals("sessaoMark", meleva.abrirSessao("mark", "m@rk"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		try {
			Assert.assertEquals("Mark Zuckerberg", meleva.getAtributoUsuario("mark", "nome"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		try {
			Assert.assertEquals("Palo Alto, California", meleva.getAtributoUsuario("mark", "endereco"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		
		// cadastra
		try {
			Assert.assertEquals("carona1ID", meleva.cadastrarCarona("sessaoMark",
					"Cajazeiras", "Patos", "20/07/2012", "14:00", "4"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		try {
			Assert.assertEquals("carona2ID", meleva.cadastrarCarona("sessaoMark",
					"São Francisco", "Palo Alto", "12/09/2012", "21:00", "2"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		try {
			Assert.assertEquals("carona3ID", meleva.cadastrarCarona("sessaoMark",
					"Campina Grande", "João Pessoa", "01/06/2012", "12:00", "1"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		try {
			Assert.assertEquals("carona4ID", meleva.cadastrarCarona("sessaoSteve",
					"Campina Grande", "João Pessoa", "02/06/2012", "12:00", "3"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		try {
			Assert.assertEquals("carona5ID", meleva.cadastrarCarona("sessaoSteve",
					"Campina Grande", "João Pessoa", "04/06/2012", "16:00", "2"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		try {
			Assert.assertEquals("carona6ID", meleva.cadastrarCarona("sessaoSteve",
					"Leeds", "Londres", "10/02/2013", "10:00", "3"));
			this.contador++;
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador + " " + e1.getMessage());
		}
		//Encerrar a sessao de mark.
		Assert.assertEquals(true, meleva.encerrarSessao("mark"));
		Assert.assertEquals(true, meleva.encerrarSessao("steve"));

		//Sugerir ponto de encontro para uma carona
		try {
			Assert.assertEquals("sessaoSteve", meleva.abrirSessao("steve", "5t3v3"));
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador++ + " " + e1.getMessage());
		}
		try {
			Assert.assertEquals("sugestaoID", meleva.sugerirPontoEncontro("sessaoSteve", "carona2ID", "[Acude Velho;Hiper Bompreco]"));
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador++ + " " + e1.getMessage());
		}
		
		//Requisitar vaga na carona.
		try {
			Assert.assertEquals("solicitacao1ID" , meleva.solicitarVaga("sessaoSteve", "carona2ID"));
		} catch (Exception e1) {
			System.out.println("AKI" + this.contador++ + " " + e1.getMessage());
		}
		try {
			Assert.assertEquals("São Francisco", meleva.getAtributoSolicitacao("solicitacao1ID", "origem"));
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("Palo Alto", meleva.getAtributoSolicitacao("solicitacao1ID", "destino"));
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("Mark Zuckerberg", meleva.getAtributoSolicitacao("solicitacao1ID", "Dono da carona"));
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("Steve Paul Jobs", meleva.getAtributoSolicitacao("solicitacao1ID", "Dono da solicitacao"));
		} catch (Exception e1) {
		
			e1.printStackTrace();
		}
		
		//Aceitar requisição
		try {
			Assert.assertEquals("sessaoMark", meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		try {
			meleva.aceitarSolicitacao("sessaoMark", "solicitacao1ID");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		try {
			Assert.assertEquals("3", meleva.getAtributoCarona("carona4ID", "vagas"));
		} catch (Exception e1) {
		
			e1.printStackTrace();
		}
		
		//Encerrar a sessao.
		Assert.assertEquals(true, meleva.encerrarSessao("mark"));
		Assert.assertEquals(true, meleva.encerrarSessao("steve"));
		
		//Finaliza o sistema
		meleva.encerrarSistema();
		
		//Abre o sistema novamente
		meleva.reiniciarSistema();


		//Comeca a recuperar as informacoes que foram persistidas anteriormente
		
		try {
			Assert.assertEquals("sessaoMark", meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e1) {
			System.out.println("AKI");
		}

		try {
			Assert.assertEquals("sessaoSteve", meleva.abrirSessao("steve", "5t3v3"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		//Recupera Informacoes Usuario
		try {
			Assert.assertEquals("Steve Paul Jobs", meleva.getAtributoUsuario("steve", "nome"));
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		try {
			Assert.assertEquals("Palo Alto, California", meleva.getAtributoUsuario("steve", "endereco"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Assert.assertEquals("Mark Zuckerberg", meleva.getAtributoUsuario("mark", "nome"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Assert.assertEquals("Palo Alto, California", meleva.getAtributoUsuario("mark", "endereco"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Recupera Informacoes das Caronas
		Assert.assertEquals("carona1ID", meleva.getCaronaUsuario("sessaoMark", 1));
		Assert.assertEquals("Cajazeiras", meleva.getAtributoCarona("carona1ID", "origem"));
		Assert.assertEquals("Patos", meleva.getAtributoCarona("carona1ID","destino"));
///////////
		Assert.assertEquals("carona2ID", meleva.getCaronaUsuario("sessaoMark", 2));
		Assert.assertEquals("São Francisco", meleva.getAtributoCarona("carona2ID", "origem"));
		Assert.assertEquals("Palo Alto", meleva.getAtributoCarona("carona2ID","destino"));
///////////
		Assert.assertEquals("carona3ID", meleva.getCaronaUsuario("sessaoMark", 3));
		Assert.assertEquals("Campina Grande", meleva.getAtributoCarona("carona3ID", "origem"));
		Assert.assertEquals("João Pessoa", meleva.getAtributoCarona("carona3ID","destino"));
///////////
		Assert.assertEquals("carona4ID", meleva.getCaronaUsuario("sessaoSteve", 1));
		Assert.assertEquals("Campina Grande", meleva.getAtributoCarona("carona4ID", "origem"));
		Assert.assertEquals("João Pessoa", meleva.getAtributoCarona("carona4ID","destino"));
///////////
		Assert.assertEquals("carona5ID", meleva.getCaronaUsuario("sessaoSteve", 2));
		Assert.assertEquals("Campina Grande", meleva.getAtributoCarona("carona5ID", "origem"));
		Assert.assertEquals("João Pessoa", meleva.getAtributoCarona("carona5ID","destino"));
///////////
		Assert.assertEquals("carona6ID", meleva.getCaronaUsuario("sessaoSteve", 3));
		Assert.assertEquals("Leeds", meleva.getAtributoCarona("carona6ID", "origem"));
		Assert.assertEquals("Londres", meleva.getAtributoCarona("carona6ID","destino"));

		//Recupera todas as caronas cadastradas
		Assert.assertEquals("{carona1ID,carona2ID,carona3ID}", meleva.getTodasCaronasUsuario("sessaoMark"));
		Assert.assertEquals("{carona4ID,carona5ID,carona6ID}", meleva.getTodasCaronasUsuario("sessaoSteve"));

		
		//Recupera solicitacoes confirmadas
		Assert.assertEquals("{solicitacao1ID}", meleva.getSolicitacoesConfirmadas("sessaoMark", "carona2ID"));
		Assert.assertEquals("", meleva.getSolicitacoesPendentes("sessaoMark", "carona2ID"));
		Assert.assertEquals("[Acude Velho;Hiper Bompreco]", meleva.getPontosSugeridos("sessaoMark", "carona2ID"));
		
		//Recupera pontos sugeridos
		Assert.assertEquals("[Acude Velho;Hiper Bompreco]", meleva.getPontosEncontro("sessaoMark","carona2ID"));
		
		//Finaliza o sistema
		meleva.encerrarSistema();
		meleva.quit();

		
	}
}
