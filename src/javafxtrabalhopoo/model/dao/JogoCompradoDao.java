package javafxtrabalhopoo.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxtrabalhopoo.model.domain.Jogo;
import javafxtrabalhopoo.model.domain.JogoComprado;
import javafxtrabalhopoo.model.domain.Usuario;

public class JogoCompradoDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    JogoDao jogoDao = new JogoDao();
    
    public boolean inserir(int idUsuario, int idJogo, double valor) {
        String sql = "INSERT INTO jogo_comprado(id_usuario, id_jogo, qtd_hora_jogada, "
                   + "valor_produto, statu_reembouso, data_compra, hora_compra) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idJogo);
            stmt.setInt(3, 0);
            stmt.setDouble(4, valor);
            stmt.setString(5, "A");
            
            Date data = new Date(System.currentTimeMillis());
            SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm:ss");
            System.out.print("Data:" + formatarDate.format(data));
            
            java.sql.Date dataf = java.sql.Date.valueOf(formatarDate.format(data));
            java.sql.Time horaf = java.sql.Time.valueOf(formatarHora.format(data));
            
            stmt.setDate(6, dataf); //pega a data
            stmt.setTime(7, horaf); //pega a hora
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JogoCompradoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<JogoComprado> listar(int id) {
        String sql = "SELECT * FROM jogo_comprado WHERE id_usuario=?";
        List<JogoComprado> retorno = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            jogoDao.setConnection(connection);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                JogoComprado jogoComprado = new JogoComprado();
                
                jogoComprado.setIdJogoComprado(resultado.getInt("id_jogo_comprado"));
                jogoComprado.setIdUsuario(resultado.getInt("id_usuario"));
                jogoComprado.setIdJogo(resultado.getInt("id_jogo"));
                jogoComprado.setQtdHoraJogada(resultado.getInt("qtd_hora_jogada"));
                jogoComprado.setValorProduto(resultado.getInt("valor_produto"));
                jogoComprado.setStatusReembolso(resultado.getString("statu_reembouso").charAt(0));
                jogoComprado.setDataCompra(resultado.getDate("data_compra"));
                jogoComprado.setHoraCompra(resultado.getTime("hora_compra"));
                jogoComprado.setJogo(jogoDao.buscarId(jogoComprado.getIdJogo()));
                if (resultado.getString("statu_reembouso").charAt(0) == 'A') {
                    retorno.add(jogoComprado);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro: JogoCompradoDao" + e);
        }

        return retorno;
    }
    
    public void UpdateHoraJoga(int qtd_hora_jogada, int id_jogo_comprado ) {
        String sql = "UPDATE jogo_comprado SET qtd_hora_jogada=? WHERE id_jogo_comprado=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, qtd_hora_jogada);
            stmt.setInt(2, id_jogo_comprado);
            
            stmt.execute();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
        public void UpdateStatu_reembouso(int id_jogo_comprado) {
        String sql = "UPDATE jogo_comprado SET statu_reembouso='R' WHERE id_jogo_comprado=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id_jogo_comprado);
            
            stmt.execute();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
        
        
    public Map<Integer, ArrayList> listarQuantidadeVendasPorMes() {
        String sql = "select count(id_jogo_comprado), extract(year from data_compra) as ano, extract(month from data_compra) as mes from jogo_comprado group by ano, mes order by ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(resultado.getInt("ano")))
                {
                    linha.add(resultado.getInt("mes"));
                    linha.add(resultado.getInt("count"));
                    retorno.put(resultado.getInt("ano"), linha);
                }else{
                    ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
                    linhaNova.add(resultado.getInt("mes"));
                    linhaNova.add(resultado.getInt("count"));
                }
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(JogoCompradoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public boolean existe(Jogo jogo) {
        String sql = "SELECT * FROM jogo_comprado WHERE id_jogo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, jogo.getIdJogo());
            
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

}
