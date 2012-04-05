package projectExeptions;

public class LoginExistente extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginExistente() {
		super("Já existe um usuário com este login"); 
	}

}
