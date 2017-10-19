package opet.funcionario.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import opet.funcionarios.vo.Diretor;
import opet.funcionarios.vo.Funcionario;
import opet.funcionarios.vo.Professor;
import opet.funcionarios.vo.Secretario;

public class FuncionarioDAO implements IFuncionarioDAO {

	public static final String DB_USUARIO = "system";
	public static final String DB_PASS = "oracle";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";

	@Override
	public boolean incluir(Funcionario funcionario) {

		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.print("Conectando ao banco...");
			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
			System.out.println("Conectado!");

			PreparedStatement pstmt = connection
					.prepareStatement("INSERT INTO FUNCIONARIO (IDFUNCIONARIO, NOME, SALARIO, TIPO) values ("
							+ "coalesce((select max(idfuncionario)+1 from funcionario),1), " + "?,?,?)");
			pstmt.setString(1, funcionario.getNome());
			pstmt.setDouble(2, funcionario.getSalario());
			if (funcionario instanceof Professor)
				pstmt.setInt(3, 3);
			else if (funcionario instanceof Secretario)
				pstmt.setInt(3, 2);
			else
				pstmt.setInt(3, 1);
			// pstmt.executeUpdate();
			pstmt.execute(); // precisou colocar execute pq uso select para
								// achar novo id

			if (pstmt != null)
				pstmt.close();

			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;

	}

	
	public List<Funcionario> listAll() {
		// TODO Auto-generated method stub
		Connection connection = null;

		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.print("Conectando ao banco...");
			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
			System.out.println("Conectado!");

			PreparedStatement pstmt = connection.prepareStatement("select nome, salario, tipo from funcionario");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Funcionario f;

				int tipo = rs.getInt("tipo");
				String nome = rs.getString("nome");
				Double salario = rs.getDouble("salario");

				if (tipo == 1)
					f = new Diretor(nome, salario);
				else if (tipo == 2) {
					f = new Secretario(nome, salario);
				} else {
					f = new Professor(nome, salario);
				}

				listaFuncionario.add(f);

			}
			return listaFuncionario;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException Except) {
				Except.printStackTrace();
			}
		}
	}

}
