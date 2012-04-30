package projectExeptions;

public class OpcaoInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor
	 */
	public OpcaoInvalidaException() {
		super("Opção inválida.");
	}
}
