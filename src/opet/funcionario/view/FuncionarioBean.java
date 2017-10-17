package opet.funcionario.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import opet.funcionarios.vo.Diretor;
import opet.funcionarios.vo.Funcionario;

@Named("funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1979204258589489458L;

	private Funcionario funcionario;
	
	private String tipoFuncionario;
	public FuncionarioBean() {
		// TODO Auto-generated constructor stub
		funcionario = new Diretor("", 0.0);
		tipoFuncionario = "";
		
	}
	
	public String incluir() {
		//TODO:: IMPLEMENTAR A CHAMADA DO CONTROLLER
		//retorna nessa string o arquivo de retorno
		return "/funcionario/resultadoCadastroFuncionario";
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

}
