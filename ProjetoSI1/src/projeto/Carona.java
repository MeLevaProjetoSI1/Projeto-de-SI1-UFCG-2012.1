package projeto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import projectExeptions.AtributoInexistenteException;
import projectExeptions.PontoInvalidoException;
import projectExeptions.SolicitacaoInexistenteException;

/**
 * UFCG - CEEI - DSC Disciplina: Sistema de Informacao I. Professor: Nazareno.
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
	private String pontosSugeriodosPeloUsuario;
	private List<Usuario> usuariosQueSolicitamVaga;
	private List<String> solicitacoesDePonto = new LinkedList<String>();
	

	//private int contadorSolicitacao = 1;
	private Map<String, Boolean> solicitacoesRecebidasDeUsuarios;
	
	private final String ORIGEM = "origem";
	private final String DESTINO = "destino";
	private final String HORA = "hora";
	private final String DATA = "data";
	private final String VAGAS = "vagas";
	private final String PONTO_DE_ENCONTRO = "Ponto de Encontro";
	private final String DONO_DA_SOLICITACAO = "Dono da solicitacao";
	private final String DONO_DA_CARONA = "Dono da carona";
	private final Usuario donoDaCarona;
	private Map<String, Usuario> sugestoesDoUsuario = new HashMap<String, Usuario>();
	

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
	public Carona(Usuario dono, int contadorDeCaronasID, String origem,
			String destino, String data, String hora, String vagas) {
		this.idSessao = geraID(contadorDeCaronasID);
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.hora = hora;
		this.vagas = vagas;
		this.solicitacoesRecebidasDeUsuarios = new HashMap<String, Boolean>();
		this.setUsuariosQueSolicitamVaga(new LinkedList<Usuario>());
		this.donoDaCarona = dono;
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
		} else if (atributo.equals(PONTO_DE_ENCONTRO)) {
			// if pontosSugeriodosPeloUsuario.contains())
			return getPontosSugeridosDeEncontro();
		} else {
			throw new AtributoInexistenteException();
		}
	}

	/**
	 * 
	 * @param idSessaoidCarona
	 * @param idSugestao
	 * @param pontosSugeridos
	 */
	// o carona responde ao usuario a solicitacao da vaga
	// o usuario que cadastrou a carona, recebe a solicitacao e pode aprovala ou
	// nao
	public void responderSugestaoPontoEncontro(Usuario usuarioResponde,
			Carona caronaQueRecebeuSolicitacao, String idSugestao,
			String[] pontosSugeridos) {
		usuarioResponde.setSugestaoDaCarona(pontosSugeridos);

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

	/**
	 * 
	 * @param usuario 
	 * @param pontosSugeridos
	 * @param sugestaoID 
	 */
	public void setPontosSugeridosDeEncontro(Usuario usuario, String pontosSugeridos, String sugestaoID) {
		this.pontosSugeriodosPeloUsuario = pontosSugeridos;
		this.sugestoesDoUsuario.put(sugestaoID, usuario); 
	}

	/**
	 * 
	 * @return
	 */
	public String getPontosSugeridosDeEncontro() {
		return this.pontosSugeriodosPeloUsuario;
	}

	/*
	 * 
	 */
	public Map<String, Usuario> getSugestoesDoUsuario() {
		return this.sugestoesDoUsuario;
	}
	
	/*
	 * 
	 */
	public String solicitarVagaPontoEncontro(Usuario usuario, String ponto, Map<String, Boolean> solicitacoesDoUsuarios)
			throws Exception {
		// gera id solicita
		String solicitacao = "solicitacao" + (this.solicitacoesRecebidasDeUsuarios.size() + 1) + "ID";
		this.getSolicitacoesDePonto().add(ponto);
		if (this.getUsuariosQueSolicitamVaga().contains(usuario)) {
			throw new PontoInvalidoException();
		}
		solicitacoesDoUsuarios.put(solicitacao, false);
		this.solicitacoesRecebidasDeUsuarios.put(solicitacao, false);
		this.getUsuariosQueSolicitamVaga().add(usuario);
		//contadorSolicitacao++;

		return solicitacao;
	}

	/*
	 * 
	 */
	public String getAtributoSolicitacao(String atributo,
			Usuario usuarioQueSolicitou) throws Exception {
		String respAtributo = null;
		if (atributo.equals(ORIGEM)) {
			respAtributo = getOrigem();
		} else if (atributo.equals(DESTINO)) {
			respAtributo = getDestino();
		} else if (atributo.equals(HORA)) {
			respAtributo = getHora();
		} else if (atributo.equals(DATA)) {
			respAtributo = getData();
		} else if (atributo.equals(VAGAS)) {
			respAtributo = getVagas();
		} else if (atributo.equals(DONO_DA_SOLICITACAO)) {
			respAtributo = usuarioQueSolicitou.getNome();
		} else if (atributo.equals(DONO_DA_CARONA)) {
			// ainda a analizar
			respAtributo = this.getDonoDaCarona().getNome();
		} else if (atributo.equals(PONTO_DE_ENCONTRO)) {
			if (pontosSugeriodosPeloUsuario.contains(usuarioQueSolicitou.getPontoEncontroSolicitado())) {
				respAtributo = usuarioQueSolicitou.getPontoEncontroSolicitado();
			}
			
		} else {
			throw new AtributoInexistenteException();
		}
		return respAtributo;
	}

	/*
	 * 
	 */
	public Usuario getDonoDaCarona() {
		// TODO Auto-generated method stub
		return this.donoDaCarona;
	}

	/*
	 * 
	 */
	public Set<String> getSolicitacoesDeUsuarios() {
		return this.solicitacoesRecebidasDeUsuarios.keySet();
	}

	/*
	 * 
	 */
	public void aceitarSolicitacaoPontoEncontro(Usuario user,
			String idSolicitacao) throws Exception {
		// se aceitar tem que tirar a solicaitacao
		
		//quer dizer se ja foi aceito
		if (this.solicitacoesRecebidasDeUsuarios.get(idSolicitacao) == true) {
			throw new SolicitacaoInexistenteException();
		}
		//contadorSolicitacao--;
		int vagasDimin = Integer.parseInt(vagas) - 1;
		setVagas(String.valueOf(vagasDimin));
		
		//removo e coloco mais uma vez so que aceito agora
		this.solicitacoesRecebidasDeUsuarios.remove(idSolicitacao);
		this.solicitacoesRecebidasDeUsuarios.put(idSolicitacao, true);
		

	}
	
	public List<String> getSolicitacoesConfirmadas() {
		List<String> lista = new LinkedList<String>();
		for (String solicitacao : this.solicitacoesRecebidasDeUsuarios.keySet()) {
			//se a solicitacao for confirmada ou seja true
			if (this.solicitacoesRecebidasDeUsuarios.get(solicitacao) == true) {
				lista.add(solicitacao);
			}
		}
		return lista;
	}
	
	public List<String> getSolicitacoesPendentes() {
		List<String> lista = new LinkedList<String>();
		for (String solicitacao : this.solicitacoesRecebidasDeUsuarios.keySet()) {
			//se a solicitacao for confirmada ou seja true
			if (this.solicitacoesRecebidasDeUsuarios.get(solicitacao) == false) {
				lista.add(solicitacao);
			}
		}
		return lista;
	}

	/*
	 * 
	 */
	public String solicitarVaga(Usuario usuario, Map<String, Boolean> solicitacoesDoUsuario) throws Exception {
		
		if (getUsuariosQueSolicitamVaga().contains(usuario)) {
			throw new PontoInvalidoException();
		}
		String solicitacao = "solicitacao" + (solicitacoesDoUsuario.size() + 1) + "ID";
		//no usuario
		solicitacoesDoUsuario.put(solicitacao, false);
		this.getUsuariosQueSolicitamVaga().add(usuario);
		//solicitacao, aceita
		//no carona
		this.solicitacoesRecebidasDeUsuarios.put(solicitacao, false);
		//contadorSolicitacao++;
		return solicitacao;
	}

	/*
	 * 
	 */
	public void aceitarSolicitacao(Usuario user, String idSolicitacao)
			throws Exception {
		//if (!usuariosQueSolicitamVaga.contains(user)) {
		//	throw new UsuarioInexistente();
		//}
		// se aceitar tem que tirar a solicaitacao
		if (this.solicitacoesRecebidasDeUsuarios.get(idSolicitacao) == true) {
			throw new SolicitacaoInexistenteException();
		}
		
		//contadorSolicitacao--;
		int vagasDiminui = Integer.parseInt(vagas) - 1;
		setVagas(String.valueOf(vagasDiminui));
		
		this.solicitacoesRecebidasDeUsuarios.remove(idSolicitacao);
		this.solicitacoesRecebidasDeUsuarios.put(idSolicitacao, true);
		
		//user.addNoHistoricoDeVagasEmCaronas(this);
	}

	/*
	 * 
	 */
	public void rejeitarSolicitacao(Usuario user, String idSolicitacao)
			throws Exception {
		// se aceitar tem que tirar a solicaitacao
		//contadorSolicitacao--;
		this.solicitacoesRecebidasDeUsuarios.remove(idSolicitacao);

	}

	/*
	 * 
	 */
	public List<Usuario> getUsuariosQueSolicitamVaga() {
		return usuariosQueSolicitamVaga;
	}

	/*
	 * 
	 */
	public void setUsuariosQueSolicitamVaga(List<Usuario> usuariosQueSolicitamVaga) {
		this.usuariosQueSolicitamVaga = usuariosQueSolicitamVaga;
	}

	/*
	 * 
	 */
	public List<String> getSolicitacoesDePonto() {
		return solicitacoesDePonto;
	}

	/*
	 * 
	 */
	public void setSolicitacoesDePonto(List<String> solicitacoesDePonto) {
		this.solicitacoesDePonto = solicitacoesDePonto;
	}

	public String getPontosEncontro() {
		//por enquanto
		return getPontosSugeridosDeEncontro();
	}

}
