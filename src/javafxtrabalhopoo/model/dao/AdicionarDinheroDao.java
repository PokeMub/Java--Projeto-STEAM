package javafxtrabalhopoo.model.dao;

import java.sql.Connection;


public class AdicionarDinheroDao {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
