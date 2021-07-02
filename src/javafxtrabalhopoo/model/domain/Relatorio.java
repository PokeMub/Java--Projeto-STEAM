package javafxtrabalhopoo.model.domain;

public class Relatorio {
    private String jogoNome;
    private String jogoGenero;
    private Integer jogoTotalDeHorasjogadas;
    private Integer jogoTotalDeVendas;
    private Double jogoValor;
    
    private Jogo jogo;
    private JogoComprado jogoComprado;
    
    public Relatorio(){
    }

    public String getJogoNome() {
        return jogoNome;
    }

    public void setJogoNome(String jogoNome) {
        this.jogoNome = jogoNome;
    }

    public String getJogoGenero() {
        return jogoGenero;
    }

    public void setJogoGenero(String jogoGenero) {
        this.jogoGenero = jogoGenero;
    }

    public Integer getJogoTotalDeHorasjogadas() {
        return jogoTotalDeHorasjogadas;
    }

    public void setJogoTotalDeHorasjogadas(Integer jogoTotalDeHorasjogadas) {
        this.jogoTotalDeHorasjogadas = jogoTotalDeHorasjogadas;
    }

    public Integer getJogoTotalDeVendas() {
        return jogoTotalDeVendas;
    }

    public void setJogoTotalDeVendas(Integer jogoTotalDeVendas) {
        this.jogoTotalDeVendas = jogoTotalDeVendas;
    }

    public Double getJogoValor() {
        return jogoValor;
    }

    public void setJogoValor(Double jogoValor) {
        this.jogoValor = jogoValor;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public JogoComprado getJogoComprado() {
        return jogoComprado;
    }

    public void setJogoComprado(JogoComprado jogoComprado) {
        this.jogoComprado = jogoComprado;
    }
    
    
}
