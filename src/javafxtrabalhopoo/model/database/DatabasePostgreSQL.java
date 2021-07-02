package javafxtrabalhopoo.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabasePostgreSQL implements Database {
    private Connection connection;

    @Override
    public Connection conectar() {
        //Aki que esta ocorrendo o erro não esta passando no try
        try {
            Class.forName("org.postgresql.Driver");
            // acredito q o erro e uma dessa duas linha a de cima e a de baixo \/   /\
            
            //this.connection = DriverManager.getConnection("jdbc:postgresql://209.209.40.83:34449/nome", "admin","12345678");
            this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/DataBase_STJO", "postgres","123");
            // conectar no banco local
            
            return this.connection; //não ta retonado 
        } catch (SQLException | ClassNotFoundException ex) {
            //se comenta a linha de baixo não aparece o erro escrito mas n funcionar de qualquer geito pois aki e o catch
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
