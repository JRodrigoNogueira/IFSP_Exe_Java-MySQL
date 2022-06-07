package br.edu.ifsp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dao.DepartamentoDao;
import br.edu.ifsp.dao.FuncionarioDao;
import br.edu.ifsp.model.cargo.Cargo;
import br.edu.ifsp.model.departamento.Departamento;
import br.edu.ifsp.model.departamento.DepartamentoValidacao;
import br.edu.ifsp.model.funcionario.Funcionario;
import br.edu.ifsp.model.funcionario.FuncionarioValidacao;

public class DepartamentoController {
	private Departamento departamento;
	private List<String> erros;

    public List<String> insereDepartamento(String nomeDepto, Funcionario funcionario) {
    	recebeDadosDepartamento(null, nomeDepto, funcionario);
    	
		// Se nenhum erro de validação for encontrado, tenta inserir o funcionário no banco.
		if (erros.size() == 0) 
			erros.add(new DepartamentoDao().insereDepartamento(departamento));
		
		// Retorna o ArrayList contendo:
		// - Em caso de sucesso: null na 1ª posição; OU
		// - Em caso de exceção: uma mensagem de exceção na 1ª posição; OU
		// - Em caso de erro de validação: mensagens de erro iniciando na 1ª posição.
		return erros; 
    }
    
    // Método usado pelas operações de inserção e alteração de funcionário.
    public void recebeDadosDepartamento(Integer id, String nomeDepto, Funcionario funcionario) {
    	departamento = new Departamento();
    	erros = new ArrayList<String>();

		// Os métodos set abaixo criam um objeto Funcionario contendo os dados do funcionário informado.
		// Este objeto será enviado à classe DAO, que fará a inserção de seus dados no banco.
    	departamento.setId(id);
    	departamento.setNomeDepto(nomeDepto);
		departamento.setGerente(funcionario);
		
		// Retorna um ArrayList contendo os erros encontrados em regras de validação e de negócios.
		erros = DepartamentoValidacao.validaDepartamento(departamento);
    }
    
    public List<Funcionario> recuperaFuncionarios() {
    	// Recupera os cargos cadastrados no banco de dados para que sejam carregados no JComboBox Cargo.
		return new DepartamentoDao().recuperaFuncionarios();		
    }
    
    public String getExcecao() {
    	// Retorna a exceção lançada ao recuperar os cargos (ao abrir a tela "Cadastro de Funcionário").
    	return new FuncionarioDao().getExcecao();
    }
}

