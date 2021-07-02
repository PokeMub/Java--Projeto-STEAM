package javafxtrabalhopoo.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroJogoDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(int idUsuario, int idJogo) {
        String sql = "INSERT INTO log_cadast_jogo(id_jogo, data_cadastro, hora_cadastro, id_usuario_cadastrou) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idJogo);

            Date data = new Date(System.currentTimeMillis());
            SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm:ss");

            java.sql.Date dataf = java.sql.Date.valueOf(formatarDate.format(data));
            java.sql.Time horaf = java.sql.Time.valueOf(formatarHora.format(data));

            stmt.setDate(2, dataf); //pega a data
            stmt.setTime(3, horaf); //pega a hora

            stmt.setInt(4, idUsuario);

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JogoCompradoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean remover(int id) {
        String sql = "DELETE FROM log_cadast_jogo WHERE id_jogo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
