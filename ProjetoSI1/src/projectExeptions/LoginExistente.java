package projectExeptions;

public class LoginExistente extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginExistente() {
		super("J� existe um usu�rio com este login"); 
	}

}
