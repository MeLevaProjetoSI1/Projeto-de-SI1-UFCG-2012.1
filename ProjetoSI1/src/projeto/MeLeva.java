package projeto;


public class MeLeva {

	private Usuario user;

	public MeLeva() {

	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		user = new Usuario(login, senha, nome, endereco, email);

	}

	public void criarUsuario(String login, String nome, String endereco,
			String email) throws Exception {
		user = new Usuario(login, nome, endereco, email);

	}

	public void zerarSistema() {
		// TODO Auto-generated method stub
	}

	public void encerrarSistema() {
		// TODO Auto-generated method stub

	}

	public void abrirSessao(String login, String senha) {
		// TODO Auto-generated method stub

	}

	public Object getAtributoUsuario(String login, String atributo) {
		return user.getAtributoUsuario(login, atributo);

	}

}
