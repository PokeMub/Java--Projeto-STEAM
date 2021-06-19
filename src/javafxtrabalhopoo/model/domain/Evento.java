package javafxtrabalhopoo.model.domain;

import java.io.Serializable;
import java.util.Date;


public class Evento implements Serializable {
    private int idEvento;
    private String nomeEvento;
    private double valorDesconto;
    private String formaDesconto;
    private char statu_evento;
    private Date dataInicio;
    private Date dataTermino;
    
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getFormaDesconto() {
        return formaDesconto;
    }

    public void setFormaDesconto(String formaDesconto) {
        this.formaDesconto = formaDesconto;
    }

    public char getStatu_evento() {
        return statu_evento;
    }

    public void setStatu_evento(char statu_evento) {
        this.statu_evento = statu_evento;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }
    
    
    
}
