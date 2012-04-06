package projeto;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import projectExeptions.*;

/**
 * UFCG - CEEI - DSC Disciplina: Sistema de Informação I. Professor: Nazareno.
 * 
 * Projeto SI1 2012.1.
 * 
 * Classe principal do Sistema Me Leva
 * 
 * 
 * @author Grupo do Projeto MeLeva
 * @version 1.0
 * 
 */
public class MeLeva {
	private int contadorDeCaronasID = 1;
	private Usuario user;
	private List<Usuario> usuariosCadastrados;
	private List<Carona> caronasCadastradas;
	private List<String> sessoesExistentes;
	private Valida validar;

	/**
	 * Construtor
	 */
	public MeLeva() {
		usuariosCadastrados = new LinkedList<Usuario>();
		caronasCadastradas = new LinkedList<Carona>();
		sessoesExistentes = new LinkedList<String>();
		validar = new Valida();

	}

	/**
	 * Método para criar um usuário
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {

		if (buscaUsuarioPorEmail(email) != null) {
			throw new EmailExistente();
		}
		usuariosCadastrados
				.add(new Usuario(login, senha, nome, endereco, email));
	}

	/**
	 * 
	 * @param login
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	public void criarUsuario(String login, String nome, String endereco,
			String email) throws Exception {

		if (buscaUsuarioPorEmail(email) != null) {
			throw new EmailExistente();
		}
		usuariosCadastrados.add(new Usuario(login, nome, endereco, email));
	}

	/**
	 * 
	 * @param login
	 * @param nome
	 * @param endereco
	 * @throws Exception
	 */
	public void criarUsuario(String login, String nome, String endereco)
			throws Exception {

		if (buscaUsuarioPorLogin(login) != null) {
			throw new LoginExistente();
		}
		usuariosCadastrados.add(new Usuario(login, nome, endereco));
	}

	/**
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @return
	 * @throws Exception
	 */
	public String localizarCarona(String idSessao, String origem, String destino)
			throws Exception {
		if (origemNaoEhValida(origem)) {
			throw new OrigemInvalidaException();
		} else if (destinoNaoEhValido(destino)) {
			throw new DestinoInvalidoException();
		}

		List<String> idsValidos = new LinkedList<String>();

		if (!caronasCadastradas.isEmpty()) {
			// Se a origem e o destino forem vazias
			// retorna todas as caronas possiveis cadastradas
			if (origem.equals("") && destino.equals("")) {
				for (Carona carona : caronasCadastradas) {
					idsValidos.add(carona.getIdSessao());
				}
				// se o destino eh vzio
				// retorno todos com a mesma origem
			} else if (!origem.equals("") && destino.equals("")) {
				for (Carona carona : caronasCadastradas) {
					if (carona.getOrigem().equals(origem)) {
						idsValidos.add(carona.getIdSessao());

					}
				}
				// se a origem eh vzio
				// retorno todos com o mesmo destino
			} else if (origem.equals("") && !destino.equals("")) {
				for (Carona carona : caronasCadastradas) {
					if (carona.getDestino().equals(destino)) {
						idsValidos.add(carona.getIdSessao());

					}
				}
			} else {
				// retorno todos com o mesmo destino e origem
				for (Carona carona : caronasCadastradas) {
					if (carona.getDestino().equals(destino)
							&& carona.getOrigem().equals(origem)) {
						idsValidos.add(carona.getIdSessao());

					}
				}
			}
		}

		return criaArray(idsValidos);
	}

	private String criaArray(List<String> idsValidos) {
		String arrayAxi;
		StringBuffer array = new StringBuffer();
		array.append("{");
		for (int i = 0; i < idsValidos.size(); i++) {
			array.append(idsValidos.get(i) + ",");
		}
		if (idsValidos.isEmpty()) {
			arrayAxi = "{}";
		} else {
			arrayAxi = array.substring(0, array.length() - 1) + "}";
		}
		return arrayAxi;
	}

	/**
	 * 
	 * @param origem
	 * @return
	 */
	private boolean origemNaoEhValida(String origem) {
		return (origem.contains("-") || origem.contains("()")
				|| origem.contains("!") || origem.contains("!?"));

	}

	/**
	 * 
	 * @param destino
	 * @return
	 */
	private boolean destinoNaoEhValido(String destino) {
		return (destino.contains(".") || destino.contains("()") || destino
				.contains("!?"));

	}

	/**
	 * 
	 * @param idSecao
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @return
	 * @throws Exception
	 */
	public String cadastrarCarona(String idSecao, String origem,
			String destino, String data, String hora, String vagas)
			throws Exception {

		if (idSecao == null || idSecao.equals("")) {
			throw new SessaoInvalidaException();
		} else if (!sessoesExistentes.contains(idSecao)) {
			throw new SessaoInexistenteException();
		} else if (origem == null || origem.equals("")) {
			throw new OrigemInvalidaException();
		} else if (destino == null || destino.equals("")) {
			throw new DestinoInvalidoException();
		} else if (data == null || data.equals("")
				|| !validar.estruturaDeData(data)) {
			throw new DataInvalidaException();
		} else if (hora == null || hora.equals("")
				|| !validar.estruturaDeHora(hora)) {
			throw new HoraInvalidaException();
		} else if (vagas == null || !validar.estruturaDeVagas(vagas)) {
			throw new VagaInvalidaException();
		}
		validar.dataValida(data.split("/"));

		Carona carona = new Carona(contadorDeCaronasID, origem, destino, data,
				hora, vagas);

		caronasCadastradas.add(carona);
		contadorDeCaronasID++;
		return carona.getIdSessao();

	}

	/**
	 * 
	 */
	public void zerarSistema() {
		usuariosCadastrados.clear();
		caronasCadastradas.clear();
		sessoesExistentes.clear();
		contadorDeCaronasID = 1;
	}

	/**
	 * 
	 */
	public void encerrarSistema() {

	}

	/**
	 * 
	 * @param login
	 */
	public void encerrarSistema(String login) {

	}

	/**
	 * 
	 * @param login
	 */
	public void encerrarSessao(String login) {
		for (String sessaoLogada : sessoesExistentes) {
			if (sessaoLogada.contains(login)) {
				sessoesExistentes.remove(sessaoLogada);
			}
		}

	}

	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public String abrirSessao(String login, String senha) throws Exception {
		Usuario usuario;

		if (login == null || senha == null) {
			throw new LoginInvalidoException();
		} else if (login.equals("")) {
			throw new LoginInvalidoException();
		}
		for (Iterator<Usuario> iterator = usuariosCadastrados.iterator(); iterator
				.hasNext();) {
			usuario = (Usuario) iterator.next();
			if (usuario.getLogin().equals(login)) {
				if (usuario.getSenha().equals(senha)) {
					user = usuario;
					String sessao = "sessao" + user.getNome().split(" ")[0];
					sessoesExistentes.add(sessao);
					return sessao;
				} else {
					throw new LoginInvalidoException();
				}
			}
		}
		throw new UsuarioInexistente();
	}

	/**
	 * 
	 * @param login
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws Exception {

		if (login == null || login.equals("")) {
			throw new LoginInvalidoException();
		} else if (atributo == null || atributo.equals("")) {
			throw new AtributoInvalido();
		} else if (buscaUsuarioPorLogin(login) == null) {
			throw new UsuarioInexistente();
		}

		return user.getAtributoUsuario(atributo);
	}

	/**
	 * 
	 * @param login
	 * @return
	 */
	private Usuario buscaUsuarioPorLogin(String login) {
		Usuario result = null;
		for (Usuario element : usuariosCadastrados) {
			if (element.getLogin().equals(login)) {
				result = element;
				break;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param id
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoCarona(String id, String atributo)
			throws Exception {
		if (id == null || id.equals("")) {
			throw new IdentificadorDeCaronaInvalidoException();
		} else if (atributo == null || atributo.equals("")) {
			throw new AtributoInvalido();
		}

		Carona carona = buscaCaronaPorID(id);
		if (carona == null) {
			throw new ItemInexistenteException();
		} else {
			return carona.getAtributoCarona(atributo);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private Carona buscaCaronaPorID(String id) {
		Carona result = null;
		for (Carona carona : caronasCadastradas) {
			if (carona.getIdSessao().equals(id)) {
				result = carona;
			}
		}
		return result;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getCarona(String id) throws Exception {
		if (id == null) {
			throw new CaronaInvalidaException();
		} else if (id.equals("")) {
			throw new CaronaInexistenteException();
		}
		Carona carona = buscaCaronaPorID(id);
		if (carona == null) {
			throw new CaronaInexistenteException();
		}
		return carona.getOrigem() + " para " + carona.getDestino()
				+ ", no dia " + carona.getData() + ", as " + carona.getHora();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getTrajeto(String id) throws Exception {
		if (id == null) {
			throw new TrajetoInvalidoException();
		} else if (id.equals("")) {
			throw new TrajetoInexistenteException();
		}
		Carona carona = buscaCaronaPorID(id);
		if (carona == null) {
			throw new TrajetoInexistenteException();
		}
		return carona.getOrigem() + " - " + carona.getDestino();
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	private Usuario buscaUsuarioPorEmail(String email) {
		Usuario result = null;
		for (Usuario element : usuariosCadastrados) {
			if (element.getEmail().equals(email)) {
				result = element;
				break;
			}
		}
		return result;

	}

}
