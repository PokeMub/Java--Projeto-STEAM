package javafxtrabalhopoo.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import javafxtrabalhopoo.model.domain.Evento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxtrabalhopoo.model.domain.Jogo;

public class EventoDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Evento buscarId(Evento evento) {
        String sql = "SELECT * FROM evento WHERE id_evento=?";
        //String sql = "SELECT COUNT(*) FROM evento WHERE id_evento=?";
        Evento retorno = new Evento();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, evento.getIdEvento());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                evento.setIdEvento(resultado.getInt("id_evento"));
                evento.setNomeEvento(resultado.getString("nome_evento"));
                evento.setStatu_evento(resultado.getString("statu_evento").charAt(0));
                evento.setFormaDesconto(resultado.getString("forma_desconto"));
                evento.setValorDesconto(resultado.getFloat("valor_desconto"));
                evento.setDataInicio(resultado.getDate("data_inicio"));
                evento.setDataTermino(resultado.getDate("data_termino"));
                retorno = evento;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Evento> listar() {
        String sql = "SELECT * FROM evento";
        List<Evento> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Evento evento = new Evento();

                evento.setNomeEvento(resultado.getString("nome_evento"));
                evento.setValorDesconto(resultado.getFloat("valor_desconto"));
                evento.setIdEvento(resultado.getInt("id_evento"));
                //
                evento.setFormaDesconto(resultado.getString("forma_desconto"));
                evento.setStatu_evento(resultado.getString("statu_evento").charAt(0));
                evento.setDataInicio(resultado.getDate("data_inicio"));
                evento.setDataTermino(resultado.getDate("data_termino"));

                //
                retorno.add(evento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean remover(Evento evento) {
        String sql = "DELETE FROM evento WHERE id_evento=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, evento.getIdEvento());
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean alterarIdEventoJogo(Jogo jogo, int id_evento) {
        String sql = "UPDATE jogo SET id_evento=? WHERE id_evento=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, 3);
            stmt.setInt(2, id_evento);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // int sendo char tlavez bugue
    public boolean alterar(Evento evento) {
        String sql = "UPDATE evento SET nome_evento=?, valor_desconto=?, forma_desconto=?, statu_evento=? WHERE id_evento=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, evento.getNomeEvento());
            stmt.setDouble(2, evento.getValorDesconto());
            stmt.setString(3, evento.getFormaDesconto());
            stmt.setString(4, String.valueOf(evento.getStatu_evento()));
            stmt.setInt(5, evento.getIdEvento());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    // int sendo char tlavez bugue

    public boolean inserir(Evento evento) {
        String sql = "INSERT INTO evento(nome_evento, valor_desconto, forma_desconto, statu_evento, data_inicio, data_termino) VALUES(?,?,?,?,?,?)";
        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, evento.getNomeEvento());

            stmt.setDouble(2, evento.getValorDesconto());

            stmt.setString(3, evento.getFormaDesconto());

            stmt.setString(4, "A");

            stmt.setDate(5, (Date) evento.getDataInicio());

            stmt.setDate(6, (Date) evento.getDataTermino());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
