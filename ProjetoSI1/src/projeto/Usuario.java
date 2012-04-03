package projeto;

import projectExeptions.AtributoInexistenteException;
import projectExeptions.EmailInvalidoException;
import projectExeptions.EnderecoInvalidoException;
import projectExeptions.LoginInvalidoException;
import projectExeptions.NomeInvalidoException;
import projectExeptions.SenhaInvalidoException;

public class Usuario {
	private String login, senha, nome, endereco, email;

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

	private void validador2(String login, String nome, String endereco,
			String email) throws Exception {
		if (login.equals("")) {
			throw new LoginInvalidoException();
		} else if (nome.equals("")) {
			throw new NomeInvalidoException();
		} else if (endereco.equals("")) {
			throw new EnderecoInvalidoException();
		} else if (email.equals("")) {
			throw new EmailInvalidoException();
		} else if (login == null || nome == null || endereco == null
				|| email == null) {
			throw new AtributoInexistenteException();
		}

	}

	private void validador(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		if (login.equals("")) {
			throw new LoginInvalidoException();
		} else if (senha.equals("")) {
			throw new SenhaInvalidoException();
		} else if (nome.equals("")) {
			throw new NomeInvalidoException();
		} else if (endereco.equals("")) {
			throw new EnderecoInvalidoException();
		} else if (email.equals("")) {
			throw new EmailInvalidoException();
		} else if (login == null || senha == null || nome == null
				|| endereco == null || email == null) {
			throw new AtributoInexistenteException();
		}

	}

	public Object getAtributoUsuario(String login, String atributo) {
		// TODO Auto-generated method stub
		return null;
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
