package projectExeptions;

public class SenhaInvalidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SenhaInvalidoException() {
		super("Senha inválida");
	} 
}
