package projeto;

public class FacadeMeLeva {
	
	Controler controler;
	
	public FacadeMeLeva() {
		controler = new Controler();
	}
	
	public void criarUsuario(String login,String senha,String nome,String endereco,String email){
		controler.criarUsuario(login, senha, nome, endereco, email);
	}
	
	public void criarUsuario(String login,String nome,String endereco,String email){
		controler.criarUsuario(login, nome, endereco, email);
	}
	
	public void criarUsuario(String login,String nome,String endereco){
		controler.criarUsuario(login, nome, endereco);
	}

	public String abrirSessao(String login, String senha){
		return controler.abrirSessao(login, senha);
	}


	public String getAtributoUsuario(String login, String atributo){
		return controler.getAtributoUsuario(login, atributo);
		
	}


	public String localizarCarona(String idSessao, String origem, String destino){
		return controler.localizarCarona(idSessao, origem, destino);
	}

	
	public void cadastrarCarona(String, String, String, String, String, String){
		
	}
	public void zerarSistema(){
		
	}
	public void encerrarSistema(){
		
	}
	public void encerrarSistema(String){
		
	}
	public void encerrarSessao(String){
		
	}
	public void getAtributoCarona(String, String){
		
	}
	public void getAtributoSolicitacao(String, String){
		
	}
	public void getAtributoPerfil(String, String){
		
	}
	public void getTrajeto(String){
		
	}
	public void getCarona(String){
		
	}
	public void aceitarSolicitacaoPontoEncontro(String, String){
		
	}
	public void aceitarSolicitacao(String, String){
		
	}
	public void solicitarVagaPontoEncontro(String, String, String){
		
	}
	public void solicitarVaga(String, String){
		
	}
	public void rejeitarSolicitacao(String, String){
		
	}

	public void sugerirPontoEncontro(String, String, String){
		
	}
	public void responderSugestaoPontoEncontro(String, String, String, String){
		
	}
	public void desistirRequisicao(String, String, String){
		
	}
	public void visualizarPerfil(String, String){
		
	}

}
