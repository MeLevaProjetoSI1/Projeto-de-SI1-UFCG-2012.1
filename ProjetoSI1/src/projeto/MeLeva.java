package projeto;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import projectExeptions.*;

public class MeLeva {
	private int contadorDeCaronasID = 1;
	private Usuario user;
	private List<Usuario> usuariosCadastrados;
	private List<Carona> caronasCadastradas;
	private List<String> sessoesExistentes;
	private Valida validar;

	public MeLeva() {
		usuariosCadastrados = new LinkedList<Usuario>();
		caronasCadastradas = new LinkedList<Carona>();
		sessoesExistentes = new LinkedList<String>();
		validar = new Valida();

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

	public String localizarCarona(String idSessao, String origem, String destino)
			throws Exception {
		if (origemNaoEhValida(origem)) {
			throw new OrigemInvalidaException();
		} else if (destinoNaoEhValido(destino)) {
			throw new DestinoInvalidoException();
		}
		String result = null;
		List<String> idsValidos = new LinkedList<String>();
		if (!caronasCadastradas.isEmpty()) {
			if (origem.equals("") || destino.equals("")) {
				for (Carona carona : caronasCadastradas) { // Todas as caronas
															// possiveis para
															// essa origem ou
															// destino
					if (carona.getOrigem().equals(origem)
							|| carona.getDestino().equals(destino)) {
						idsValidos.add(carona.getIdSessao());
					}
				}
			}

			else {
				for (Carona carona : caronasCadastradas) { // Apenas as que
															// possuem
															// exatamente essa
															// origem e esse
															// destino
					if (carona.getOrigem().equals(origem)
							&& carona.getDestino().equals(destino)) {
						idsValidos.add(carona.getIdSessao());
					}
				}
			}
		}
		result = Arrays.toString(idsValidos.toArray());
		return result.replace("[", "{").replace("]", "}");
	}

	private boolean origemNaoEhValida(String origem) {
		return (origem.contains("-") || origem.contains("()")
				|| origem.contains("!") || origem.contains("!?"));

	}

	private boolean destinoNaoEhValido(String destino) {
		return (destino.contains(".") || destino.contains("()") || destino
				.contains("!?"));

	}

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

	public void zerarSistema() {
		usuariosCadastrados.clear();
		caronasCadastradas.clear();
		sessoesExistentes.clear();
	}

	public void encerrarSistema() {

	}

	public void encerrarSistema(String login) {

	}

	public void encerrarSessao(String login) {
		for (String sessaoLogada : sessoesExistentes) {
			if (sessaoLogada.contains(login)) {
				sessoesExistentes.remove(sessaoLogada);
			}
		}

	}

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

	private Carona buscaCaronaPorID(String id) {
		Carona result = null;
		for (Carona carona : caronasCadastradas) {
			if (carona.getIdSessao().equals(id)) {
				result = carona;
			}
		}
		return result;

	}

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
