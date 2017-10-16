package opet.funcionario.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1979204258589489458L;

	public FuncionarioBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String incluir() {
		//TODO:: IMPLEMENTAR A CHAMADA DO CONTROLLER
		//retorna nessa string o arquivo de retorno
		return "/funcionario/resultadoCadastroFuncionario";
	}

}
