package projectExeptions;

public class SolicitacaoInexistenteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SolicitacaoInexistenteException() {
		super("Solicitação inexistente"); 
	}
}
