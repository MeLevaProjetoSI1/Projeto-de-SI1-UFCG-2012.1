package projeto;

import projectExeptions.AtributoInexistenteException;

public class Carona {
	private String idSessao, origem, destino, hora, data;
	private String vagas;
	private final String ORIGEM = "origem";
	private final String DESTINO = "destino";
	private final String HORA = "hora";
	private final String DATA = "data";
	private final String VAGAS = "vagas";

	public Carona(int contadorDeCaronasID, String origem, String destino, String data,
			String hora, String vagas) {
		this.idSessao = geraID(contadorDeCaronasID);
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.hora = hora;
		this.vagas = vagas;
	}
	

	private String geraID(int contadorDeCaronasID) {
		return "carona" + contadorDeCaronasID + "ID";
	}
	
	public String getAtributoCarona(String atributo) throws Exception {
		if (atributo.equals(ORIGEM)) {
			return getOrigem();
		}
		else if (atributo.equals(DESTINO)) {
			return getDestino();
		}
		else if (atributo.equals(HORA)) {
			return getHora();
		}
		else if (atributo.equals(DATA)) {
			return getData();
		}
		else if (atributo.equals(VAGAS)) {
			return getVagas();
		}
		else {
			throw new AtributoInexistenteException();
		}
	}


	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}

}
