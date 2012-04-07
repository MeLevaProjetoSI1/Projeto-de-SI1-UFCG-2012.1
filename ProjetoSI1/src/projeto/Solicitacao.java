package projeto;

import projectExeptions.AtributoInexistenteException;

/**
 * UFCG - CEEI - DSC Disciplina: Sistema de Informacao I. Professor: Nazareno.
 * 
 * Projeto SI1 2012.1.
 * 
 * CLASSE PARA REPRESENTAÇÃO DE UMA SOLICITACAO
 * 
 * @author Grupo do Projeto MeLeva
 * @version 1.0
 * 
 */
public class Solicitacao {
	private String pontoEncontro;
	private Carona carona;
	private Usuario donoSolicitacao;
	private String idSolicitacao;
	private final String ORIGEM = "origem";
	private final String DESTINO = "destino";
	private final String DONO_DA_CARONA = "Dono da carona";
	private final String DONO_DA_SOLICITACAO = "Dono da solicitacao";
	private final String PONTO_DE_ENCONTRO = "Ponto de Encontro";

	/**
	 * 
	 * @param contadorDeSolicitacoes
	 * @param pontoEncontro
	 * @param carona
	 * @param donoSolicitacao
	 */
	public Solicitacao(int contadorDeSolicitacoes, String pontoEncontro,
			Carona carona, Usuario donoSolicitacao) {
		this.idSolicitacao = geraID(contadorDeSolicitacoes);
		this.pontoEncontro = pontoEncontro;
		this.carona = carona;
		this.donoSolicitacao = donoSolicitacao;
	}

	/**
	 * 
	 * @param contadorDeSolicitacoes
	 * @param carona
	 * @param donoSolicitacao
	 */
	public Solicitacao(int contadorDeSolicitacoes, Carona carona,
			Usuario donoSolicitacao) {
		this.idSolicitacao = geraID(contadorDeSolicitacoes);
		this.carona = carona;
		this.donoSolicitacao = donoSolicitacao;
	}

	/**
	 * 
	 * @param contadorDeSolicitacoes
	 * @return
	 */
	private String geraID(int contadorDeSolicitacoes) {
		return "solicitacao" + contadorDeSolicitacoes + "ID";
	}

	/**
	 * 
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoSolicitacao(String atributo) throws Exception {
		if (atributo.equals(ORIGEM)) {
			return carona.getOrigem();
		} else if (atributo.equals(DESTINO)) {
			return carona.getDestino();
		} else if (atributo.equals(DONO_DA_CARONA)) {
			return carona.getAtributoCarona(DONO_DA_CARONA);
		} else if (atributo.equals(DONO_DA_SOLICITACAO)) {
			return donoSolicitacao.getNome();
		} else if (atributo.equals(PONTO_DE_ENCONTRO)) {
			return pontoEncontro;
		} else {
			throw new AtributoInexistenteException();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getPontoEncontro() {
		return pontoEncontro;
	}

	/**
	 * 
	 * @param pontoEncontro
	 */
	public void setPontoEncontro(String pontoEncontro) {
		this.pontoEncontro = pontoEncontro;
	}

	public String getIdSolicitacao() {
		return idSolicitacao;
	}

	/**
	 * 
	 * @param idSolicitacao
	 */
	public void setIdSolicitacao(String idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}

	/**
	 * 
	 * @return
	 */
	public Carona getCarona() {
		return carona;
	}

	/**
	 * 
	 * @param carona
	 */
	public void setCarona(Carona carona) {
		this.carona = carona;
	}

}
