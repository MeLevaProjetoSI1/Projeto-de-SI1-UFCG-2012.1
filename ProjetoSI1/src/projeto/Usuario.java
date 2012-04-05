package projeto;

import projectExeptions.*;

public class Usuario {
	private String login, senha, nome, endereco, email;

	private final String NOME = "nome";
	private final String ENDERECO = "endereco";

	public Usuario(String login, String senha, String nome, String endereco,
			String email) throws Exception {

		validador(login, senha, nome, endereco, email);
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
	}

	public Usuario(String login, String nome, String endereco, String email)
			throws Exception {

		validador2(login, nome, endereco, email);
		this.login = login;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
	}

	public Usuario(String login, String nome, String endereco) throws Exception {
		validador3(login, nome, endereco);
		this.login = login;
		this.nome = nome;
		this.endereco = endereco;
	}

	private void validador3(String login, String nome, String endereco)
			throws Exception {
		if (login == null || login.equals("")) {
			throw new LoginInvalidoException();
		}  else if (nome == null || nome.equals("")) {
			throw new NomeInvalidoException();
		} else if (endereco == null || endereco.equals("")) {
			throw new EnderecoInvalidoException();
		} 
		
	}

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

	public String getAtributoUsuario(String atributo)
			throws Exception {
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
