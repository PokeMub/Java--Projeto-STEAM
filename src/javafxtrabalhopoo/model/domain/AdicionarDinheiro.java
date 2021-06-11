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
    
}
