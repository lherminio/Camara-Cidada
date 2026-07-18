package escolar.ifrn.edu.br.Modelo;

/**
 * Entidade que representa um Parlamentar (vereador).
 * Mapeia diretamente os dados da tabela e abrange os requisitos do RF01.
 */
public class Parlamentar {

    private Long id; // Identificador correspondente à Chave Primária do MySQL
    private String nomeCompleto;
    private String nomeParlamentar;
    private String partido;
    private String biografia;
    private String foto;
    private boolean ativo;
    private int legislatura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeParlamentar() {
        return nomeParlamentar;
    }

    public void setNomeParlamentar(String nomeParlamentar) {
        this.nomeParlamentar = nomeParlamentar;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getLegislatura() {
        return legislatura;
    }

    public void setLegislatura(int legislatura) {
        this.legislatura = legislatura;
    }

    @Override
    public String toString() {
        return "Parlamentar{id=" + id + ", nomeParlamentar='" + nomeParlamentar +
           "', partido='" + partido + "', legislatura=" + legislatura +
           ", ativo=" + ativo + "}";
    }

}