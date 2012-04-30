package projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Classe renponsavel pela permanencia dos dados
 */
public class BancoDeDados implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controler controler;

	/*
	 * 
	 */
	public BancoDeDados(Controler controler) {
		this.controler = controler;
	}

	/*
	 * 
	 */
	public boolean gravarDados() {
		return gravarUsuarios() && gravarCaronas() && gravarOutros();
	}

	/*
	 * 
	 */
	public void lerDados() {
		lerUsuarios();
		lerCaronas();
		lerOutros();
	}

	/*
	 * 
	 */
	private boolean gravarUsuarios() {
		try {
			File arquivo = new File("src/ArquivosSalvos/Usuarios.txt");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(arquivo)));
			// Saida Usuario no formato:
			// login|senha|nome|endereco|email|hCaronas|hVagasEmCaronas
			String linha = "";

			for (Usuario usuario : controler.getUsuariosCadastrados()) {
				linha = "";
				linha += usuario.getLogin() + "|";
				linha += usuario.getSenha() + "|";
				linha += usuario.getNome() + "|";
				linha += usuario.getEndereco() + "|";
				linha += usuario.getEmail() + "|";
				linha += Arrays.toString(usuario.gethCaronas().toArray())
						.replace("[", "").replace("]", "").replace(",", ";")
						+ "|";
				linha += Arrays
						.toString(usuario.gethVagasEmCaronas().toArray())
						.replace("[", "").replace("]", "").replace(",", ";")
						+ "|";
				writer.write(linha + "\n");
			}
			writer.flush();// grava
			writer.close();// fecha
		} catch (IOException ioe) {
			System.out.println("erro aki1");
			ioe.printStackTrace();
		}
		return true;
	}

	/*
	 * 
	 */
	private boolean gravarCaronas() {
		try {
			File arquivo = new File("src/ArquivosSalvos/Caronas.txt");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(arquivo)));
			// Saida carona no formato:
			// sessao|origem|destino|data|hora|vagas|LoginDoDonoDaCarona|PontosSugeridosDeEncontro|LoginsDosUsuariosQSolitaramVaga
			String linha;

			for (Carona carona : controler.getCaronasCadastradas()) {
				linha = "";
				linha += carona.getIdSessao() + "|";
				linha += carona.getOrigem() + "|";
				linha += carona.getDestino() + "|";
				linha += carona.getData() + "|";
				linha += carona.getHora() + "|";
				linha += carona.getVagas() + "|";
				linha += carona.getDonoDaCarona().getLogin() + "|";
				if (carona.getPontosSugeridosDeEncontro() == null) {
					linha += "|";
				} else {

					linha += carona.getPontosSugeridosDeEncontro() + "|";
				}

				String loginsUsuariosQSolicitamVaga = "";
				for (String usuario : carona.getSolicitacoesDeUsuarios()) {
					if (carona.getSolicitacoesDeUsuarios().size() == 1) {
						loginsUsuariosQSolicitamVaga += usuario;
					} else {
						loginsUsuariosQSolicitamVaga += usuario + ";";
					}
				}

				if (loginsUsuariosQSolicitamVaga != ""
						&& carona.getSolicitacoesDeUsuarios().size() > 1) {
					loginsUsuariosQSolicitamVaga = loginsUsuariosQSolicitamVaga
							.substring(0, -2);
				}

				linha += loginsUsuariosQSolicitamVaga + "|";
				linha += Arrays
						.toString(carona.getSolicitacoesDePonto().toArray())
						.replace("[", "").replace("]", "").replace(",", ";");

				writer.write(linha + "\n");
			}

			writer.flush();// grava
			writer.close();// fecha

		} catch (Exception e) {
			System.out.println("erro aki2");
			System.out.println(e.getMessage());
		}
		return true;
	}

	// coisas do controler
	/*
	 * 
	 */
	private boolean gravarOutros() {
		return true;
	}

	/*
	 * 
	 */
	private void lerUsuarios() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(
					"src/ArquivosSalvos/Usuarios.txt"));

			List<Usuario> usuarios = new ArrayList<Usuario>();
			// BufferedReader buffer = null;

			// buffer = new BufferedReader(new InputStreamReader(streamEntrada,
			// "UTF-8"));

			String linha;
			String[] argumentos, arrayTemp;
			Usuario user;

			while ((linha = reader.readLine()) != null) {
				argumentos = linha.split("|");
				user = new Usuario(argumentos[0], argumentos[1], argumentos[2],
						argumentos[3], argumentos[4]);

				arrayTemp = argumentos[5].split(";");
				user.sethCaronas(Arrays.asList(arrayTemp));
				arrayTemp = argumentos[6].split(";");
				//user.sethVagasEmCaronas(Arrays.asList(arrayTemp));

				usuarios.add(user);
			}

			controler.setUsuariosCadastrados(usuarios);
			reader.close();

		} catch (Exception e) {
			System.out.println("erro aki3");
			System.out.println(e.getMessage());
		}

	}

	/*
	 * 
	 */
	private void lerCaronas() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"src/ArquivosSalvos/Usuarios.txt"));
			// File arquivo = new File("Caronas.txt");
			// FileInputStream streamEntrada = new FileInputStream(arquivo);
			List<Carona> caronas = new ArrayList<Carona>();
			// BufferedReader buffer = null;

			// buffer = new BufferedReader(new InputStreamReader(streamEntrada,
			// "UTF-8"));

			String linha;
			String[] argumentos;
			while ((linha = reader.readLine()) != null) {
				argumentos = linha.split("|");
				caronas.add(new Carona(
						controler.getSecoes().get(argumentos[0]), controler
								.getContadorDeCaronasID(), argumentos[1],
						argumentos[2], argumentos[3], argumentos[4],
						argumentos[5]));
			}

			controler.setCaronasCadastrados(caronas);
			reader.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * 
	 */
	private void lerOutros() {
		// TODO
	}
}
