package projectExeptions;

public class ItemInexistenteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemInexistenteException() {
		super("Item inexistente"); 
	}
}
