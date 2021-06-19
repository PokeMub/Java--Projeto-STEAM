/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller.adm;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafxtrabalhopoo.model.dao.JogoCompradoDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;

public class ADMMenuGraficosTodosJogosController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis categoryAxis;
    @FXML
    private NumberAxis numberAxis;
    
  

    private ObservableList<String> observableListIDjogos = FXCollections.observableArrayList();

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final JogoCompradoDao jogoCompradoDao = new JogoCompradoDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        String[] arrayMeses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};

        observableListIDjogos.addAll(Arrays.asList(arrayMeses));
        // Associa os nomes de mês como categorias para o eixo horizontal.

        categoryAxis.setCategories(observableListIDjogos);

        jogoCompradoDao.setConnection(connection);

        
        Map<Integer, ArrayList> dados = jogoCompradoDao.listarQuantidadeVendasPorMes();

        for (Map.Entry<Integer, ArrayList> dadosItem : dados.entrySet()) { // vai pegar os dados do map em dados item
            XYChart.Series<String, Integer> series = new XYChart.Series<>();// serie 2021
            series.setName(dadosItem.getKey().toString());

            for (int i = 0; i < dadosItem.getValue().size(); i = i + 2) {//varrer o ano de 2021 e ver todos os meses com vendas
                String mes;
                Integer quantidade;
                mes = retornaNomeMes((int) dadosItem.getValue().get(i));// vai ver o mes e retonar a aray
                quantidade = (Integer) dadosItem.getValue().get(i + 1);// pegar a venda e adiciona no map
                series.getData().add(new XYChart.Data<>(mes, quantidade));
            }

            barChart.getData().add(series);
        }

    }

    public String retornaNomeMes(int mes) {
        switch (mes) {
            case 1:
                return "Jan";
            case 2:
                return "Fev";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "Mai";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Set";
            case 10:
                return "Out";
            case 11:
                return "Nov";
            case 12:
                return "Dez";
            default:
                return "";
}

    }

}