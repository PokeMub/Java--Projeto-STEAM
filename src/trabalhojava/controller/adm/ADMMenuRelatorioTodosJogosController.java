/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller.adm;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxtrabalhopoo.model.dao.JogoCompradoDao;
import javafxtrabalhopoo.model.dao.JogoDao;
import javafxtrabalhopoo.model.dao.RelatorioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Relatorio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ADMMenuRelatorioTodosJogosController implements Initializable {

    @FXML
    private TableView<Relatorio> tableViewRelatorio;
    @FXML
    private TableColumn<Relatorio, String> tableColumnNome;
    @FXML
    private TableColumn<Relatorio, String> tableColumnGenero;
    @FXML
    private TableColumn<Relatorio, Integer> tableColumnHorasJogadas;
    @FXML
    private TableColumn<Relatorio, Integer> tableColumnVendaTotal;
    @FXML
    private TableColumn<Relatorio, Double> tableColumnValor;
    @FXML
    private Button buttonImprimir;
    
    private List<Relatorio> listRelatorio;
    private ObservableList<Relatorio> observableListRelatorio;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private final RelatorioDao relatorioDao = new RelatorioDao();
    private final JogoCompradoDao jogoCompradoDao = new JogoCompradoDao();
    private final JogoDao jogoDao = new JogoDao();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        relatorioDao.setConnection(connection);
        jogoCompradoDao.setConnection(connection);
        jogoDao.setConnection(connection);
        
        carregarTableRelatorio();
    }    
    
    public void carregarTableRelatorio() {
        listRelatorio = relatorioDao.listarRelatorio();

        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("jogoNome"));
        tableColumnGenero.setCellValueFactory(new PropertyValueFactory<>("jogoGenero"));
        tableColumnHorasJogadas.setCellValueFactory(new PropertyValueFactory<>("jogoTotalDeHorasjogadas"));
        tableColumnVendaTotal.setCellValueFactory(new PropertyValueFactory<>("jogoTotalDeVendas"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("jogoValor"));
        
        try {
            observableListRelatorio = FXCollections.observableArrayList(listRelatorio);
            tableViewRelatorio.setItems(observableListRelatorio);
        } catch (Exception e) {
            System.out.println("[Erro] RelatorioControle: " + e);
        }

    }
    
    public void handleImprimir() throws JRException{
        //HashMap filtro = new HashMap();
        //filtro.put("cdCategoria", 1);

        URL url = getClass().getResource("/trabalhojava/relatorios/RelatorioADM.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }
    
}
