package projectExeptions;

public class SessaoInexistenteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessaoInexistenteException() {
		super("Sessão inexistente"); 
	}
}
