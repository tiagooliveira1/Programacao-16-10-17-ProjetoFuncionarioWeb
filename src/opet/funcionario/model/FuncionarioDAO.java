package opet.funcionario.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import opet.funcionarios.vo.Funcionario;
import opet.funcionarios.vo.Professor;
import opet.funcionarios.vo.Secretario;

public class FuncionarioDAO implements IFuncionarioDAO {

	public static final String DB_USUARIO = "system";
	public static final String DB_PASS = "oracle";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	@Override
	public boolean incluir(Funcionario funcionario)  {
		
		Connection connection = null;
		Funcionario func = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.print("Conectando ao banco...");
			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
			System.out.println("Conectado!");

			PreparedStatement pstmt = connection
					.prepareStatement("INSERT INTO FUNCIONARIO (IDFUNCIONARIO, NOME, SALARIO, TIPO) values ("
							+ "coalesce((select max(idfuncionario)+1 from funcionario),1), " + "?,?,?)");
			pstmt.setString(1, func.getNome());
			pstmt.setDouble(2, func.getSalario());
			if (func instanceof Professor)
				pstmt.setInt(3, 3);
			else if (func instanceof Secretario)
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

	@Override
	public List<Funcionario> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
