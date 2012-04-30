package testesJUnitProject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import projeto.Controler;

public class TesteJU05 {
	Controler meleva;

	@Before
	public void testeJU() {
		meleva = new Controler();
	}

	@Test
	public void testCriaUsuario() {
		//Limpar os dados do sistema.
		meleva.zerarSistema();
		//Criar usuário.
		
		try {
			meleva.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("sessaoMark", meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// cadastra
		try {
			Assert.assertEquals("carona1ID", meleva.cadastrarCarona("sessaoMark",
					"Cajazeiras", "Patos", "20/07/2012", "14:00", "4"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("carona2ID", meleva.cadastrarCarona("sessaoMark",
					"São Francisco", "Palo Alto", "12/09/2012", "21:00", "2"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("carona3ID", meleva.cadastrarCarona("sessaoMark",
					"Campina Grande", "João Pessoa", "01/06/2012", "12:00", "1"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("carona4ID", meleva.cadastrarCarona("sessaoMark",
					"Campina Grande", "João Pessoa", "02/06/2012", "12:00", "3"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("carona5ID", meleva.cadastrarCarona("sessaoMark",
					"Campina Grande", "João Pessoa", "04/06/2012", "16:00", "2"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("carona6ID", meleva.cadastrarCarona("sessaoMark",
					"Leeds", "Londres", "10/02/2013", "10:00", "3"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Encerrar a sessao de mark.
		Assert.assertEquals(true, meleva.encerrarSessao("mark"));

		// Iniciar sessão com outro usuário.
		try {
			meleva.criarUsuario("bill", "billz@o", "William Henry Gates III",
					"Medina, Washington", "billzin@gmail.com");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("sessaoBill", meleva.abrirSessao("bill", "billz@o"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		//Requisitar vaga na carona.
		try {
			Assert.assertEquals("solicitacao1ID" , meleva.solicitarVaga("sessaoBill", "carona4ID"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("Campina Grande", meleva.getAtributoSolicitacao("solicitacao1ID", "origem"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("João Pessoa", meleva.getAtributoSolicitacao("solicitacao1ID", "destino"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("Mark Zuckerberg", meleva.getAtributoSolicitacao("solicitacao1ID", "Dono da carona"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("William Henry Gates III", meleva.getAtributoSolicitacao("solicitacao1ID", "Dono da solicitacao"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Iniciar sessão.
		try {
			Assert.assertEquals("sessaoMark", meleva.abrirSessao("mark", "m@rk"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Aceitar requisição
		try {
			meleva.aceitarSolicitacao("sessaoMark", "solicitacao1ID");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("2", meleva.getAtributoCarona("carona4ID", "vagas"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Tentar aceitar novamente a requisição
		try {
			meleva.aceitarSolicitacao("sessaoMark", "solicitacao1ID");
			
		} catch (Exception e) {
			Assert.assertEquals("Solicitação inexistente", e.getMessage());
		}
		try {
			Assert.assertEquals("2", meleva.getAtributoCarona("carona4ID", "vagas"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		//Requisitar vaga na carona.
		//por que na carona 5 ja começa com solicitacao 2 se é a primeira vez que ele recebe
		try {
			Assert.assertEquals("solicitacao2ID", meleva.solicitarVaga("sessaoBill", "carona5ID"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("Campina Grande", meleva.getAtributoSolicitacao("solicitacao2ID", "origem"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("João Pessoa", meleva.getAtributoSolicitacao("solicitacao2ID", "destino"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("Mark Zuckerberg", meleva.getAtributoSolicitacao("solicitacao2ID", "Dono da carona"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				try {
					Assert.assertEquals("William Henry Gates III", meleva.getAtributoSolicitacao("solicitacao2ID", "Dono da solicitacao"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		//Rejeitar requisição
		try {
			meleva.rejeitarSolicitacao("sessaoMark", "solicitacao2ID");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Assert.assertEquals("2", meleva.getAtributoCarona("carona5ID", "vagas"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Tentar rejeitar novamente a requisição
		//o problema é que essa solicatacao pedida ao pode ser tanto do carona 5ID como do 
		try {
			meleva.rejeitarSolicitacao("sessaoMark", "solicitacao2ID");
		} catch (Exception e) {
			Assert.assertEquals("Solicitação inexistente", e.getMessage());
		}
		
		try {
			Assert.assertEquals("2",meleva. getAtributoCarona("carona5ID", "vagas"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
