package javafxtrabalhopoo.model.domain;

import java.sql.Time;
import java.util.Date;


public class CadastroJogo {
    private int idLog;
    private int idJogo;
    private int idUsuario;
    private Date dataCadastro;
    private Time horaCadastro;
    
    public CadastroJogo(){
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Time getHoraCadastro() {
        return horaCadastro;
    }

    public void setHoraCadastro(Time horaCadastro) {
        this.horaCadastro = horaCadastro;
    }
    
}
