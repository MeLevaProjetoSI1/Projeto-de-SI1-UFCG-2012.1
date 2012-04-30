package projeto;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import projectExeptions.LoginInvalidoException;
import projectExeptions.PontoInvalidoException;
import projectExeptions.SolicitacaoInexistenteException;

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
public class Controler {
	private int contadorDeCaronasID = 1;
	// private int contadorDeSugestoesID;
	private int contadorDeRespostaID;
	private Valida validar;

	private List<Usuario> usuariosCadastrados;
	private List<Carona> caronasCadastradas;

	private Map<String, Carona> sugestoesIDs;

	// private List<String> pontosSugeridos = new LinkedList<String>();
	private Map<String, Usuario> sessoesExistentes = new HashMap<String, Usuario>();

	private BancoDeDados bancoDeDados;

	/**
	 * Construtor.
	 */
	public Controler() {
		usuariosCadastrados = new LinkedList<Usuario>();
		caronasCadastradas = new LinkedList<Carona>();
		sugestoesIDs = new HashMap<String, Carona>();
		validar = new Valida();
		bancoDeDados = new BancoDeDados(this);
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

		this.validar.verificaEmailExistente(this.usuariosCadastrados, email);
		this.validar.verificaLoginExistente(this.usuariosCadastrados, login);

		this.usuariosCadastrados.add(new Usuario(login, senha, nome, endereco,
				email));
	}

	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public String abrirSessao(String login, String senha) throws Exception {
		Usuario usuario = null;
		String sessao = null;
		this.validar.validaLogin(login);
		this.validar.validaSenha(senha);

		for (Usuario user : this.usuariosCadastrados) {

			if (user.getLogin().equals(login)) {
				if (senha.equals(user.getSenha())) {
					sessao = "sessao" + login.substring(0, 1).toUpperCase()
							+ login.substring(1);
					// this.user = user;
					usuario = user;
					this.sessoesExistentes.put(sessao, user);
					break;
				} else {
					throw new LoginInvalidoException();
				}

			}
		}
		this.validar.verificaUsuarioInexistente(usuario);
		//getSecoes().put(sessao, usuario);

		return sessao;

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
		this.validar.verificaOrigemInvalida(origem);
		this.validar.verificaDestinoInvalido(destino);

		List<String> idsValidos = new LinkedList<String>();

		if (!this.caronasCadastradas.isEmpty()) {
			// Se a origem e o destino forem vazias
			// retorna todas as caronas possiveis cadastradas
			if (origem.equals("") && destino.equals("")) {
				for (Carona carona : this.caronasCadastradas) {
					idsValidos.add(carona.getIdSessao());
				}
				// se o destino eh vzio
				// retorno todos com a mesma origem
			} else if (!origem.equals("") && destino.equals("")) {
				for (Carona carona : this.caronasCadastradas) {
					if (carona.getOrigem().equals(origem)) {
						idsValidos.add(carona.getIdSessao());

					}
				}
				// se a origem eh vzio
				// retorno todos com o mesmo destino
			} else if (origem.equals("") && !destino.equals("")) {
				for (Carona carona : this.caronasCadastradas) {
					if (carona.getDestino().equals(destino)) {
						idsValidos.add(carona.getIdSessao());

					}
				}
			} else {
				// retorno todos com o mesmo destino e origem
				for (Carona carona : this.caronasCadastradas) {
					if (carona.getDestino().equals(destino)
							&& carona.getOrigem().equals(origem)) {
						idsValidos.add(carona.getIdSessao());

					}
				}
			}
		}

		return Arrays.toString(idsValidos.toArray()).replace("[", "{")
				.replace("]", "}").replace(" ", "");
	}

	/**
	 * Método que cadastra a carona.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @return
	 * @throws Exception
	 */
	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws Exception {

		this.validar.validaSessao(idSessao);
		this.validar.verificaSessaoInexistente(sessoesExistentes, idSessao);

		this.validar.validaOrigem(origem);
		this.validar.verificaOrigemInvalida(origem);

		this.validar.validaDestino(destino);
		this.validar.verificaDestinoInvalido(destino);

		this.validar.validaData(data, hora);
		this.validar.validaHora(hora);

		this.validar.validaVagas(vagas);

		// encontra carona e usuario
		Usuario usuarioQueEstaOferencendoACarona = buscaUsuarioPorLogin(idSessao
				.substring(6).toLowerCase());

		Carona carona = new Carona(usuarioQueEstaOferencendoACarona,
				getContadorDeCaronasID(), origem, destino, data, hora, vagas);

		// adiciona no historico de vagas oferecidas pelo usuario
		usuarioQueEstaOferencendoACarona
				.addNoHistoricoDeVagasOferecidas(carona);
		// adiciona no historico de vagas oferecidas pelo usuario
		usuarioQueEstaOferencendoACarona.addCaronaCadastrada(carona,
				carona.getIdSessao());

		// adiciona nas caronas cadastradas
		this.caronasCadastradas.add(carona);
		setContadorDeCaronasID(getContadorDeCaronasID() + 1);

		getSecoes().get(idSessao).addCarona(carona.getIdSessao());
		return carona.getIdSessao();

	}

	/**
	 * Método que zera o sistema.
	 */
	public void zerarSistema() {
		this.setContadorDeCaronasID(1);
		this.contadorDeRespostaID = 0;
		this.usuariosCadastrados.clear();
		this.caronasCadastradas.clear();

		this.sessoesExistentes.clear();
		this.sugestoesIDs.clear();

		// this.pontosSugeridos.clear();

		this.getSecoes().clear();
	}

	/**
	 * Metodo que encerra o sistema
	 */
	public boolean encerrarSistema() {
		return bancoDeDados.gravarDados();
	}

	/**
	 * 
	 * @param login
	 */
	public boolean encerrarSessao(String login) {
		for (String sessaoLogada : sessoesExistentes.keySet()) {
			if (sessaoLogada.contains(login)) {
				sessoesExistentes.remove(sessaoLogada);
				break;
			}
		}

		for (String sessaoLogada : sessoesExistentes.keySet()) {
			if (sessaoLogada.contains(login)) {
				return false;
			}
		}
		return true;

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

		Usuario usuario = buscaUsuarioPorLogin(login);

		validar.validaLogin(login);
		validar.validaAtributo(atributo);
		validar.verificaUsuarioInexistente(usuario);

		return usuario.getAtributoUsuario(atributo);
	}

	/**
	 * 
	 * @param id
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoCarona(String idCarona, String atributo)
			throws Exception {

		Carona carona = buscaCaronaPorID(idCarona);
		validar.validaAtributo(atributo);
		validar.validaIdCarona(idCarona);
		validar.verificaItemInexistente(carona);

		return carona.getAtributoCarona(atributo);
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
		Carona caronaQueTemSolicaitacao = null;
		Usuario usuarioQueSolicitou = null;
		for (Carona carona : caronasCadastradas) {
			// pego o usuario que tem a solicitacao
			for (String solicitacaoDoUsuario : carona
					.getSolicitacoesDeUsuarios()) {
				if (idSolicitacao.equals(solicitacaoDoUsuario)) {
					caronaQueTemSolicaitacao = carona;
					break;
				}
			}
		}

		for (Usuario us : usuariosCadastrados) {
			if (us.getSolicitacoesDoUsuario().containsKey(idSolicitacao)) {
				usuarioQueSolicitou = us;
			}
		}
		return caronaQueTemSolicaitacao.getAtributoSolicitacao(atributo,
				usuarioQueSolicitou);
	}

	/*
	 * Pegar atributos de um perfil
	 */
	public String getAtributoPerfil(String login, String atributo)
			throws Exception {
		Usuario usuario = buscaUsuarioPorLogin(login);

		return usuario.getAtributoPerfil(atributo);

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

	/*
	 * 
	 */
	public String getTodasCaronasUsuario(String idSessao) {
		Usuario usuarioQueEstaOferencendoACarona = buscaUsuarioPorLogin(idSessao
				.substring(6).toLowerCase());
		List<String> lista = new LinkedList<String>();
		Collection<Carona> listaDeCaronas = usuarioQueEstaOferencendoACarona
				.getListaDeCaronasCadastradasPeloUsuario().values();
		for (Carona carona : listaDeCaronas) {
			lista.add(carona.getIdSessao());
		}
		return lista.toString().replace("[", "{").replace("]", "}")
				.replace(" ", "");
	}

	/*
	 * 
	 */
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {

		Carona carona = buscaCaronaPorID(idCarona);
		return carona.getSolicitacoesConfirmadas().toString().replace("[", "{")
				.replace("]", "}").replace(" ", "");
	}

	/*
	 * 
	 */
	public String getSolicitacoesPendentes(String idSessao, String idCarona) {
		Carona carona = buscaCaronaPorID(idCarona);
		if (carona.getSolicitacoesPendentes().size() == 0) {
			return "";
		}
		return carona.getSolicitacoesPendentes().toString().replace("[", "{")
				.replace("]", "}").replace(" ", "");
	}

	/*
	 * 
	 */
	public String getPontosSugeridos(String idSessao, String idCarona) {
		Carona carona = buscaCaronaPorID(idCarona);
		return carona.getPontosSugeridosDeEncontro();
	}

	/*
	 * 
	 */
	public String getPontosEncontro(String idSessao, String idCarona) {
		Carona carona = buscaCaronaPorID(idCarona);
		return carona.getPontosEncontro();
	}

	/*
	 * 
	 */
	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review) throws Exception {

		Usuario user = buscaUsuarioPorLogin(idSessao.substring(6).toLowerCase());
		Carona carona = buscaCaronaPorID(idCarona);

		//if (!user.gethVagasEmCaronas().contains(carona)) {
		//	throw new UsuarioNaoPossuiVagaNaCaronaExeption();
		//}
		this.validar.validaReview(review);
		user.addReview(carona, review);

	}

	// Uusuario aceita uma solicitacao
	/*
	 * 
	 */
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws Exception {
		Usuario user = buscaUsuarioPorLogin(idSessao.substring(6).toLowerCase());
		Carona carona = null;
		for (Carona corona : caronasCadastradas) {
			if (corona.getSolicitacoesDeUsuarios().contains(idSolicitacao)) {
				carona = corona;
				break;
			}
		}
		//user.addNoHistoricoDeVagasEmCaronas(getTodasCaronasUsuario(idSessao));
		// remover a sugestao

		// contadorDeSugestoesID--;
		carona.aceitarSolicitacaoPontoEncontro(user, idSolicitacao);
		user.addCarona(carona.getIdSessao());

	}

	/*
	 * Método onde o motorista aceita uma solicitacao
	 */
	public void aceitarSolicitacao(String idSessao, String idSolicitacao)
			throws Exception {
		Usuario user = buscaUsuarioPorLogin(idSessao.substring(6).toLowerCase());
		Carona result = null;
		for (Carona carona : caronasCadastradas) {
			if (carona.getSolicitacoesDeUsuarios().contains(idSolicitacao)) {
				result = carona;
				break;
			}
		}
		// falta
		if (result == null) {
			throw new SolicitacaoInexistenteException();
		}
		//user.addNoHistoricoDeVagasEmCaronas(result.getIdSessao());
		result.aceitarSolicitacao(user, idSolicitacao);
		user.addCarona(result.getIdSessao());

	}

	/*
	 * 
	 */
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws Exception {
		Usuario usuario = buscaUsuarioPorLogin(idSessao.substring(6)
				.toLowerCase());
		Carona carona = buscaCaronaPorID(idCarona);

		// gerar id de solicitação
		return usuario.solicitarVagaPontoEncontro(carona, ponto);

	}

	/*
	 * 
	 */
	public String solicitarVaga(String idSessao, String idCarona)
			throws Exception {
		validar.validaSessao(idSessao);
		validar.validaIdCarona(idCarona);

		Usuario caroneiro = buscaUsuarioPorLogin(idSessao.substring(6)
				.toLowerCase());
		Carona caronaQueRecebeSolicitacaoDaVaga = buscaCaronaPorID(idCarona);

		return caroneiro.solicitarVaga(caronaQueRecebeSolicitacaoDaVaga);

	}

	/*
	 * 
	 */
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)
			throws Exception {
		Usuario user = buscaUsuarioPorLogin(idSessao.substring(6).toLowerCase());
		Carona carona = null;
		for (Carona corona : caronasCadastradas) {
			if (corona.getSolicitacoesDeUsuarios().contains(idSolicitacao)) {
				carona = corona;
				break;
			}
		}
		if (carona == null) {
			throw new SolicitacaoInexistenteException();
		}
		// contadorDeSugestoesID--;
		carona.rejeitarSolicitacao(user, idSolicitacao);

	}

	/**
	 * Método interno que busca o usuário ja cadastrado por login.
	 * 
	 * @param login
	 * @return
	 */
	protected Usuario buscaUsuarioPorLogin(String login) {
		Usuario result = null;
		for (Usuario element : this.getSecoes().values()) {
			if (element.getLogin().equals(login)) {
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

	// usuario sugerir ponto de encontro com a carona
	/*
	 * 
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws Exception {
		if (pontos.equals("")) {
			throw new PontoInvalidoException();
		}
		Usuario usuarioSugerePonto = buscaUsuarioPorLogin(idSessao.substring(6)
				.toLowerCase());
		Carona caronaQueRecebeSugestao = buscaCaronaPorID(idCarona);

		String sugestao;
		if (this.sugestoesIDs.size() == 0) {
			sugestao = "sugestaoID";
		} else {
			sugestao = "sugestao" + this.sugestoesIDs.size() + "ID";
		}

		for (Carona carona : this.sugestoesIDs.values()) {
			if (carona.equals(caronaQueRecebeSugestao)) {
				throw new PontoInvalidoException();

			}
		}

		usuarioSugerePonto.sugerirPontoEncontro(caronaQueRecebeSugestao,
				pontos, sugestao);

		// this.pontosSugeridos.add(pontos);
		this.sugestoesIDs.put(sugestao, caronaQueRecebeSugestao);
		// contadorDeSugestoesID++;
		return sugestao;
	}

	// o carona responde ao usuario a solicitacao da vaga
	// o usuario que cadastrou a carona, recebe a solicitacao e pode aprovala ou
	// nao
	/*
	 * 
	 */
	public String responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) throws Exception {
		if (pontos.equals("")) {
			throw new PontoInvalidoException();
		}
		Usuario usuarioResponde = buscaUsuarioPorLogin(idSessao.substring(6)
				.toLowerCase());
		Carona caronaQueRecebeuSolicitacao = buscaCaronaPorID(idCarona);
		String[] pontosSugeridos = pontos.split(";");
		for (String ponto : pontosSugeridos) {
			ponto = ponto.trim();
		}
		String id = null;
		for (String string : sugestoesIDs.keySet()) {
			if (string.equals(idSugestao)) {
				id = idSugestao;
				break;
			}
		}

		caronaQueRecebeuSolicitacao.responderSugestaoPontoEncontro(
				usuarioResponde, caronaQueRecebeuSolicitacao, id,
				pontosSugeridos);
		if (contadorDeRespostaID == 0) {
			return "respostaID";
		}
		return "resposta" + contadorDeRespostaID + "ID";
	}

	/*
	 * 
	 */
	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) {
		Usuario usuarioSugerePonto = buscaUsuarioPorLogin(idSessao.substring(6)
				.toLowerCase());
		Carona caronaQueRecebeSugestao = buscaCaronaPorID(idCarona);
		usuarioSugerePonto.desistirRequisicao(caronaQueRecebeSugestao,
				idSolicitacao);
	}

	/*
	 * 
	 */
	public String visualizarPerfil(String idSessao, String login)
			throws LoginInvalidoException {
		Usuario usuario = buscaUsuarioPorLogin(login);
		if (usuario == null) {
			throw new LoginInvalidoException();
		}
		return "perfil" + usuario.getLogin().substring(0, 1).toUpperCase()
				+ usuario.getLogin().substring(1);

	}

	/*
	 * Metodo sair
	 */
	public void quit() {
		// System.exit(0);
	}

	/*
	 * Metodo para reiniciar o sistema
	 */
	public void reiniciarSistema() {
		// bancoDeDados.lerDados();

	}

	/*
	 * 
	 */
	public int getContadorDeCaronasID() {
		return this.contadorDeCaronasID;
	}

	/*
	 * 
	 */
	public void setContadorDeCaronasID(int contadorDeCaronasID) {
		this.contadorDeCaronasID = contadorDeCaronasID;
	}

	/*
	 * 
	 */
	public Map<String, Usuario> getSecoes() {
		return sessoesExistentes;
	}

	public String getCaronaUsuario(String sessao, int i) {
		Usuario usuarioSugerePonto = buscaUsuarioPorLogin(sessao.substring(6)
				.toLowerCase());

		return usuarioSugerePonto.getListaDeCaronasCadastradasPeloUsuario()
				.get(i).getIdSessao();
	}

	public List<Usuario> getUsuariosCadastrados() {
		// TODO Auto-generated method stub
		return this.usuariosCadastrados;
	}

	public List<Carona> getCaronasCadastradas() {
		// TODO Auto-generated method stub
		return this.caronasCadastradas;
	}

	public void setUsuariosCadastrados(List<Usuario> usuarios) {
		this.usuariosCadastrados = usuarios;

	}

	public void setCaronasCadastrados(List<Carona> caronas) {
		this.caronasCadastradas = caronas;

	}

}