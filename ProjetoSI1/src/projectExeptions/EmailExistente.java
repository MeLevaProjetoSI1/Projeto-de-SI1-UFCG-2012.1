package projectExeptions;

public class EmailExistente extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailExistente() {
		super("J� existe um usu�rio com este email");
	}

}
