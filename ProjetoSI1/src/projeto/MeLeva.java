package projeto;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import projectExeptions.AtributoInvalido;
import projectExeptions.EmailExistente;
import projectExeptions.LoginExistente;
import projectExeptions.LoginInvalidoException;
import projectExeptions.UsuarioInexistente;

public class MeLeva {
	private int contadorDeCaronasID = 1;
	private Usuario user;
	private List<Usuario> usuariosCadastrados;
	private List<Carona> caronasCadastrados;

	public MeLeva() {
		usuariosCadastrados = new LinkedList<Usuario>();
		caronasCadastrados = new LinkedList<Carona>();

	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {

		if (buscaUsuarioPorEmail(email) != null) {
			throw new EmailExistente();
		}
		usuariosCadastrados
				.add(new Usuario(login, senha, nome, endereco, email));
	}

	public void criarUsuario(String login, String nome, String endereco,
			String email) throws Exception {

		if (buscaUsuarioPorEmail(email) != null) {
			throw new EmailExistente();
		}
		usuariosCadastrados.add(new Usuario(login, nome, endereco, email));
	}

	public void criarUsuario(String login, String nome, String endereco)
			throws Exception {

		if (buscaUsuarioPorLogin(login) != null) {
			throw new LoginExistente();
		}
		usuariosCadastrados.add(new Usuario(login, nome, endereco));
	}

	public String localizarCarona(String idSessao, String origem, String destino) {
		String result = null;
		if (caronasCadastrados.isEmpty()) {
			result = "{}";
		} else {
			for (Carona carona : caronasCadastrados) {
				
			}
			result = "";
		}
		return result;
	}

	private String geraID() {
		return "${carona" + contadorDeCaronasID++ + "ID}";
	}

	public void criarCarona(String origem, String destino, String data,
			String hora, int vagas) {
		
		caronasCadastrados.add(new Carona(geraID(), origem, destino, data,
				hora, vagas));
		
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
		for (Iterator<Usuario> iterator = usuariosCadastrados.iterator(); iterator
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
		for (Usuario element : usuariosCadastrados) {
			if (element.getLogin().equals(login)) {
				result = element;
				break;
			}
		}
		return result;

	}

	public Usuario buscaUsuarioPorEmail(String email) {
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
