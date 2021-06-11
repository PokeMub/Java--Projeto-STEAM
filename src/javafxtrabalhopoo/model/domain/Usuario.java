package javafxtrabalhopoo.model.domain;

import java.util.Date;


public class Usuario {
    private int idUsuario;
    private String nomeUsuario;
    private String restNome;
    private String cpf;
    private Date dataNascimento;
    private String senha;
    private String telefone;
    private char status;
    private String email;
    private double valorCarteira;
    
    public Usuario(){
    }

    
    public int getIdUsuario() {
        return idUsuario;
    }

    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    
    public String getRestNome() {
        return restNome;
    }

    
    public void setRestNome(String restNome) {
        this.restNome = restNome;
    }

    
    public String getCpf() {
        return cpf;
    }

    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    public Date getDataNascimento() {
        return dataNascimento;
    }

    
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    
    public String getSenha() {
        return senha;
    }

    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    public String getTelefone() {
        return telefone;
    }

    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    public char getStatus() {
        return status;
    }

    
    public void setStatus(char status) {
        this.status = status;
    }

    
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public double getValorCarteira() {
        return valorCarteira;
    }

    
    public void setValorCarteira(double valorCarteira) {
        this.valorCarteira = valorCarteira;
    }
    
    
    
}
