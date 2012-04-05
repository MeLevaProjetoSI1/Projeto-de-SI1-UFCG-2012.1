package projeto;

import java.util.Calendar;
import java.util.GregorianCalendar;

import projectExeptions.DataInvalidaException;

public class Valida {
	private Calendar calendario;

	public Valida() {
		calendario = new GregorianCalendar();
	}

	public void dataValida(String day, String month, String year)
			throws Exception {
		Calendar calendarioAtual = new GregorianCalendar();

		this.calendario.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		this.calendario.set(Calendar.YEAR, Integer.parseInt(year));

		if (Integer.parseInt(year) < calendarioAtual.get(Calendar.YEAR)) {
			throw new DataInvalidaException();
		}
		if (Integer.parseInt(year) < this.calendario.get(Calendar.YEAR)) {
			throw new DataInvalidaException();
		} else if (Integer.parseInt(day) > this.calendario
				.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			throw new DataInvalidaException();
		}

	}
}
