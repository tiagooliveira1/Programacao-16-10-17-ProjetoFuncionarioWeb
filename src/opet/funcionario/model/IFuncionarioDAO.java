package opet.funcionario.model;

import java.sql.SQLException;
import java.util.List;

import opet.funcionarios.vo.Funcionario;

public interface IFuncionarioDAO {

	public boolean incluir(Funcionario funcionario);
	
	public List<Funcionario> listAll();
}
