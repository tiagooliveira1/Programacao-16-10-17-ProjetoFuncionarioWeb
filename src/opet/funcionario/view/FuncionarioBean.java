package opet.funcionario.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import opet.funcionario.controller.FuncionarioController;
import opet.funcionarios.vo.Diretor;
import opet.funcionarios.vo.Funcionario;
import opet.funcionarios.vo.Professor;
import opet.funcionarios.vo.Secretario;

@Named("funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1979204258589489458L;

	//private Funcionario funcionario;
	
	private String nomeFuncionario;
	private Double salarioFuncionario;
	private String tipoFuncionario;
	
	private FuncionarioController funcionarioController;
	
	private List<Funcionario> listaFuncionarios;
	
	

	public FuncionarioBean() {
		//funcionario = new Diretor("", 0.0);
		setTipoFuncionario("");
		setTipoFuncionario("");
		setSalarioFuncionario(0d);
		
		listaFuncionarios = null;
		
	}
	
	public String incluir() {
		
		Funcionario funcionario = null;
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		funcionarioController = new FuncionarioController();
		
		if(salarioFuncionario == null || salarioFuncionario == 0) {
			setTipoFuncionario("");
			setNomeFuncionario("");
			setSalarioFuncionario(0d);
			contexto.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Salário não pode ser zero.", null) );
			return "/funcionario/cadastroFuncionario";
		}
		
		if(tipoFuncionario.equals("1")) {
			funcionario = new Diretor(nomeFuncionario, salarioFuncionario);
		} else if(tipoFuncionario.equals("2")) {
			funcionario = new Secretario(nomeFuncionario, salarioFuncionario);
		} else if(tipoFuncionario.equals("3")) {
			funcionario = new Professor(nomeFuncionario, salarioFuncionario);
		} else {
			contexto.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de funcionário inválido.", null) );
			return "/funcionario/cadastroFuncionario";
		}
		if(funcionarioController.salvar(funcionario) ) {
			contexto.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inclusão OK!", null) );
			System.out.println("Funcionário inserido com sucesso.");
		} else {
			contexto.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas na inclusão!", null) );
			System.out.println("Problemas ao inserir funcionario");
		}
		return "/funcionario/resultadoCadastroFuncionario";
	}
	
	public String pesquisar() {
		//Funcionario funcionario = null;
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		funcionarioController = new FuncionarioController();
		
		if(nomeFuncionario == null) {
			setTipoFuncionario("");
			setNomeFuncionario("");
			setSalarioFuncionario(0d);
			contexto.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome não pode ser nulo.", null) );
			return "/funcionario/filtroPesquisaFuncionario";
		}
		
		if(nomeFuncionario.equals("")) {
			listaFuncionarios = funcionarioController.listarAll();
			if(listaFuncionarios == null) {
				setNomeFuncionario("");
				
				contexto.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao retornar lista", null) );
				return "/funcionario/filtroPesquisaFuncionario";
			}
			
		} else {
			
		}
		return "/funcionario/resultadoPesquisaFuncionario";
		
	}

	/*public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}*/

	public String getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Double getSalarioFuncionario() {
		return salarioFuncionario;
	}

	public void setSalarioFuncionario(Double salarioFuncionario) {
		this.salarioFuncionario = salarioFuncionario;
	}
	
	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

}
