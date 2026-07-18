package escolar.ifrn.edu.br.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Classe de Infraestrutura responsável pela gerência da conexão com o serviço MySQL e carga dinâmica de scripts DDL.*/
public class GerenciadorDeConexao {

    private static final String URL = "jdbc:mysql://localhost:3306/camara_legislativa_db?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";     // Substitua pelo usuário do seu MySQL local
    private static final String PASSWORD = "root"; // Substitua pela senha do seu MySQL local

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}