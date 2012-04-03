package projectExeptions;

public class EnderecoInvalidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnderecoInvalidoException() {
		super("Endereço inválido");
	}

}
