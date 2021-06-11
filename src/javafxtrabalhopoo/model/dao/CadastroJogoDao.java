package javafxtrabalhopoo.model.dao;

import java.sql.Connection;


public class CadastroJogoDao {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
