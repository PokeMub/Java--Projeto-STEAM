package javafxtrabalhopoo.model.domain;

import java.util.Date;

public class Jogo {
    private int idJogo;
    private int idGenero;
    private int idEvento;
    private int idUsuario;
    private int qtdVendida;
    private int restricaoIdade;
    private double valor;
    private int tempoEstimado;
    private String nome;
    private char statu;
    private Date dateCriacao;
    private String GeneroNome;
    
    public Jogo(){
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getQtdVendida() {
        return qtdVendida;
    }

    public void setQtdVendida(int qtdVendida) {
        this.qtdVendida = qtdVendida;
    }

    public int getRestricaoIdade() {
        return restricaoIdade;
    }

    public void setRestricaoIdade(int restricaoIdade) {
        this.restricaoIdade = restricaoIdade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(int tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getStatu() {
        return statu;
    }

    public void setStatu(char statu) {
        this.statu = statu;
    }

    public Date getDateCriacao() {
        return dateCriacao;
    }

    public void setDateCriacao(Date dateCriacao) {
        this.dateCriacao = dateCriacao;
    }

    public String getGeneroNome() {
        return GeneroNome;
    }

    public void setGeneroNome(String GeneroNome) {
        this.GeneroNome = GeneroNome;
    }
    
}
