package br.misael.clinicamedica.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.misael.clinicamedica.model.Utils;

/**
 * Classe responsável pela conexão com a base de dados.
 * 
 * @author Misael C. Homem
 *
 */
public class Dao {
	
	// Alterarções para MySql 8.0 (driver jdbc.jar e a classe mudaram)
	
	// De MySql 5.6
	//private static final String URL_MYSQL          = "jdbc:mysql://localhost/clinica_medica";
	//private static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
	
	// Para MySql 8.0
	private static final String URL_MYSQL          = "jdbc:mysql://localhost:3306/clinica_medica?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC";
	private static final String DRIVER_CLASS_MYSQL = "com.mysql.cj.jdbc.Driver";
	private static final String USER               = "root";
	private static final String PASS               = "root";
	
	public Dao() {}
	
	/**
	 * Conecta na base de dados do servidor de base de dados.]
	 * 
	 * @return Retorna a conexão estabelecida.
	 * @throws ClassNotFoundException Diasparado se houver algum problema ao registrar o drive especificado.
	 * @throws SQLException Disparado se ocorrer algum erro de sql. 
	 */
	public Connection conectar() throws ClassNotFoundException, SQLException {
		
		Class.forName(DRIVER_CLASS_MYSQL);
		Connection conn = DriverManager.getConnection(URL_MYSQL, USER, PASS);
		System.out.println("[" +  new Utils().getDataHora() + "]: Conectado na base de dados.");
		return conn;
		
	}
	
	/**
	 * Encerra a conexão ou uma declaração preparada ou um grupo de resultados de dados.
	 * 
	 * @param conn O objeto de conexão.
 	 * @param pstm O objeto de declaração preparada.
	 * @param rs O objeto de grupo de resultados.
	 * @throws SQLException Disparado quando um erro de sql ocorrer.
	 */
	public void desconectar(Connection conn, PreparedStatement pstm, ResultSet rs) throws SQLException {
		
        if (conn!= null) {
            conn.close();
            System.out.println("["  + new Utils().getDataHora() + "]: Desconectado do Banco de dados.");
        }

        if (pstm!= null) {
        	pstm.close();
        }

        if (rs!= null) {
            rs.close();
        }
		
	}

}
