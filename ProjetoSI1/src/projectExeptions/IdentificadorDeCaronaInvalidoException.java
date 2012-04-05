package projectExeptions;

public class IdentificadorDeCaronaInvalidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdentificadorDeCaronaInvalidoException() {
		super("Identificador do carona é inválido"); 
	}
}
