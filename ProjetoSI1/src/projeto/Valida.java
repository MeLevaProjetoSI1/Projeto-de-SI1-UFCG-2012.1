package projeto;

import java.util.Calendar;
import java.util.GregorianCalendar;

import projectExeptions.DataInvalidaException;

/**
 * UFCG - CEEI - DSC Disciplina: Sistema de Informacao I. Professor: Nazareno.
 * 
 * Projeto SI1 2012.1.
 * 
 * Classe para verificacao de dados do sistema
 * 
 * Classe para execucao dos testes do EasyAcceppt.
 * 
 * @author Grupo do Projeto MeLeva
 * @version 1.0
 * 
 */
public class Valida {
	private Calendar calendario;

	/**
	 * Construtor
	 */
	public Valida() {
		calendario = new GregorianCalendar();
	}

	/**
	 * Metodo para verificar se a data é correta
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void dataValida(String[] data) throws Exception {
		Calendar calendarioAtual = new GregorianCalendar();
		Integer day = Integer.parseInt(data[0]);
		Integer month = Integer.parseInt(data[1]);
		Integer year = Integer.parseInt(data[2]);
		this.calendario.set(Calendar.MONTH, month - 1);
		this.calendario.set(Calendar.YEAR, year);

		// se o ano que passei for anterior ao calendario atual
		if (year < calendarioAtual.get(Calendar.YEAR)) {
			throw new DataInvalidaException();
		}
		// se o dias for maior que o total de dias nesse ano passado como
		// paramentro
		if (day > this.calendario.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			throw new DataInvalidaException();
		}

	}

	/**
	 * Metodo para verificar se a estrutura da data recebida eh 00:00
	 * 
	 * @param data
	 * @return
	 */
	public boolean estruturaDeData(String data) {
		return data.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}");
	}

	/**
	 * Metodo para verificar se a estrutura da hora recebida eh 00/00/0000
	 * 
	 * @param hora
	 * @return
	 */
	public boolean estruturaDeHora(String hora) {
		return hora.matches("[0-9]{2}:[0-9]{2}");
	}

	/**
	 * Metodo para verificar se a estrutura da vaga recebida esta correta
	 * 
	 * @param vagas
	 * @return
	 */
	public boolean estruturaDeVagas(String vagas) {
		return vagas.matches("^[0-9]*$");
	}

}
