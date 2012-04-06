package projeto;

import projectExeptions.AtributoInexistenteException;

/**
 * UFCG - CEEI - DSC
 * Disciplina: Sistema de Informacao I.
 * Professor: Nazareno.
 * 
 * Projeto SI1 2012.1.
 * 
 * CLASSE PARA REPRESENTAÇÃO DE UMA CARONA
 * 
 * @author Grupo do Projeto MeLeva
 * @version 1.0
 * 
 */
public class Carona {
	private String idSessao, origem, destino, hora, data;
	private String vagas;
	private final String ORIGEM = "origem";
	private final String DESTINO = "destino";
	private final String HORA = "hora";
	private final String DATA = "data";
	private final String VAGAS = "vagas";

	/**
	 * Contrutor de Objeto Carona
	 * 
	 * @param contadorDeCaronasID
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 */
	public Carona(int contadorDeCaronasID, String origem, String destino,
			String data, String hora, String vagas) {
		this.idSessao = geraID(contadorDeCaronasID);
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.hora = hora;
		this.vagas = vagas;
	}

	/**
	 * Método para gerar um ID de acordo com as caronas já cadastradas
	 * 
	 * @param contadorDeCaronasID
	 * @return
	 */
	private String geraID(int contadorDeCaronasID) {
		return "carona" + contadorDeCaronasID + "ID";
	}

	/**
	 * Método para retorno do atributo da carona {origem, destino, hora, data,
	 * vaga}
	 * 
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoCarona(String atributo) throws Exception {
		if (atributo.equals(ORIGEM)) {
			return getOrigem();
		} else if (atributo.equals(DESTINO)) {
			return getDestino();
		} else if (atributo.equals(HORA)) {
			return getHora();
		} else if (atributo.equals(DATA)) {
			return getData();
		} else if (atributo.equals(VAGAS)) {
			return getVagas();
		} else {
			throw new AtributoInexistenteException();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getIdSessao() {
		return idSessao;
	}

	/**
	 * 
	 * @param idSessao
	 */
	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	/**
	 * 
	 * @return
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * 
	 * @param origem
	 */
	public void setOrigem(String origem) {
		this.origem = origem;
	}

	/**
	 * 
	 * @return
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * 
	 * @param destino
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}

	/**
	 * 
	 * @return
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * 
	 * @param hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * 
	 * @return
	 */
	public String getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * 
	 * @return
	 */
	public String getVagas() {
		return vagas;
	}

	/**
	 * 
	 * @param vagas
	 */
	public void setVagas(String vagas) {
		this.vagas = vagas;
	}

}
