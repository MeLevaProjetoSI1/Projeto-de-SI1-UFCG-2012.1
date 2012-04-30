package testesProject;

import java.util.ArrayList;
import java.util.List;

import projeto.Controler;
import easyaccept.EasyAcceptFacade;

/**
 * UFCG - CEEI - DSC Disciplina: Sistema de Informacao I. Professor: Nazareno.
 * 
 * Projeto SI1 2012.1.
 * 
 * Pacote testesProject Classe Main
 * 
 * Classe para execucao dos testes do EasyAcceppt.
 * 
 * @author Grupo do Projeto MeLeva
 * @version 1.0
 * 
 */

public class Teste {

	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();

		files.add("testesProjectEasyAcceptFacade/US01.txt");
		files.add("testesProjectEasyAcceptFacade/US02.txt");
		files.add("testesProjectEasyAcceptFacade/US03.txt");
		files.add("testesProjectEasyAcceptFacade/US04.txt");
		files.add("testesProjectEasyAcceptFacade/US05.txt");
		files.add("testesProjectEasyAcceptFacade/US06.txt");
		files.add("testesProjectEasyAcceptFacade/US07.txt");
		files.add("testesProjectEasyAcceptFacade/US08.txt");
		
		Controler projeto = new Controler();

		// Instantiate EasyAccept façade

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(projeto, files);

		// Execute the tests

		eaFacade.executeTests();

		// Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());

	}

}
