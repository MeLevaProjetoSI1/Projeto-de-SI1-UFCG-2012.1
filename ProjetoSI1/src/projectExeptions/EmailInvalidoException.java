package projectExeptions;

public class EmailInvalidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailInvalidoException() {
		super("Email inválido");
	}
}
