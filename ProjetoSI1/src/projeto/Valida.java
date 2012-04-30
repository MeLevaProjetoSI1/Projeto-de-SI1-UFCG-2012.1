package projeto;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import projectExeptions.AtributoInvalido;
import projectExeptions.CaronaInexistenteException;
import projectExeptions.CaronaInvalidaException;
import projectExeptions.DataInvalidaException;
import projectExeptions.DestinoInvalidoException;
import projectExeptions.EmailExistente;
import projectExeptions.EmailInvalidoException;
import projectExeptions.EnderecoInvalidoException;
import projectExeptions.HoraInvalidaException;
import projectExeptions.IdentificadorDeCaronaInvalidoException;
import projectExeptions.ItemInexistenteException;
import projectExeptions.LoginExistente;
import projectExeptions.LoginInvalidoException;
import projectExeptions.NomeInvalidoException;
import projectExeptions.OpcaoInvalidaException;
import projectExeptions.OrigemInvalidaException;
import projectExeptions.SenhaInvalidoException;
import projectExeptions.SessaoInexistenteException;
import projectExeptions.SessaoInvalidaException;
import projectExeptions.SolicitacaoInexistenteException;
import projectExeptions.TrajetoInexistenteException;
import projectExeptions.TrajetoInvalidoException;
import projectExeptions.UsuarioInexistente;
import projectExeptions.VagaInvalidaException;

/**
 * UFCG - CEEI - DSC Disciplina: Sistema de Informacao I. Professor: Nazareno.
 * 
 * Projeto SI1 2012.1.
 * 
 * Classe para verificacao de dados do sistema.
 * 
 * Classe para execucao dos testes do EasyAcceppt.
 * 
 * @author Grupo do Projeto MeLeva.
 * @version 1.0
 * 
 */
public class Valida {
	private static final String FALTOU = "faltou";
	private static final String NAO_FALTOU = "não faltou";
	private Calendar calendario;

	/**
	 * Construtor.
	 */
	public Valida() {
		calendario = new GregorianCalendar();
	}

	/**
	 * Método para validar argumentos origem ou destino são validos.
	 * 
	 * @param origem
	 * @return
	 * @throws Exception
	 */
	public void verificaOrigemInvalida(String origem) throws Exception {
		if (origem.contains("-") || origem.contains("()")
				|| origem.contains("!") || origem.contains("!?")
				|| origem.contains(".")) {
			throw new OrigemInvalidaException();
		}

	}

	public void verificaDestinoInvalido(String destino) throws Exception {
		if (destino.contains("-") || destino.contains("()")
				|| destino.contains("!") || destino.contains("!?")
				|| destino.contains(".")) {
			throw new DestinoInvalidoException();
		}

	}

	/**
	 * Método para validar argumentos para abrir sessao.
	 * 
	 * @param login
	 * @param senha
	 * @throws Exception
	 */
	public void validaAbrirSessao(String login, String senha) throws Exception {
		if (login == null || senha == null) {
			throw new LoginInvalidoException();
		} else if (login.equals("")) {
			throw new LoginInvalidoException();
		}
	}

	/**
	 * Método para validar argumentos para pegar o atributo do usuario.
	 * 
	 * @param login
	 * @param atributo
	 * @param Usuario
	 * @throws Exception
	 */
	public void validaGetAtributoUsuario(String login, String atributo,
			Usuario Usuario) throws Exception {
		if (login == null || login.equals("")) {
			throw new LoginInvalidoException();
		} else if (atributo == null || atributo.equals("")) {
			throw new AtributoInvalido();
		} else if (Usuario == null) {
			throw new UsuarioInexistente();
		}

	}

	/**
	 * Método para validar argumentos para pegar o atributo da carona.
	 * 
	 * @param id
	 * @param atributo
	 * @param Carona
	 * @throws Exception
	 */
	public void validaGetAtributoCarona(String id, String atributo,
			Carona Carona) throws Exception {
		if (id == null || id.equals("")) {
			throw new IdentificadorDeCaronaInvalidoException();
		} else if (Carona == null) {
			throw new ItemInexistenteException();
		}

	}

	/**
	 * Método para validar argumentos para pegar carona.
	 * 
	 * @param id
	 * @param carona
	 * @throws Exception
	 */

	public void validaGetCarona(String id, Carona carona) throws Exception {

		if (id == null) {
			throw new CaronaInvalidaException();
		} else if (id.equals("")) {
			throw new CaronaInexistenteException();
		} else if (carona == null) {
			throw new CaronaInexistenteException();
		}

	}

	/**
	 * Método para validar argumentos para pegar o trajeto.
	 * 
	 * @param id
	 * @param carona
	 * @throws Exception
	 */
	public void validaGetTrajeto(String id, Carona carona) throws Exception {
		if (id == null) {
			throw new TrajetoInvalidoException();
		} else if (id.equals("")) {
			throw new TrajetoInexistenteException();
		} else if (carona == null) {
			throw new TrajetoInexistenteException();
		}

	}

	/**
	 * 
	 * @param idSolicitacao
	 * @param atributo
	 * @throws Exception
	 */
	public void validaGetAtributoSolicitacao(String idSolicitacao,
			String atributo) throws Exception {
		if (idSolicitacao == null || idSolicitacao.equals("")) {
			throw new SolicitacaoInexistenteException();
		} else if (atributo == null || atributo.equals("")) {
			throw new AtributoInvalido();
		}
	}

	public void validaLogin(String login) throws Exception {
		if (login == null || login.equals("")) {
			throw new LoginInvalidoException();
		}

	}

	public void validaNome(String nome) throws Exception {
		if (nome == null || nome.equals("")) {
			throw new NomeInvalidoException();
		}
	}

	public void validaSenha(String senha) throws Exception {
		if (senha == null || senha.equals("")) {
			throw new SenhaInvalidoException();
		}
	}

	public void validaEndereco(String endereco) throws Exception {
		if (endereco == null || endereco.equals("")) {
			throw new EnderecoInvalidoException();
		}
	}

	public void validaEmail(String email) throws Exception {
		if (email == null || email.equals("")) {
			throw new EmailInvalidoException();
		}
	}

	public void validaAtributo(String atributo) throws Exception {
		if (atributo == null || atributo.equals("")) {
			throw new AtributoInvalido();
		}
	}

	public void validaSessao(String idSessao) throws Exception {
		if (idSessao == null || idSessao.equals("")) {
			throw new SessaoInvalidaException();
		}
	}

	public void validaData(String data, String hora) throws Exception {
		if (data == null || data.equals("")
				|| !data.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
			throw new DataInvalidaException();
		}

		String[] dataSoNumeros = data.trim().split("/");

		Calendar calendarioAtual = new GregorianCalendar();

		Integer day = Integer.parseInt(dataSoNumeros[0]);
		Integer month = Integer.parseInt(dataSoNumeros[1]);
		Integer year = Integer.parseInt(dataSoNumeros[2]);


		this.calendario.set(Calendar.MONTH, month - 1);
		this.calendario.set(Calendar.YEAR, year);

		// se o ano que passei for anterior ao calendario atual
		if (year < calendarioAtual.get(Calendar.YEAR)) {
			throw new DataInvalidaException();
		}
		// se os dias for maior que o total de dias nesse ano passado como
		// paramentro
		if (day > this.calendario.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			throw new DataInvalidaException();
		}
		
		if (hora == null || hora.equals("") || !hora.matches("[0-9]{2}:[0-9]{2}")) {
			throw new HoraInvalidaException();
		}
		
		String[] horaSoNumeros = hora.trim().split(":");


		Integer hour = Integer.parseInt(horaSoNumeros[0]);
		Integer minut = Integer.parseInt(horaSoNumeros[1]);
		
		this.calendario.set(Calendar.HOUR, hour);
		this.calendario.set(Calendar.MINUTE, minut);
		

		if (this.calendario.getTime().compareTo(calendarioAtual.getTime()) == -1) {
			/*
			 * System.out.println(data + " " + hora);
			 * System.out.println("minha hora");
			 * System.out.println(calendario.getTime());
			 * System.out.println("hora do relogio");
			 * System.out.println(calendarioAtual.getTime());
			 */

			throw new DataInvalidaException();
		}
	}

	public void validaHora(String hora) throws Exception {
		if (hora == null || hora.equals("")
				|| !hora.matches("[0-9]{2}:[0-9]{2}")) {
			throw new HoraInvalidaException();
		}
	}

	public void validaVagas(String vagas) throws Exception {
		if (vagas == null || vagas.equals("") || !vagas.matches("^[0-9]*$")) {
			throw new VagaInvalidaException();
		}
	}

	public void validaOrigem(String origem) throws Exception {
		if (origem == null || origem.equals("")) {
			throw new OrigemInvalidaException();
		}
	}

	public void validaDestino(String destino) throws Exception {
		if (destino == null || destino.equals("")) {
			throw new DestinoInvalidoException();
		}
	}

	public void validaIdCarona(String idCarona) throws Exception {
		if (idCarona == null || idCarona.equals("")) {
			throw new IdentificadorDeCaronaInvalidoException();

		}

	}

	public void verificaEmailExistente(List<Usuario> usuariosCadastrados,
			String email) throws Exception {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(email)) {
				throw new EmailExistente();
			}
		}
	}

	public void verificaLoginExistente(List<Usuario> usuariosCadastrados,
			String login) throws Exception {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getLogin().equals(login)) {
				throw new LoginExistente();
			}
		}
	}

	public void verificaUsuarioInexistente(Usuario usuario) throws Exception {
		if (usuario == null)
			throw new UsuarioInexistente();
	}

	public void verificaSessaoInexistente(Map<String, Usuario> sessoesExistentes,
			String sessao) throws Exception {
		if (!sessoesExistentes.containsKey(sessao))
			throw new SessaoInexistenteException();
	}

	public void verificaItemInexistente(Carona carona) throws Exception {
		if (carona == null)
			throw new ItemInexistenteException();
	}

	public void validaReview(String review) throws Exception {
		if (!review.equals(NAO_FALTOU) && !review.equals(FALTOU)) {
			throw new OpcaoInvalidaException();
		}
		
	}
}
