package javafxtrabalhopoo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafxtrabalhopoo.model.domain.Jogo;
import javafxtrabalhopoo.model.domain.JogoComprado;
import javafxtrabalhopoo.model.domain.Relatorio;

public class RelatorioDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    JogoDao jogoDao = new JogoDao();
    JogoCompradoDao jogoCompradoDao = new JogoCompradoDao();

    public List<Relatorio> listarRelatorio() {
        String sql = "WITH\n"
                + "	listar_relatorio AS (\n"
                + "		SELECT DISTINCT  \n"
                + "			j.id_jogo, \n"
                + "			j.nome, \n"
                + "			gen.nome_genero, \n"
                + "			j.qtd_vend_jogo, \n"
                + "			j.valor\n"
                + "		FROM jogo AS j \n"
                + "			INNER JOIN genero AS gen \n"
                + "				ON (j.id_genero = gen.id_genero)\n"
                + "		ORDER BY j.id_jogo\n"
                + "	), 	listar_soma AS (\n"
                + "		SELECT DISTINCT\n"
                + "			j.id_jogo,\n"
                + "			COALESCE(SUM(jc.qtd_hora_jogada), 0) AS total\n"
                + "		FROM jogo AS j\n"
                + "			INNER JOIN jogo_comprado AS jc \n"
                + "				ON (j.id_jogo = jc.id_jogo)\n"
                + "		GROUP BY j.id_jogo\n"
                + "		ORDER BY j.id_jogo\n"
                + "	)\n"
                + "SELECT DISTINCT  \n"
                + "		lr.id_jogo,\n"
                + "		lr.nome, \n"
                + "		lr.nome_genero,\n"
                + "		ls.total,\n"
                + "		lr.qtd_vend_jogo,\n"
                + "		lr.valor\n"
                + "FROM listar_relatorio AS lr, listar_soma AS ls\n"
                + "WHERE lr.id_jogo = ls.id_jogo\n"
                + "ORDER BY lr.id_jogo;";

        List<Relatorio> retorno = new ArrayList<>();

        try {
            PreparedStatement stmtRelatorio = connection.prepareStatement(sql);
            ResultSet resultado = stmtRelatorio.executeQuery();

            while (resultado.next()) {
                Relatorio relatorio = new Relatorio();

                relatorio.setJogoNome(resultado.getString("nome"));
                relatorio.setJogoGenero(resultado.getString("nome_genero"));
                relatorio.setJogoTotalDeHorasjogadas(resultado.getInt("total"));
                relatorio.setJogoTotalDeVendas(resultado.getInt("qtd_vend_jogo"));
                relatorio.setJogoValor(resultado.getDouble("valor"));

                retorno.add(relatorio);
            }

        } catch (SQLException e) {
            System.out.println("Erro: RelatorioDao" + e);
        }

        return retorno;
    }

}
