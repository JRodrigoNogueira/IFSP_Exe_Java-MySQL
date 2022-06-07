package br.edu.ifsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabase {
	private static Connection conexao; // Atributo que receberá os dados para conexão com o banco de dados.
	
	// String de conexão (Dados: servidor, banco de dados, usuário, senha, uso de SSL e fuso horário).
	private static final String SERVER = "jdbc:mysql://localhost";
	private static final String DATABASE = "/dpbd";
	private static final String USER = "?user=root";
	private static final String PASSWORD = "&password=1234";
	
	// O protocolo SSL criptografa o fluxo de dados entre o servidor de banco de dados 
    // e a aplicação cliente, protegendo-o de ataques externos.
	private static final String USE_SSL = "&useSSL=false";
	
    // Dependendo da versão do MySQL e de como seu servidor está configurado, também
    // pode ser preciso determinar um fuso horário específico do servidor MySQL.
	private static final String USE_TIMEZONE = "&useTimezone=true";
	private static final String SERVER_TIMEZONE = "&serverTimezone=UTC";
	
	private static final String STRING_CONNECTION = SERVER + DATABASE + USER + PASSWORD + USE_SSL + USE_TIMEZONE + SERVER_TIMEZONE;

    public static String conectaBd() { // Abre a conexão com o banco de dados.
        try {
            conexao = DriverManager.getConnection(STRING_CONNECTION); // Atribui os dados de conexão ao objeto "conexao".
        } catch (Exception e) {
        	// Caso ocorra qualquer tipo de exceção.
            return "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage(); 
        }
        return null; // Caso a conexão ocorra com sucesso.
    }

    public static Connection getConexaoBd() { // Retorna o objeto que contém os dados para conexão com o banco de dados.
        return conexao;
    }
}
