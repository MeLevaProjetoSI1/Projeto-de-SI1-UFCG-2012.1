package projeto;

import java.util.Calendar;
import java.util.GregorianCalendar;

import projectExeptions.DataInvalidaException;

public class Valida {
	private Calendar calendario;

	public Valida() {
		calendario = new GregorianCalendar();
	}

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
		//se o dias for maior que o total de dias nesse ano passado como paramentro
		if (day > this.calendario.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			throw new DataInvalidaException();
		}

	}

	public boolean estruturaDeData(String data) {
		return data.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}");
	}

	public boolean estruturaDeHora(String hora) {
		return hora.matches("[0-9]{2}:[0-9]{2}");
	}

	public boolean estruturaDeVagas(String vagas) {
		return vagas.matches("^[0-9]*$");
	}

}
