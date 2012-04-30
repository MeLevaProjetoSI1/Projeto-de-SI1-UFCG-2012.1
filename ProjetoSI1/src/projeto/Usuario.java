package projeto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import projectExeptions.AtributoInexistenteException;

/**
 * UFCG - CEEI - DSC Disciplina: Sistema de Informacao I. Professor: Nazareno.
 * 
 * Projeto SI1 2012.1.
 * 
 * Classe para representação de um usuario.
 * 
 * @author Grupo do Projeto MeLeva.
 * @version 1.0
 * 
 */
public class Usuario {
	private String login, senha, nome, endereco, email;
	private Valida validar = new Valida();
	private String[] sugestaoDaCarona;

	private Map<String, Boolean> solicitacoesDoUsuario = new HashMap<String, Boolean>();
	private Map<String, Carona> sugestoesDoUsuario = new HashMap<String, Carona>();
	

	private Map<Integer ,Carona> listaDeCaronasCadastradasPeloUsuario = new HashMap<Integer ,Carona>();
	private int caronasQueFuncionaram = 0;
	private int caronasQueNaoFuncionaram = 0;
	private final String NOME = "nome";
	private final String ENDERECO = "endereco";
	private final String EMAIL = "email";
	private final String H_CARONAS = "historico de caronas";
	private final String H_VAGAS_EM_CARONAS = "historico de vagas em caronas";
	private final String CARONAS_Q_FUNCIONARAM = "caronas seguras e tranquilas";
	private final String CARONAS_Q_N_FUNCIONARAM = "caronas que não funcionaram";
	private final String FALTAS_DE_VAGAS = "faltas em vagas de caronas";
	private final String PRESENCAS_EM_VAGAS = "presenças em vagas de caronas";
	private final String FALTOU = "faltou";
	private final String NAO_FALTOU = "não faltou";
	
	private String pontoSolicitadoNaVagaDoCarona;
	
	private List<Carona> caronasQueFaltou = new LinkedList<Carona>();
	private List<Carona> caronasQueNaoFaltou = new LinkedList<Carona>();
	private List<Carona> caronasOferecidas = new LinkedList<Carona>();
	
	private List<String> hCaronas = new LinkedList<String>();
	private List<Carona> hVagasEmCaronas = new LinkedList<Carona>();
	private List<String> lista = new LinkedList<String>();
	
	/**
	 * Construtor de um usuário.
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	public Usuario(String login, String senha, String nome, String endereco,
			String email) throws Exception {

		validar.validaLogin(login);
		validar.validaSenha(senha);
		validar.validaNome(nome);
		validar.validaEndereco(endereco);
		validar.validaEmail(email);

		
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
	}


	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontosSugeridos
	 * @param sugestao 
	 */

	public void sugerirPontoEncontro(Carona caronaQueRecebeSugestao,
			String pontosSugeridos, String sugestaoID) {
		caronaQueRecebeSugestao.setPontosSugeridosDeEncontro(this, pontosSugeridos, sugestaoID);
		this.sugestoesDoUsuario.put(sugestaoID, caronaQueRecebeSugestao);
		

	}

	/*
	 * 
	 */
	public String solicitarVagaPontoEncontro(Carona carona,
			String ponto) throws Exception {
		this.pontoSolicitadoNaVagaDoCarona = ponto;
		return carona.solicitarVagaPontoEncontro(this, ponto, this.solicitacoesDoUsuario);
	}

	/*
	 * 
	 */
	public String solicitarVaga(Carona caronaQueRecebeSolicitacaoDaVaga) throws Exception {
		return caronaQueRecebeSolicitacaoDaVaga.solicitarVaga(this, this.solicitacoesDoUsuario);
		
	}

	/*
	 * 
	 */
	public List<String> getSugestaoDaCarona() {
		return Arrays.asList(this.sugestaoDaCarona);
	}

	/**
	 * 
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoUsuario(String atributo) throws Exception {
		if (atributo.equals(NOME)) {
			return getNome();
		} else if (atributo.equals(ENDERECO)) {
			return getEndereco();
		} else {
			throw new AtributoInexistenteException();
		}
	}
	
	/*
	 * 
	 */
	public String getAtributoPerfil(String atributo) throws Exception {
		if (atributo.equals(NOME)) {
			return getNome();
			
		} else if (atributo.equals(ENDERECO)) {
			return getEndereco();
			
		} else if (atributo.equals(EMAIL)) {
			return getEmail();
			
		} else if (atributo.equals(H_CARONAS)) {
			System.out.println(hCaronas);
			return Arrays.toString(this.hCaronas.toArray()).replace("{", "[").replace("}", "]");
			//historico de vagas em caronas
		} else if (atributo.equals(H_VAGAS_EM_CARONAS)) {
			return Arrays.toString(this.hVagasEmCaronas.toArray()).replace("{", "[").replace("}", "]");
			
		} else if (atributo.equals(CARONAS_Q_FUNCIONARAM)) {
			return String.valueOf(getCaronasQueFuncionaram());
			
		} else if (atributo.equals(CARONAS_Q_N_FUNCIONARAM)) {
			return String.valueOf(getCaronasQueNaoFuncionaram());
			
		} else if (atributo.equals(FALTAS_DE_VAGAS)) {
			return String.valueOf(getFaltouNasVagas());
			
		} else if (atributo.equals(PRESENCAS_EM_VAGAS)) {
			return String.valueOf(getPresencaEmVagas());
			
		} else {
			throw new AtributoInexistenteException();
		}
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/*
	 * 
	 */
	public List<String> gethCaronas() {
		return hCaronas;
	}

	/*
	 * 
	 */
	public void sethCaronas(List<String> hCaronas) {
		this.hCaronas = hCaronas;
	}

	/*
	 * 
	 */
	public List<Carona> gethVagasEmCaronas() {
		return hVagasEmCaronas;
	}

	public int getCaronasQueFuncionaram() {
		return this.caronasQueFuncionaram;
	}

	/*
	 * 
	 */
	public void setCaronasQueFuncionaram(int caronasQueFuncionaram) {
		this.caronasQueFuncionaram = caronasQueFuncionaram;
	}

	/*
	 * 
	 */
	public int getCaronasQueNaoFuncionaram() {
		return this.caronasQueNaoFuncionaram;
	}

	/*
	 * 
	 */
	public void setCaronasQueNaoFuncionaram(int caronasQueNaoFuncionaram) {
		this.caronasQueNaoFuncionaram = caronasQueNaoFuncionaram;
	}

	/*
	 * 
	 */
	public int getFaltouNasVagas() {
		return this.caronasQueFaltou.size();
	}

	/*
	 * 
	 */
	public int getPresencaEmVagas() {
		return this.caronasQueNaoFaltou.size();
	}


	/*
	 * 
	 */
	public void setSugestaoDaCarona(String[] pontosSugeridos) {
		this.sugestaoDaCarona = pontosSugeridos;
		
	}

	/*
	 * 
	 */
	public void addCarona(String idSessao) {
		hCaronas.add(idSessao);
	}

	/*
	 * 
	 */
	public String getCaronaUsuario(String idSessao, String indexCarona) {
		return hCaronas.get(Integer.valueOf(indexCarona));
	}

	/*
	 * 
	 */
	public void desistirRequisicao(Carona caronaQueRecebeSugestao, String idSugestao) {
		this.solicitacoesDoUsuario.remove(idSugestao);
		
	}
	
	/*
	 * 
	 */
	public Map<String, Boolean> getSolicitacoesDoUsuario() {
		return this.solicitacoesDoUsuario;
	}

	/*
	 * 
	 */
	public void addCaronaCadastrada(Carona carona, String iDSessao) {
		this.listaDeCaronasCadastradasPeloUsuario.put(this.listaDeCaronasCadastradasPeloUsuario.size()+1, carona);
		
	}
	
	/*
	 * 
	 */
	public Map<Integer ,Carona> getListaDeCaronasCadastradasPeloUsuario() {
		return this.listaDeCaronasCadastradasPeloUsuario;
	}

	/*
	 * 
	 */
	public String getPontoEncontroSolicitado() {
		// TODO Auto-generated method stub
		return pontoSolicitadoNaVagaDoCarona;
	}
	
	/*
	 * 
	 */
	public Map<String, Carona> getSugestoesDoUsuario() {
		return sugestoesDoUsuario;
	}


	//quantas vagas eu consegui
	public void addNoHistoricoDeVagasEmCaronas(Carona carona) {
		this.hVagasEmCaronas.add(carona);
		
	}

	public void addReview(Carona carona, String review) {
		if (review.equals(FALTOU)) {
			this.caronasQueFaltou.add(carona);
		}
		else if (review.equals(NAO_FALTOU)) {
			this.caronasQueNaoFaltou.add(carona);
		}
		
	}


	public void addNoHistoricoDeVagasOferecidas(Carona carona) {
		this.caronasOferecidas.add(carona);
		
	}
	
	public List<Carona> gaetVagasOferecidas() {
		return this.caronasOferecidas;
		
	}


	public void addNoHistoricoDeVagasEmCaronas(String iterable_element) {
		lista.add(iterable_element);
		System.out.println(iterable_element);
		System.out.println(lista);
		
	}


}
