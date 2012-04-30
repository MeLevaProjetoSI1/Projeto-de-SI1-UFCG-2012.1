package projectExeptions;

public class UsuarioNaoPossuiVagaNaCaronaExeption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoPossuiVagaNaCaronaExeption() {
		super("Usuário não possui vaga na carona.");
	}

}
