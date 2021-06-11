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
    
}
