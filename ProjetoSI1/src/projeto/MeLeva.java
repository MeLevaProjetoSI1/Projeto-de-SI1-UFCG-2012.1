package projeto;

public class MeLeva {

	private Usuario user;

	public MeLeva() {

	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) {
		user = new Usuario(login, senha, nome, endereco, email);
	}

	public void zerarSistema() {
		// TODO Auto-generated method stub
	}
	

	public Object getAtributoUsuario(String login, String atributo) {
		return user.getAtributoUsuario(login, atributo);

	}

}
