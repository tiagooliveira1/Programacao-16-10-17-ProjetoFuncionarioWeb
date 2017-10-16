package opet.funcionario.main;

import opet.funcionario.model.FuncionarioDAO;
import opet.funcionarios.vo.Diretor;
import opet.funcionarios.vo.Funcionario;

/**
 * Classe somente para facilitar a execução de testes das classes
 * @author troli
 *
 */
public class MaintTeste {
	
	
	
	public static void main(String args[]) {
		Funcionario f = new Diretor("Tiago", 1550.22);
		FuncionarioDAO  fDAO = new FuncionarioDAO();
		
		fDAO.incluir(f);
	}

}
