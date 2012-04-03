package projectExeptions;

public class AtributoInexistenteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AtributoInexistenteException() {
		super("Atributo inválido");
	}

}
