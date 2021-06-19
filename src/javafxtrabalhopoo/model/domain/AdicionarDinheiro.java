package javafxtrabalhopoo.model.domain;

import java.io.Serializable;
import java.util.Date;


public class AdicionarDinheiro implements Serializable {
    
    private int idDinheiro;
    private int usuario;
    private String numCartao;
    private String validade;
    private String codSegurança;
    private double valor;
    private Date dataTransacao;
    private String nomePortador;
    
    public int getIdDinheiro() {
        return idDinheiro;
    }

    public void setIdDinheiro(int idDinheiro) {
        this.idDinheiro = idDinheiro;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCodSegurança() {
        return codSegurança;
    }

    public void setCodSegurança(String codSegurança) {
        this.codSegurança = codSegurança;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public String getNomePortador() {
        return nomePortador;
    }

    public void setNomePortador(String nomePortador) {
        this.nomePortador = nomePortador;
    }
    
}
