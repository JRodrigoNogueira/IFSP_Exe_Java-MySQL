package br.edu.ifsp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dao.CargoDao;
import br.edu.ifsp.dao.FuncionarioDao;
import br.edu.ifsp.model.cargo.Cargo;
import br.edu.ifsp.model.cargo.CargoValidacao;
import br.edu.ifsp.model.departamento.Departamento;
import br.edu.ifsp.model.funcionario.Funcionario;
import br.edu.ifsp.model.funcionario.FuncionarioValidacao;

public class CargoController {
	private Cargo cargo;
	private List<String> erros;

    public List<String> insereCargo(String descricao, Departamento departamento) {
    	recebeDadosCargo(null, descricao, departamento);
    	
		// Se nenhum erro de validação for encontrado, tenta inserir o funcionário no banco.
		if (erros.size() == 0) 
			erros.add(new CargoDao().insereCargo(cargo));
		
		// Retorna o ArrayList contendo:
		// - Em caso de sucesso: null na 1ª posição; OU
		// - Em caso de exceção: uma mensagem de exceção na 1ª posição; OU
		// - Em caso de erro de validação: mensagens de erro iniciando na 1ª posição.
		return erros; 
    }
    
    // Método usado pelas operações de inserção e alteração de funcionário.
    public void recebeDadosCargo(Integer id, String descricao, Departamento departamento) {
    	cargo = new Cargo();
    	erros = new ArrayList<String>();

		// Os métodos set abaixo criam um objeto Funcionario contendo os dados do funcionário informado.
		// Este objeto será enviado à classe DAO, que fará a inserção de seus dados no banco.
    	cargo.setId(id);
    	cargo.setDescricao(descricao);
		cargo.setDepartamento(departamento);

		// Retorna um ArrayList contendo os erros encontrados em regras de validação e de negócios.
		erros = CargoValidacao.validaCargo(cargo);
    }
    
    public List<Departamento> recuperaDepartamentos() {
    	// Recupera os cargos cadastrados no banco de dados para que sejam carregados no JComboBox Cargo.
		return new CargoDao().recuperaDepartamentos();		
    }
    
    public String getExcecao() {
    	// Retorna a exceção lançada ao recuperar os cargos (ao abrir a tela "Cadastro de Funcionário").
    	return new FuncionarioDao().getExcecao();
    }
}

