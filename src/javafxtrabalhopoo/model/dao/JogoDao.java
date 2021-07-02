package javafxtrabalhopoo.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxtrabalhopoo.model.domain.Evento;
import javafxtrabalhopoo.model.domain.Jogo;

public class JogoDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    GeneroDao generoDao = new GeneroDao();
    EventoDao eventoDao = new EventoDao();

    public List<Jogo> listar() {
        String sql = "SELECT * FROM jogo";
        List<Jogo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            generoDao.setConnection(connection);
            eventoDao.setConnection(connection);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Jogo jogo = new Jogo();
                jogo.setIdJogo(resultado.getInt("id_jogo"));
                jogo.setIdGenero(resultado.getInt("id_genero"));
                jogo.setIdEvento(resultado.getInt("id_evento"));
                jogo.setIdUsuario(resultado.getInt("id_usuario"));
                jogo.setQtdVendida(resultado.getInt("qtd_vend_jogo"));
                jogo.setRestricaoIdade(resultado.getInt("restricao_idade"));
                jogo.setValor(resultado.getDouble("valor"));
                jogo.setTempoEstimado(resultado.getInt("tempo_estimado"));
                jogo.setNome(resultado.getString("nome"));
                jogo.setStatu(resultado.getString("statu_jogo").charAt(0));
                jogo.setDateCriacao(resultado.getDate("data_criacao"));
                jogo.setGeneroNome(generoDao.buscarNome(jogo.getIdGenero()));
                System.out.println("Nome: " + jogo.getNome());
                if (jogo.getIdEvento() == 3) {
                    jogo.setValorDesconto(jogo.getValor());
                    System.out.println("1 if");
                }else{
                    
                    Evento evento = new Evento();
                    evento.setIdEvento(jogo.getIdEvento());
                    evento = eventoDao.buscarId(evento);
                    
                    if (evento.getStatu_evento() == 'D') {
                        
                        jogo.setValorDesconto(jogo.getValor());
                       
                    }else{
                        
                        if (evento.getFormaDesconto().equals('%')) {
                            jogo.setValorDesconto((jogo.getValor() * (100 - evento.getValorDesconto())) / 100);
                           
                        }else if (evento.getFormaDesconto().replaceAll("\\s+","").toUpperCase().equals("FLAT")) {
                            jogo.setValorDesconto(jogo.getValor() - evento.getValorDesconto());
                            
                        }else{
                            jogo.setValorDesconto(jogo.getValor());
                            
                        }
                    }
                    
                }

                retorno.add(jogo);
            }
        } catch (Exception ex) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Jogo buscarId(int id) {
        String sql = "SELECT * FROM jogo WHERE id_jogo=?";
        Jogo jogo = new Jogo();
        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            generoDao.setConnection(connection);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                jogo.setIdJogo(resultado.getInt("id_jogo"));
                jogo.setIdGenero(resultado.getInt("id_genero"));
                jogo.setIdEvento(resultado.getInt("id_evento"));
                jogo.setIdUsuario(resultado.getInt("id_usuario"));
                jogo.setQtdVendida(resultado.getInt("qtd_vend_jogo"));
                jogo.setRestricaoIdade(resultado.getInt("restricao_idade"));
                jogo.setValor(resultado.getDouble("valor"));
                jogo.setTempoEstimado(resultado.getInt("tempo_estimado"));
                jogo.setNome(resultado.getString("nome"));
                jogo.setStatu(resultado.getString("statu_jogo").charAt(0));
                jogo.setDateCriacao(resultado.getDate("data_criacao"));
                jogo.setGeneroNome(generoDao.buscarNome(jogo.getIdGenero()));
            }
        } catch (SQLException e) {
            System.out.println("JogoDao > buscarId");
        }

        return jogo;
    }
    
    public void alterarQtd(int id, int qtd) {
        String sql = "UPDATE jogo SET qtd_vend_jogo=? WHERE id_jogo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, qtd);
            stmt.setInt(2, id);
            
            stmt.execute();

            
        } catch (SQLException ex) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    
    public boolean alterar(Jogo jogo) {
        String sql = "UPDATE jogo SET id_genero=?, id_evento=?, restricao_idade=?, valor=?, tempo_estimado=?, nome=?, statu_jogo=? WHERE id_jogo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, jogo.getIdGenero());
            stmt.setInt(2, jogo.getIdEvento());
            stmt.setInt(3, jogo.getRestricaoIdade());
            stmt.setDouble(4, jogo.getValor());
            stmt.setInt(5, jogo.getTempoEstimado());
            stmt.setString(6, jogo.getNome());
            stmt.setString(7, String.valueOf(jogo.getStatu()));
            stmt.setInt(8, jogo.getIdJogo());
            stmt.execute();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean inserir(Jogo jogo) {
        String sql = "INSERT INTO jogo(id_genero, id_evento, id_usuario, qtd_vend_jogo, restricao_idade, valor, tempo_estimado, nome, statu_jogo, data_criacao) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, jogo.getIdGenero());
            stmt.setInt(2, jogo.getIdEvento());
            stmt.setInt(3, jogo.getIdUsuario());
            stmt.setInt(4, 0);
            stmt.setInt(5, jogo.getRestricaoIdade());
            stmt.setDouble(6, jogo.getValor());
            stmt.setInt(7, jogo.getTempoEstimado());
            stmt.setString(8, jogo.getNome());
            stmt.setString(9, String.valueOf('A'));
            stmt.setDate(10, (Date) jogo.getDateCriacao());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    public boolean remover(Jogo jogo) {
        String sql = "DELETE FROM jogo WHERE id_jogo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, jogo.getIdJogo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean existe(String nome) {
        String sql = "SELECT * FROM jogo WHERE nome=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, nome);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    
    public int buscaId(String nome) {
        String sql = "SELECT id_jogo FROM jogo WHERE nome=?";
        int id = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, nome);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                id = resultado.getInt("id_jogo");
            }

        } catch (Exception e) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
}
