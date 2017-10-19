package opet.funcionario.controller;

import java.util.List;

import opet.funcionario.model.FuncionarioDAO;
import opet.funcionarios.vo.Funcionario;

public class FuncionarioController {
	
	private Funcionario funcionario;
	public FuncionarioController() {
		
	}
	
	public boolean salvar(Funcionario funcionario) {
		
		Boolean retorno = null; 
		try {
			FuncionarioDAO funcDAO = new FuncionarioDAO();
			retorno = funcDAO.incluir(funcionario);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return retorno;
		
	}
	
	public List<Funcionario> listarAll() {
		return null;
		
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	

}
