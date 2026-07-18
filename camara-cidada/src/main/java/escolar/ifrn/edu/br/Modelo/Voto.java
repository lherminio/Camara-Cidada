package escolar.ifrn.edu.br.Modelo;

public class Voto {

    private Long id; // Identificador correspondente à Chave Primária do MySQL
    private Parlamentar parlamentar;
    private String voto;

    // SIM
    // NAO
    // ABSTENCAO
    // AUSENCIA

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Parlamentar getParlamentar() { return parlamentar; }
    public void setParlamentar(Parlamentar parlamentar) { this.parlamentar = parlamentar; }

    public String getVoto() { return voto; }
    public void setVoto(String voto) { this.voto = voto; }
}