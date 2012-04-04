package projectExeptions;

public class EmailExistente extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailExistente() {
		super("Já existe um usuário com este email");
	}

}
