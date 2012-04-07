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
 * Classe Principal do Sistema Me Leva
 * 
 * 
 * @author Grupo do Projeto MeLeva
 * @version 1.0
 * 
 */
public class MeLeva {
	private int contadorDeCaronasID = 1;
	private int contadorDeSolicitacoesID = 1;
	private int contadorDeSugestoesID = 1;
	private Usuario user;
	private List<Usuario> usuariosCadastrados;
	private List<Carona> caronasCadastradas;
	private List<String> sessoesExistentes;
	private List<Solicitacao> solicitacoes;
	private Valida validar;

	/**
	 * Construtor.
	 */
	public MeLeva() {
		usuariosCadastrados = new LinkedList<Usuario>();
		caronasCadastradas = new LinkedList<Carona>();
		sessoesExistentes = new LinkedList<String>();
		solicitacoes = new LinkedList<Solicitacao>();
		validar = new Valida();

	}

	/**
	 * Método que cria o usuario.
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
	 * Método que cria o usuario.
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
	 * Método que cria o usuario.
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
	 * Método que Localiza a carona.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @return
	 * @throws Exception
	 */
	public String localizarCarona(String idSessao, String origem, String destino)
			throws Exception {
		if (validar.validaOrigemOuDestinoNaoEhValida(origem)) {
			throw new OrigemInvalidaException();
		} else if (validar.validaOrigemOuDestinoNaoEhValida(destino)) {
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

	/**
	 * Método interno para criar uma string como array sem espaços. Ex.:
	 * {1,1,1,1} != {1, 1, 1, 1}
	 * 
	 * @param idsValidos
	 * @return
	 */
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
	 * Método que cadastra a carona.
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

		validar.validaCadastraCarona(sessoesExistentes, idSecao, origem, destino,
				data, hora, vagas);
		validar.dataValida(data.split("/"));

		Carona carona = new Carona(contadorDeCaronasID, origem, destino, data,
				hora, vagas);
		caronasCadastradas.add(carona);
		contadorDeCaronasID++;
		return carona.getIdSessao();

	}

	/**
	 * Método que zera o sistema.
	 */
	public void zerarSistema() {
		usuariosCadastrados.clear();
		caronasCadastradas.clear();
		sessoesExistentes.clear();
		solicitacoes.clear();
		contadorDeSolicitacoesID = 1;
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
				break;
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

		validar.validaAbrirSessao(login, senha);
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

		validar.validaGetAtributoUsuario(login, atributo,
				buscaUsuarioPorLogin(login));

		return user.getAtributoUsuario(atributo);
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

		validar.validaGetAtributoCarona(id, atributo, buscaCaronaPorID(id));

		return buscaCaronaPorID(id).getAtributoCarona(atributo);
	}

	/**
	 * Método que pela solicitação, buscada por ID, retona o atributo refente a
	 * ela.
	 * 
	 * @param idSolicitacao
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoSolicitacao(String idSolicitacao, String atributo)
			throws Exception {

		return buscaSolicitacaoPorID(idSolicitacao).getAtributoSolicitacao(
				atributo);

	}

	/**
	 * Método interno que busca o usuário ja cadastrado por login.
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
	 * Método interno que busca o usuário ja cadastrado por email.
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

	/**
	 * Método interno que busca a carona ja cadastrada.
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
	 * Método interno que busca a solicitação por ID, ID referente as
	 * solicitações.
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private Solicitacao buscaSolicitacaoPorID(String id) throws Exception {
		Solicitacao result = null;
		for (Solicitacao solicitacao : solicitacoes) {
			if (solicitacao.getIdSolicitacao().equals("{" + id + "}")) {
				result = solicitacao;
				break;
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

		Carona carona = buscaCaronaPorID(id);

		validar.validaGetCarona(id, carona);
		
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

		Carona carona = buscaCaronaPorID(id);
	
		validar.validaGetTrajeto(id, carona);
		
		return carona.getOrigem() + " - " + carona.getDestino();
	}

	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @throws Exception
	 */
	public void solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String pontos) throws Exception {

		Solicitacao solicitacao = new Solicitacao(contadorDeSolicitacoesID,
				pontos, buscaCaronaPorID(idCarona), user);
		solicitacoes.add(solicitacao);
		contadorDeSolicitacoesID++;

	}

	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 */
	public void solicitarVaga(String idSessao, String idCarona) {

		Solicitacao solicitacao = new Solicitacao(contadorDeSolicitacoesID,
				buscaCaronaPorID(idCarona), user);
		solicitacoes.add(solicitacao);
		contadorDeSolicitacoesID++;

	}

	/**
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws Exception
	 */
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws Exception {
		Solicitacao solicitacao = buscaSolicitacaoPorID(idSolicitacao);
		if (solicitacao == null) {
			throw new SolicitacaoInexistenteException();
		}

		buscaCaronaPorID(solicitacao.getCarona().getIdSessao()).setVagas( // Diminuir
																			// 1
																			// vaga
				String.valueOf(Integer.parseInt(solicitacao.getCarona()
						.getVagas()) - 1));
		solicitacoes.remove(solicitacao);
	}

	/**
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws Exception
	 */
	public void aceitarSolicitacao(String idSessao, String idSolicitacao)
			throws Exception {
		aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}

	/**
	 * Usuario sugerir ponto de encontro para uma carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) {

		//Carona caronaAReceberSugestao = buscaCaronaPorID(idCarona);
		
		//Usuario usuarioDaSessao = buscaUsuarioPorLogin(idSessao.substring(6).toLowerCase());
		
		String sugestaoID = null;
		if (sessoesExistentes.contains(idSessao)) {
			if (contadorDeCaronasID == 0) {
				sugestaoID = "sugestaoID";
				contadorDeCaronasID++;
			} else {
				sugestaoID = "sugestao"+contadorDeCaronasID+"ID";
				contadorDeCaronasID++;
			}

			//por que da null se eu colocar pra funcionar o ponto sugerido
			//usuarioDaSessao.sugerirPontoEncontro(caronaAReceberSugestao, pontos);
		}

		
		return sugestaoID;
	}

	/**
	 * 
	 * @param idSessao
	 * @param idEncontro
	 * @param idSugestao
	 * @param pontos
	 * @throws Exception
	 */
	public void responderSugestaoPontoEncontro(String idSessao,
			String idEncontro, String idSugestao, String pontos)
			throws Exception {
		if (pontos == null || pontos.equals("")) {
			throw new PontoInvalidoException();
		}

	}

	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 */
	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) {
		return;
	}

	/**
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws Exception
	 */
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)
			throws Exception {
		if (buscaSolicitacaoPorID(idSolicitacao) == null) {
			throw new SolicitacaoInexistenteException();
		}
	}
}
