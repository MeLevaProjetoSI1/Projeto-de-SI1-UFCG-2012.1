package projectExeptions;

public class UsuarioInexistente extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioInexistente() {
		super("Usuário inexistente");
	}

}
