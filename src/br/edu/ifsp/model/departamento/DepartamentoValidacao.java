package br.edu.ifsp.model.departamento;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoValidacao {
	private static List<String> errosValidacao; // List para armazenar as mensagens de erro de validação.
	
	// Valida os dados informados conforme as regras abaixo.
	public static List<String> validaDepartamento(Departamento departamento){
		errosValidacao = new ArrayList<>();
		
		// Validação do campo NomeDepto.
		if (!departamento.getNomeDepto().equals("")) {
			if (departamento.getNomeDepto().length() < 5 || departamento.getNomeDepto().length() > 60)
				errosValidacao.add("* O Nome do Departamento deve ter entre 5 e 60 caracteres.");
		} else {
			errosValidacao.add("* O Nome do Departamento não foi informado.");
		}
		
		
		// Validação do campo Funcionario (para o caso de não existirem funcionários cadastrados).
		if (departamento.getGerente() == null)
			errosValidacao.add("* O Funcionário não foi informado.");
		
		return errosValidacao; // Retorna o ArrayList contendo as mensagens de erro de validação.
	}
}