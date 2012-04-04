package projeto;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import projectExeptions.AtributoInvalido;
import projectExeptions.EmailExistente;
import projectExeptions.LoginExistente;
import projectExeptions.LoginInvalidoException;
import projectExeptions.UsuarioInexistente;

public class MeLeva {

	private Usuario user;
	private List<Usuario> UsuariosCadastrados;

	public MeLeva() {
		UsuariosCadastrados = new LinkedList<Usuario>();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {

		if (buscaUsuarioPorEmail(email) != null) {
			throw new EmailExistente();
		}
		UsuariosCadastrados
				.add(new Usuario(login, senha, nome, endereco, email));
	}

	public void criarUsuario(String login, String nome, String endereco,
			String email) throws Exception {

		if (buscaUsuarioPorEmail(email) != null) {
			throw new EmailExistente();
		}
		UsuariosCadastrados.add(new Usuario(login, nome, endereco, email));
	}

	public void criarUsuario(String login, String nome, String endereco)
			throws Exception {

		if (buscaUsuarioPorLogin(login) != null) {
			throw new LoginExistente();
		}
		UsuariosCadastrados.add(new Usuario(login, nome, endereco));
	}

	public void zerarSistema() {
		// TODO Auto-generated method stub
	}

	public void encerrarSistema() {
		// TODO Auto-generated method stub

	}

	public void abrirSessao(String login, String senha) throws Exception {
		Usuario usuario;
		if (login == null || senha == null) {
			throw new LoginInvalidoException();
		} else if (login.equals("")) {
			throw new LoginInvalidoException();
		}
		for (Iterator<Usuario> iterator = UsuariosCadastrados.iterator(); iterator
				.hasNext();) {
			usuario = (Usuario) iterator.next();
			if (usuario.getLogin().equals(login)) {
				if (usuario.getSenha().equals(senha)) {
					user = usuario;
					return;
				} else {
					throw new LoginInvalidoException();
				}
			}
		}
		throw new UsuarioInexistente();
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {

		if (login == null || login.equals("")) {
			throw new LoginInvalidoException();
		} else if (atributo == null || atributo.equals("")) {
			throw new AtributoInvalido();
		} else if (buscaUsuarioPorLogin(login) == null) {
			throw new UsuarioInexistente();
		}

		return user.getAtributoUsuario(login, atributo);
	}

	public Usuario buscaUsuarioPorLogin(String login) {
		Usuario result = null;
		for (Usuario element : UsuariosCadastrados) {
			if (element.getLogin().equals(login)) {
				result = element;
				break;
			}
		}
		return result;

	}

	public Usuario buscaUsuarioPorEmail(String email) {
		Usuario result = null;
		for (Usuario element : UsuariosCadastrados) {
			if (element.getEmail().equals(email)) {
				result = element;
				break;
			}
		}
		return result;

	}

}
