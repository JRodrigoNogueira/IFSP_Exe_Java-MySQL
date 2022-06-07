package br.edu.ifsp.view.funcionario;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.edu.ifsp.controller.CargoController;
import br.edu.ifsp.controller.FuncionarioController;
import br.edu.ifsp.model.departamento.Departamento;

@SuppressWarnings("serial")
public class CargoCadastro extends JDialog {
	private JLabel lbTitulo, lbDescricao, lbDepartamento;
	private JTextField tfDescricao;
	private JComboBox<Departamento> cbDepartamento;
	private JButton btCadastrar;
	private Container cp; // Container para organizar os componentes na janela.	

	public CargoCadastro() { // Construtor.
		// Instancia��o e configura��o dos componentes de interface.
		setTitle("Cadastro de Cargos"); // T�tulo da janela.
		setSize(500, 335); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que n�o permite acesso a outras janelas abertas).
		
		lbTitulo = new JLabel("Cadastro de Cargos");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		lbDescricao = new JLabel("Descri��o");
		lbDepartamento = new JLabel("Departamento");
		
		tfDescricao = new JTextField();
		
		cbDepartamento = new JComboBox<>();
		
		List<Departamento> departamentos = new ArrayList<Departamento>();
		
		// Retorna um ArrayList de objetos Cargo, contendo o Id e a Descri��o dos cargos cadastrados.
		departamentos = new CargoController().recuperaDepartamentos();
		if (departamentos != null) // Se existir pelo menos um cargo cadastrado.
			for (Departamento c : departamentos)
				// O m�todo addItem adiciona o objeto Cargo (contendo os atributos Id e Descri��o) ao JComboBox. Ao ser carregado,
				// o JComboBox chama automaticamente o m�todo toString dos objetos Cargo para convert�-los para String, pois o
				// dado a ser exibido no JComboBox deve ser deste tipo. Como o m�todo toString foi sobrescrito na classe Cargo, 
				// de modo a retornar a descri��o do cargo, � esta a informa��o que ser� exibida no JComboBox.
				cbDepartamento.addItem(c);
		
		String erro = new CargoController().getExcecao();
		
		if (erro != null) // Caso ocorra qualquer tipo de exce��o.
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel recuperar os dados dos departamentos:\n" + erro, 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		
		btCadastrar = new JButton("Cadastrar");

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbTitulo.setBounds(125, 10, 300, 25); // x, y, largura, altura.
		lbDescricao.setBounds(20, 50, 100, 25);
		tfDescricao.setBounds(100, 50, 360, 25);
		lbDepartamento.setBounds(20, 170, 100, 25);
		cbDepartamento.setBounds(100, 170, 220, 25);
		btCadastrar.setBounds(200, 250, 100, 25);

		// Adi��o dos componentes de interface ao container.
		cp.add(lbTitulo);
		cp.add(lbDescricao);
		cp.add(tfDescricao);
		cp.add(lbDepartamento);
		cp.add(cbDepartamento);
		cp.add(btCadastrar);

		// Declara��o do processador de evento referente ao clique no bot�o Cadastrar.
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				btCadastrarAction();
			}
		});
	} // Final do construtor.

	private void btCadastrarAction() { // M�todo acionado pelo clique no bot�o Cadastrar.

		List<String> erros = new ArrayList<String>();
		
		// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
		// O controller retorna ent�o um ArrayList contendo os erros encontrados.
		erros = new CargoController().insereCargo(tfDescricao.getText(),
										                      (Departamento) cbDepartamento.getSelectedItem());
		
		if (erros.get(0) == null) { // Se o primeiro elemento do ArrayList for null.
			JOptionPane.showMessageDialog(this, "Cargo cadastrado com sucesso.", 
					                      "Informa��o", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false); // Fecha a janela.
		} else { // Se o primeiro elemento do ArrayList n�o for null.
			String mensagem = "N�o foi poss�vel cadastrar o cargo:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erros", JOptionPane.ERROR_MESSAGE);
		}
	}
}






















