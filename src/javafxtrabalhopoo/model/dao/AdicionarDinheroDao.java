package javafxtrabalhopoo.model.dao;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxtrabalhopoo.model.domain.AdicionarDinheiro;
import javafxtrabalhopoo.model.domain.Usuario;



public class AdicionarDinheroDao {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
    public boolean inserirHistCart(AdicionarDinheiro adicionarDinheiro) throws SQLException{
        String sql = "INSERT INTO ad_dinheiro(id_usuario, num_cartao, validade, cod_seguranca, valor, data_transacao, nome_portador) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, adicionarDinheiro.getNumCartao());
            stmt.setString(2, adicionarDinheiro.getValidade());
            stmt.setString(3, adicionarDinheiro.getCodSegurança());
            stmt.setDouble(4, adicionarDinheiro.getValor());
            stmt.setDate(5, (Date) adicionarDinheiro.getDataTransacao());
            stmt.setString(6, adicionarDinheiro.getNomePortador()); 
            stmt.execute();
            return true;
        }catch (SQLException ex){
            Logger.getLogger(AdicionarDinheroDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        
        }
        
        
    }
    
    
    
    
    
    
    
    
    
}
