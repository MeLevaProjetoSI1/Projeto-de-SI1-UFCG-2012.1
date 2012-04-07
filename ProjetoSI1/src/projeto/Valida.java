package projeto;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import projectExeptions.AtributoInvalido;
import projectExeptions.CaronaInexistenteException;
import projectExeptions.CaronaInvalidaException;
import projectExeptions.DataInvalidaException;
import projectExeptions.DestinoInvalidoException;
import projectExeptions.HoraInvalidaException;
import projectExeptions.IdentificadorDeCaronaInvalidoException;
import projectExeptions.ItemInexistenteException;
import projectExeptions.LoginInvalidoException;
import projectExeptions.OrigemInvalidaException;
import projectExeptions.SessaoInexistenteException;
import projectExeptions.SessaoInvalidaException;
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
	private Calendar calendario;

	/**
	 * Construtor.
	 */
	public Valida() {
		calendario = new GregorianCalendar();
	}

	/**
	 * Metodo para verificar se a data é correta.
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void dataValida(String[] data) throws Exception {
		Calendar calendarioAtual = new GregorianCalendar();
		Integer day = Integer.parseInt(data[0]);
		Integer month = Integer.parseInt(data[1]);
		Integer year = Integer.parseInt(data[2]);
		this.calendario.set(Calendar.MONTH, month - 1);
		this.calendario.set(Calendar.YEAR, year);

		// se o ano que passei for anterior ao calendario atual
		if (year < calendarioAtual.get(Calendar.YEAR)) {
			throw new DataInvalidaException();
		}
		// se o dias for maior que o total de dias nesse ano passado como
		// paramentro
		if (day > this.calendario.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			throw new DataInvalidaException();
		}

	}

	/**
	 * Método para validar argumentos origem ou destino são validos.
	 * 
	 * @param origem
	 * @return
	 */
	public boolean validaOrigemOuDestinoNaoEhValida(String origemOuDestino) {
		return (origemOuDestino.contains("-") || origemOuDestino.contains("()")
				|| origemOuDestino.contains("!")
				|| origemOuDestino.contains("!?") || origemOuDestino
					.contains("."));

	}

	/**
	 * Método para verificar se a estrutura da data recebida é 00:00.
	 * 
	 * @param data
	 * @return
	 */
	public boolean validaEstruturaDeData(String data) {
		return data.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}");
	}

	/**
	 * Método para verificar se a estrutura da hora recebida é 00/00/0000.
	 * 
	 * @param hora
	 * @return
	 */
	public boolean validaEstruturaDeHora(String hora) {
		return hora.matches("[0-9]{2}:[0-9]{2}");
	}

	/**
	 * Metodo para verificar se a estrutura da vaga recebida esta correta.
	 * 
	 * @param vagas
	 * @return
	 */
	public boolean validaEstruturaDeVagas(String vagas) {
		return vagas.matches("^[0-9]*$");
	}

	/**
	 * Método para validar o argumentos do cadastramento de carona.
	 * 
	 * @param sessoesExistentes
	 * @param idSecao
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @throws Exception
	 */
	public void validaCadastraCarona(List<String> sessoesExistentes, String idSecao,
			String origem, String destino, String data, String hora,
			String vagas) throws Exception {
		if (idSecao == null || idSecao.equals("")) {
			throw new SessaoInvalidaException();
		} else if (!sessoesExistentes.contains(idSecao)) {
			throw new SessaoInexistenteException();
		} else if (origem == null || origem.equals("")) {
			throw new OrigemInvalidaException();
		} else if (destino == null || destino.equals("")) {
			throw new DestinoInvalidoException();
		} else if (data == null || data.equals("") || !validaEstruturaDeData(data)) {
			throw new DataInvalidaException();
		} else if (hora == null || hora.equals("") || !validaEstruturaDeHora(hora)) {
			throw new HoraInvalidaException();
		} else if (vagas == null || !validaEstruturaDeVagas(vagas)) {
			throw new VagaInvalidaException();
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
	 * @param userAxi
	 * @throws Exception
	 */
	public void validaGetAtributoUsuario(String login, String atributo,
			Usuario userAxi) throws Exception {
		if (login == null || login.equals("")) {
			throw new LoginInvalidoException();
		} else if (atributo == null || atributo.equals("")) {
			throw new AtributoInvalido();
		} else if (userAxi == null) {
			throw new UsuarioInexistente();
		}

	}

	/**
	 * Método para validar argumentos para pegar o atributo da carona.
	 * 
	 * @param id
	 * @param atributo
	 * @param carona
	 * @throws Exception
	 */
	public void validaGetAtributoCarona(String id, String atributo,
			Carona carona) throws Exception {
		if (id == null || id.equals("")) {
			throw new IdentificadorDeCaronaInvalidoException();
		} else if (atributo == null || atributo.equals("")) {
			throw new AtributoInvalido();
		} else if (carona == null) {
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

}
