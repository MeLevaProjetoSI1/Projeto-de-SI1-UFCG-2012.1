package projectExeptions;

/**
 * UFCG - CEEI - DSC Disciplina: Sistema de Informacao I. Professor: Nazareno.
 * 
 * Projeto SI1 2012.1.
 * 
 * Classe exeção de atributo inexistentes
 * 
 * @author Grupo do Projeto MeLeva
 * @version 1.0
 * 
 */

public class AtributoInexistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor
	 */
	public AtributoInexistenteException() {
		super("Atributo inexistente");
	}

}
