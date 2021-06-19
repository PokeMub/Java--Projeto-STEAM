package javafxtrabalhopoo.model.domain;

import java.sql.Time;
import java.util.Date;

public class JogoComprado {
    private int idJogoComprado;
    private int idUsuario;
    private int idJogo;
    private int qtdHoraJogada;
    private double valorProduto;
    private char statusReembolso;
    private Date dataCompra;
    private Time horaCompra;
    private Jogo jogo;
    private String jogoNome;
    private String JogoGenero;
    
    public JogoComprado(){
    }

    public int getIdJogoComprado() {
        return idJogoComprado;
    }

    public void setIdJogoComprado(int idJogoComprado) {
        this.idJogoComprado = idJogoComprado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getQtdHoraJogada() {
        return qtdHoraJogada;
    }

    public void setQtdHoraJogada(int qtdHoraJogada) {
        this.qtdHoraJogada = qtdHoraJogada;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public char getStatusReembolso() {
        return statusReembolso;
    }

    public void setStatusReembolso(char statusReembolso) {
        this.statusReembolso = statusReembolso;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Time getHoraCompra() {
        return horaCompra;
    }

    public void setHoraCompra(Time horaCompra) {
        this.horaCompra = horaCompra;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public String getJogoNome() {
        return getJogo().getNome();
    }

    public String getJogoGenero() {
        return getJogo().getGeneroNome();
    }
    
}
