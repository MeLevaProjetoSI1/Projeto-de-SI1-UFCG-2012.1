package projeto;

import projectExeptions.*;

/**
 * UFCG - CEEI - DSC Disciplina: Sistema de Informacao I. Professor: Nazareno.
 * 
 * Projeto SI1 2012.1.
 * 
 * CLASSE PARA REPRESENTAÇÃO DE UM USUÁRIO
 * 
 * @author Grupo do Projeto MeLeva
 * @version 1.0
 * 
 */
public class Usuario {
	private String login, senha, nome, endereco, email;

	private final String NOME = "nome";
	private final String ENDERECO = "endereco";

	/**
	 * Construtor de um Usuario
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

		validador(login, senha, nome, endereco, email);
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
	}

	/**
	 * Construtor de um Usuario
	 * 
	 * @param login
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	public Usuario(String login, String nome, String endereco, String email)
			throws Exception {

		validador2(login, nome, endereco, email);
		this.login = login;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
	}

	/**
	 * Construtor de um Usuario
	 * 
	 * @param login
	 * @param nome
	 * @param endereco
	 * @throws Exception
	 */
	public Usuario(String login, String nome, String endereco) throws Exception {
		validador3(login, nome, endereco);
		this.login = login;
		this.nome = nome;
		this.endereco = endereco;
	}

	/**
	 * Método para validar os atributos de um usuário.
	 * 
	 * @param login
	 * @param nome
	 * @param endereco
	 * @throws Exception
	 */
	private void validador3(String login, String nome, String endereco)
			throws Exception {
		if (login == null || login.equals("")) {
			throw new LoginInvalidoException();
		} else if (nome == null || nome.equals("")) {
			throw new NomeInvalidoException();
		} else if (endereco == null || endereco.equals("")) {
			throw new EnderecoInvalidoException();
		}

	}

	/**
	 * Método para validar os atributos de um usuário.
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	private void validador(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		if (login == null || login.equals("")) {
			throw new LoginInvalidoException();
		} else if (senha == null || senha.equals("")) {
			throw new SenhaInvalidoException();
		} else if (nome == null || nome.equals("")) {
			throw new NomeInvalidoException();
		} else if (endereco == null || endereco.equals("")) {
			throw new EnderecoInvalidoException();
		} else if (email == null || email.equals("")) {
			throw new EmailInvalidoException();
		}
	}

	/**
	 * Método para validar os atributos de um usuário.
	 * 
	 * @param login
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	private void validador2(String login, String nome, String endereco,
			String email) throws Exception {

		if (login == null || login.equals("")) {
			throw new LoginInvalidoException();
		} else if (nome == null || nome.equals("")) {
			throw new NomeInvalidoException();
		} else if (endereco == null || endereco.equals("")) {
			throw new EnderecoInvalidoException();
		} else if (email == null || email.equals("")) {
			throw new EmailInvalidoException();
		}
	}

	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 */
	public void solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 */
	public void sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) {
		// O usuário que cadastrou a carona recebe a solicitação e pode
		// aprová-la ou sugerir um outro ponto de encontro.
		// Ao aprová-la a quantidade de vagas disponíveis na carona é
		// atualizada.

	}

	/**
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 */
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param idSessaoidCarona
	 * @param idSugestao
	 * @param pontos
	 */
	public void responderSugestaoPontoEncontro(String idSessaoidCarona,
			String idSugestao, String pontos) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param idSolicitacao
	 * @param atributo
	 */
	public void getAtributoSolicitacao(String idSolicitacao, String atributo) {
		// TODO Auto-generated method stub

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

}
