package projectExeptions;

public class TrajetoInexistenteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrajetoInexistenteException() {
		super("Trajeto Inexistente"); 
	}
}
